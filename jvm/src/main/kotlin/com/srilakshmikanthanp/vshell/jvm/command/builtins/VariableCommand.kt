package com.srilakshmikanthanp.vshell.jvm.command.builtins

import com.srilakshmikanthanp.vshell.jvm.context.Context

abstract class VariableCommand(protected val context: Context, protected val args: List<String>) : TextCapableCommand {
  override fun execute(stdIn: Input, stdOut: Output, stdErr: Output): Int {
    if (args.isEmpty()) {
      getVariables().forEach { (key, value) -> stdOut.writer.println("$key=$value") }
      return 0
    }

    args.map { parse(it) }.forEach { (name, value) ->
      this.setVariable(name, value)
    }

    return 0
  }

  private fun parse(arg: String): Pair<String, String> {
    val parsed = arg.split('=').also { if (it.size != 2) throw CommandException(1, "Invalid variable arg '$arg'") }
    setVariable(parsed[0].trim(), parsed[1].trim())
    return Pair(parsed[0].trim(), parsed[1].trim())
  }

  abstract fun setVariable(name: String, value: String)
  abstract fun getVariables(): Map<String, String>
}
