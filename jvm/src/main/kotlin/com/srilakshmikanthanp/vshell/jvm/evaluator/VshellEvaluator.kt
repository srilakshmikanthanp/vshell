package com.srilakshmikanthanp.vshell.jvm.evaluator

import com.srilakshmikanthanp.vshell.parser.VshellAntlrParser
import com.srilakshmikanthanp.vshell.parser.VshellParser
import com.srilakshmikanthanp.vshell.parser.VshellSyntaxException
import com.srilakshmikanthanp.vshell.jvm.command.CommandBuilderRegistry
import com.srilakshmikanthanp.vshell.jvm.context.Context
import com.srilakshmikanthanp.vshell.jvm.executor.CommandsShellNode
import com.srilakshmikanthanp.vshell.jvm.executor.ExecutorVisitor
import com.srilakshmikanthanp.vshell.jvm.executor.substitution.StdOutSubstitutor
import org.apache.commons.io.input.CloseShieldInputStream
import org.apache.commons.io.output.CloseShieldOutputStream
import java.io.InputStream
import java.io.OutputStream

class VshellEvaluator(
  val commandBuilderRegistry: CommandBuilderRegistry,
  context: Context,
  stdIn: InputStream,
  stdOut: OutputStream,
  stdErr: OutputStream,
) {
  private val executorVisitor = ExecutorVisitor(context, StdOutSubstitutor(), commandBuilderRegistry)
  private val parser : VshellParser = VshellAntlrParser()
  private val stdOut = CloseShieldOutputStream.wrap(stdOut)
  private val stdIn = CloseShieldInputStream.wrap(stdIn)
  private val stdErr = CloseShieldOutputStream.wrap(stdErr)

  fun evaluate(input: String) {
    val tree = parser.parse(input)
    val node = tree.accept(executorVisitor) as? CommandsShellNode ?: throw VshellSyntaxException("$input is not a command")
    node.commands.forEach { it.execute(stdIn, stdOut, stdErr) }
  }
}
