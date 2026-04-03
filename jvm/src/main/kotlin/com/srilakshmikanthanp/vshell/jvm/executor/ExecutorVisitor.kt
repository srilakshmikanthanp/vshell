package com.srilakshmikanthanp.vshell.jvm.executor

import com.srilakshmikanthanp.vshell.core.ast.*
import com.srilakshmikanthanp.vshell.jvm.command.operator.AndOperatorCommand
import com.srilakshmikanthanp.vshell.jvm.command.CommandBuilderRegistry
import com.srilakshmikanthanp.vshell.jvm.command.operator.OrOperatorCommand
import com.srilakshmikanthanp.vshell.jvm.command.operator.PipeOperatorCommand
import com.srilakshmikanthanp.vshell.jvm.context.Context
import com.srilakshmikanthanp.vshell.jvm.executor.substitution.CommandSubstitutor

class ExecutorVisitor(
  private val context: Context,
  private val commandSubstitutor: CommandSubstitutor,
  private val commandBuilderRegistry: CommandBuilderRegistry,
): NodeVisitor<ExecutionShellNode> {
  override fun visitCommandSubstitution(node: CommandSubstitutionNode): ExecutionShellNode {
    val command = node.commandNode.accept(this).asCommandShellNode().command
    return StringLiteralShellNode(commandSubstitutor.substitute(command))
  }

  override fun visitLeafCommand(node: LeafCommandNode): ExecutionShellNode {
    val commandBuilder = commandBuilderRegistry.get(node.identifierNode.name) ?: throw ExecutorCommandNotFoundException(node.identifierNode.name)
    val arguments = node.arguments.map { it.accept(this).asExpressionShellNode().asLiteralShellNode().asStringLiteralShellNode() }
    return CommandShellNode(commandBuilder.build(context, arguments.map { it.value }))
  }

  override fun visitCommands(node: CommandsNode): ExecutionShellNode {
    return CommandsShellNode(node.commands.map { it.accept(this).asCommandShellNode().command })
  }

  override fun visitAndCommand(node: AndCommandNode): ExecutionShellNode {
    val left = node.left.accept(this).asCommandShellNode().command
    val right = node.right.accept(this).asCommandShellNode().command
    return CommandShellNode(AndOperatorCommand(left, right))
  }

  override fun visitOrCommand(node: OrCommandNode): ExecutionShellNode {
    val left = node.left.accept(this).asCommandShellNode().command
    val right = node.right.accept(this).asCommandShellNode().command
    return CommandShellNode(OrOperatorCommand(left, right))
  }

  override fun visitPipeCommand(node: PipeCommandNode): ExecutionShellNode {
    val left = node.left.accept(this).asCommandShellNode().command
    val right = node.right.accept(this).asCommandShellNode().command
    return CommandShellNode(PipeOperatorCommand(left, right))
  }

  override fun visitAnd(node: AndNode): ExecutionShellNode {
    val left = node.left.accept(this).asExpressionShellNode().asLiteralShellNode().asBooleanLiteralShellNode()
    if (!left.value) return BooleanLiteralShellNode(false)
    val right = node.right.accept(this).asExpressionShellNode().asLiteralShellNode()
    return left.asAndShellOperatorNode().and(right)
  }

  override fun visitOr(node: OrNode): ExecutionShellNode {
    val left = node.left.accept(this).asExpressionShellNode().asLiteralShellNode().asBooleanLiteralShellNode()
    if (left.value) return BooleanLiteralShellNode(true)
    val right = node.right.accept(this).asExpressionShellNode().asLiteralShellNode()
    return left.asOrShellOperatorNode().or(right)
  }

  override fun visitNot(node: NotNode): ExecutionShellNode {
    return node.expression.accept(this).asExpressionShellNode().asLiteralShellNode().asNotShellOperatorNode().not()
  }

  override fun visitPlus(node: PlusNode): ExecutionShellNode {
    val left = node.left.accept(this).asExpressionShellNode().asLiteralShellNode()
    val right = node.right.accept(this).asExpressionShellNode().asLiteralShellNode()
    return left.asPlusShellOperatorNode().plus(right)
  }

  override fun visitMinus(node: MinusNode): ExecutionShellNode {
    val left = node.left.accept(this).asExpressionShellNode().asLiteralShellNode()
    val right = node.right.accept(this).asExpressionShellNode().asLiteralShellNode()
    return left.asMinusShellOperatorNode().minus(right)
  }

  override fun visitMultiply(node: MultiplyNode): ExecutionShellNode {
    val left = node.left.accept(this).asExpressionShellNode().asLiteralShellNode()
    val right = node.right.accept(this).asExpressionShellNode().asLiteralShellNode()
    return left.asMultiplyShellOperatorNode().multiply(right)
  }

  override fun visitDivide(node: DivideNode): ExecutionShellNode {
    val left = node.left.accept(this).asExpressionShellNode().asLiteralShellNode()
    val right = node.right.accept(this).asExpressionShellNode().asLiteralShellNode()
    return left.asDivideShellOperatorNode().divide(right)
  }

  override fun visitModulo(node: ModuloNode): ExecutionShellNode {
    val left = node.left.accept(this).asExpressionShellNode().asLiteralShellNode()
    val right = node.right.accept(this).asExpressionShellNode().asLiteralShellNode()
    return left.asModuloShellOperatorNode().modulo(right)
  }

  override fun visitPower(node: PowerNode): ExecutionShellNode {
    val left = node.left.accept(this).asExpressionShellNode().asLiteralShellNode()
    val right = node.right.accept(this).asExpressionShellNode().asLiteralShellNode()
    return left.asPowerShellOperatorNode().power(right)
  }

  override fun visitNeq(node: NeqNode): ExecutionShellNode {
    val left = node.left.accept(this).asExpressionShellNode().asLiteralShellNode()
    val right = node.right.accept(this).asExpressionShellNode().asLiteralShellNode()
    return left.asNeqShellOperatorNode().neq(right)
  }

  override fun visitEq(node: EqNode): ExecutionShellNode {
    val left = node.left.accept(this).asExpressionShellNode().asLiteralShellNode()
    val right = node.right.accept(this).asExpressionShellNode().asLiteralShellNode()
    return left.asEqShellOperatorNode().eq(right)
  }

  override fun visitGt(node: GtNode): ExecutionShellNode {
    val left = node.left.accept(this).asExpressionShellNode().asLiteralShellNode()
    val right = node.right.accept(this).asExpressionShellNode().asLiteralShellNode()
    return left.asGtShellOperatorNode().gt(right)
  }

  override fun visitGte(node: GteNode): ExecutionShellNode {
    val left = node.left.accept(this).asExpressionShellNode().asLiteralShellNode()
    val right = node.right.accept(this).asExpressionShellNode().asLiteralShellNode()
    return left.asGteShellOperatorNode().gte(right)
  }

  override fun visitLt(node: LtNode): ExecutionShellNode {
    val left = node.left.accept(this).asExpressionShellNode().asLiteralShellNode()
    val right = node.right.accept(this).asExpressionShellNode().asLiteralShellNode()
    return left.asLtShellOperatorNode().lt(right)
  }

  override fun visitLte(node: LteNode): ExecutionShellNode {
    val left = node.left.accept(this).asExpressionShellNode().asLiteralShellNode()
    val right = node.right.accept(this).asExpressionShellNode().asLiteralShellNode()
    return left.asLteShellOperatorNode().lte(right)
  }

  override fun visitUnaryPlus(node: UnaryPlusNode): ExecutionShellNode {
    return node.expression.accept(this).asUnaryPlusShellOperatorNode().plus()
  }

  override fun visitUnaryMinus(node: UnaryMinusNode): ExecutionShellNode {
    return node.expression.accept(this).asUnaryMinusShellOperatorNode().minus()
  }

  override fun visitNumeric(node: NumericExpressionValue): ExecutionShellNode {
    return NumericLiteralShellNode(node.value.toDouble())
  }

  override fun visitBoolean(node: BooleanExpressionValue): ExecutionShellNode {
    return BooleanLiteralShellNode(node.value)
  }

  override fun visitString(node: StringExpressionValue): ExecutionShellNode {
    return StringLiteralShellNode(node.value)
  }

  override fun visitInteger(node: IntegerExpressionValue): ExecutionShellNode {
    return IntegerLiteralShellNode(node.value.toInt())
  }

  override fun visitIdentifier(node: IdentifierNode): ExecutionShellNode {
    return StringLiteralShellNode(context.findReference(node.name) ?: throw ExecutorException("Variable ${node.name} not found"))
  }

  override fun visitTemplateString(node: TemplateStringNode): ExecutionShellNode {
    val segments = node.segments.map { it.accept(this).asExpressionShellNode().asLiteralShellNode().asStringLiteralShellNode() }
    return StringLiteralShellNode(segments.joinToString(separator = "") { it.value })
  }
}
