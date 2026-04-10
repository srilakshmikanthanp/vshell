package com.srilakshmikanthanp.vshell.jvm.command.builtins

import com.srilakshmikanthanp.vshell.jvm.command.Command
import com.srilakshmikanthanp.vshell.jvm.command.CommandBuilder
import com.srilakshmikanthanp.vshell.jvm.command.CommandBuilderDescriptor
import com.srilakshmikanthanp.vshell.jvm.context.Context

class ExitCommand(private val context: Context, private val args: List<String>) : AbstractCommand {
  override fun execute(stdIn: Input, stdOut: Output, stdErr: Output): Int {
    try {
      val exitCode = when(args.size) {
        1 -> args[0].toInt()
        0 -> 0
        else -> throw InvalidCommandArgumentException(1, "exit: too many arguments")
      }
      throw ExitException(exitCode)
    } catch (e: InvalidCommandArgumentException) {
      stdErr.writer.println(e.message)
      return e.exitCode
    } catch (e: NumberFormatException) {
      stdErr.writer.println("exit: ${args[0]}: numeric argument required")
      return -1
    }
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
