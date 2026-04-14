package com.srilakshmikanthanp.vshell.jvm.command.builtins

import com.srilakshmikanthanp.vshell.jvm.command.Command
import com.srilakshmikanthanp.vshell.jvm.command.CommandBuilder
import com.srilakshmikanthanp.vshell.jvm.command.CommandBuilderDescriptor
import com.srilakshmikanthanp.vshell.jvm.command.builtins.exception.CommandException
import com.srilakshmikanthanp.vshell.jvm.command.builtins.exception.ExitException
import com.srilakshmikanthanp.vshell.jvm.context.Context
import com.srilakshmikanthanp.vshell.jvm.io.Input
import com.srilakshmikanthanp.vshell.jvm.io.Output

class ExitCommand(private val context: Context, private val args: List<String>) : AbstractTextCapableCommand {
  override fun execute(stdIn: Input, stdOut: Output, stdErr: Output): Int {
    val exitCode = when(args.size) {
      1 -> args[0].toIntOrNull() ?: throw CommandException(-1, "exit: ${args[0]}: numeric argument required")
      0 -> 0
      else -> throw CommandException(1, "exit: too many arguments")
    }
    throw ExitException(exitCode, "Bye!")
  }

  @CommandBuilderDescriptor("exit", aliases = ["quit"])
  class ExitCommandBuilder: CommandBuilder {
    override fun build(
      context: Context,
      args: List<String>
    ): Command {
      return ExitCommand(context, args)
    }
  }
}
