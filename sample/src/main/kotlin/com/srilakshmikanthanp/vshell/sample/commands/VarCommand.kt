package com.srilakshmikanthanp.vshell.sample.commands

import com.srilakshmikanthanp.vshell.jvm.command.builtins.CommandException
import com.srilakshmikanthanp.vshell.jvm.command.builtins.Input
import com.srilakshmikanthanp.vshell.jvm.command.builtins.Output
import com.srilakshmikanthanp.vshell.jvm.command.builtins.TextCapableCommand
import com.srilakshmikanthanp.vshell.jvm.context.Context

abstract class VarCommand(protected val context: Context, protected val args: List<String>) : TextCapableCommand {
  override fun execute(stdIn: Input, stdOut: Output, stdErr: Output): Int {
    if (args.isEmpty()) {
      getVariables().forEach { (key, value) -> stdOut.writer.println("$key=$value") }
      return 0
    }

    for(arg in args) {
      val nameValue = arg.split('=')
      if (nameValue.size != 2) {
        throw CommandException(1, "var: invalid variable assignment: $arg")
      }
      val name = nameValue[0].trim()
      val value = nameValue[1].trim()
      setVariable(name, value)
    }

    return 0
  }

  abstract fun setVariable(name: String, value: String)
  abstract fun getVariables(): Map<String, String>
}
