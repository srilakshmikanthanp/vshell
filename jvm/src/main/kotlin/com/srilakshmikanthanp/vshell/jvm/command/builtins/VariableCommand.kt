package com.srilakshmikanthanp.vshell.jvm.command.builtins

import com.srilakshmikanthanp.vshell.jvm.command.builtins.exception.CommandException
import com.srilakshmikanthanp.vshell.jvm.context.Context
import com.srilakshmikanthanp.vshell.jvm.context.ContextVariables
import com.srilakshmikanthanp.vshell.jvm.io.Input
import com.srilakshmikanthanp.vshell.jvm.io.Output

abstract class VariableCommand(protected val context: Context, protected val args: List<String>) : AbstractTextCapableCommand {
  private val variableNameRegex = Regex("^[a-zA-Z_][a-zA-Z0-9_]*$")

  override fun execute(stdIn: Input, stdOut: Output, stdErr: Output): Int {
    if (args.isEmpty()) {
      this.getContextVariables().getAll().forEach { (key, value) -> stdOut.writer.println("$key=${value.get()}") }
    } else {
      args.map { parse(it) }.forEach { (name, value) -> this.getContextVariables().set(name) { value } }
    }
    return 0
  }

  private fun parse(arg: String): Pair<String, String> {
    val parsed = arg.split('=')

    if (parsed.size != 2) {
      throw CommandException(1, "Invalid variable arg '$arg'")
    }

    val key = parsed[0]
    val value = parsed[1]

    if (!key.matches(variableNameRegex)) {
      throw CommandException(1, "Invalid variable name '$key'")
    }

    return key to value
  }

  abstract fun getContextVariables(): ContextVariables
}
