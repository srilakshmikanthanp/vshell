package com.srilakshmikanthanp.vshell.parser

import com.srilakshmikanthanp.vshell.parser.ast.*
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs
import kotlin.test.assertTrue

class VshellParserTest {
  private val parser : VshellParser = VshellAntlrParser()

  @Test
  fun shouldParseEmptyInputAsEmptyCommandsNode() {
    val input = ""
    val result = parser.parse(input).asVShellCommandsNode()
    assertTrue(result.commands.isEmpty())
  }

  @Test
  fun shouldParseBlankInputAsEmptyCommandsNode() {
    val input = "   \t  \n  "
    val result = parser.parse(input).asVShellCommandsNode()
    assertTrue(result.commands.isEmpty())
  }

  @Test
  fun shouldParseSingleCommandWithoutArguments() {
    val commandName = "echo"
    val command = assertIs<LeafCommandNode>(parser.parse(commandName).asVShellCommandsNode().commands.single())
    assertEquals("echo", command.identifierNode.name)
    assertTrue(command.arguments.isEmpty())
  }

  @Test
  fun shouldParseLeafCommandWithArguments() {
    val input = "cp 'src' 'dst'"
    val command = assertIs<LeafCommandNode>(parser.parse(input).asVShellCommandsNode().commands.single())
    assertEquals("cp", command.identifierNode.name)
    assertEquals(2, command.arguments.size)
    val src = assertIs<StringExpressionValue>(command.arguments[0])
    val dst = assertIs<StringExpressionValue>(command.arguments[1])
    assertEquals("src", src.value)
    assertEquals("dst", dst.value)
  }

  @Test
  fun shouldParseSemicolonSeparatedCommandsIntoMultipleCommandNodes() {
    val input = "echo'a'; echo 'b'"
    val commands = parser.parse(input).asVShellCommandsNode()
    assertEquals(2, commands.commands.size)
    assertIs<LeafCommandNode>(commands.commands[0])
    assertIs<LeafCommandNode>(commands.commands[1])
  }

  @Test
  fun shouldParseSingleCommandWithTrailingSemicolon() {
    val input = "echo 'a';"
    val commands = parser.parse(input).asVShellCommandsNode()
    assertEquals(1, commands.commands.size)
    assertIs<LeafCommandNode>(commands.commands.single())
  }

  @Test
  fun shouldBuildPipeCommandNodeForPipeOperator() {
    val input = "cat 'file' | grep 'foo'"
    val command = parser.parse(input).asVShellCommandsNode().commands.single()
    val pipe = assertIs<PipeCommandNode>(command)
    assertIs<LeafCommandNode>(pipe.left)
    assertIs<LeafCommandNode>(pipe.right)
  }

  @Test
  fun shouldBuildAndCommandNodeForCommandLevelAndOperator() {
    val input = "build && test"
    val command = parser.parse(input).asVShellCommandsNode().commands.single()
    val andNode = assertIs<AndCommandNode>(command)
    assertIs<LeafCommandNode>(andNode.left)
    assertIs<LeafCommandNode>(andNode.right)
  }

  @Test
  fun shouldBuildOrCommandNodeForCommandLevelOrOperator() {
    val input = "build || fallback"
    val command = parser.parse(input).asVShellCommandsNode().commands.single()
    val orNode = assertIs<OrCommandNode>(command)
    assertIs<LeafCommandNode>(orNode.left)
    assertIs<LeafCommandNode>(orNode.right)
  }

  @Test
  fun shouldFlattenGroupedSubCommandToInnerCommandNode() {
    val input = "(echo 'hi')"
    val command = parser.parse(input).asVShellCommandsNode().commands.single()
    val leaf = assertIs<LeafCommandNode>(command)
    assertEquals("echo", leaf.identifierNode.name)
  }

  @Test
  fun shouldAssociateChainedPipeCommandsLeftToRight() {
    val input = "a | b | c"
    val command = parser.parse(input).asVShellCommandsNode().commands.single()
    val root = assertIs<PipeCommandNode>(command)
    assertIs<PipeCommandNode>(root.left)
    assertIs<LeafCommandNode>(root.right)
  }

  @Test
  fun shouldParseIdentifierArgumentAsIdentifierNode() {
    val input = $$"echo %`${varName}`"
    val command = assertIs<LeafCommandNode>(parser.parse(input).asVShellCommandsNode().commands.single())
    assertEquals(1, command.arguments.size)
    val argument = command.arguments.single().asVShellExpressionNode()
    val template = assertIs<TemplateStringNode>(argument)
    val identifier = assertIs<IdentifierNode>(template.segments.single())
    assertEquals("varName", identifier.name)
  }

  @Test
  fun shouldParseCommandSubstitutionArgumentAsCommandSubstitutionNode() {
    val input = $$"echo %`${#(pwd)}`"
    val command = assertIs<LeafCommandNode>(parser.parse(input).asVShellCommandsNode().commands.single())
    assertEquals(1, command.arguments.size)
    val argument = command.arguments.single().asVShellExpressionNode()
    val template = assertIs<TemplateStringNode>(argument)
    val substitution = assertIs<CommandSubstitutionNode>(template.segments.single())
    assertIs<LeafCommandNode>(substitution.commandNode)
  }

  @Test
  fun shouldParsePipelineInsideCommandSubstitutionAsExpressionArgument() {
    val input = $$"echo %`${#(cat 'file' | grep 'value')}`"
    val command = assertIs<LeafCommandNode>(parser.parse(input).asVShellCommandsNode().commands.single())
    assertEquals(1, command.arguments.size)
    val argument = command.arguments.single().asVShellExpressionNode()
    val template = assertIs<TemplateStringNode>(argument)
    val substitution = assertIs<CommandSubstitutionNode>(template.segments.single())
    assertIs<PipeCommandNode>(substitution.commandNode)
  }
}
