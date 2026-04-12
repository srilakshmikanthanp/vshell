package com.srilakshmikanthanp.vshell.sample.commands

import com.srilakshmikanthanp.vshell.jvm.command.Command
import com.srilakshmikanthanp.vshell.jvm.command.CommandBuilder
import com.srilakshmikanthanp.vshell.jvm.command.CommandBuilderDescriptor
import com.srilakshmikanthanp.vshell.jvm.command.builtins.TextableCommand
import com.srilakshmikanthanp.vshell.jvm.command.builtins.exception.CommandException
import com.srilakshmikanthanp.vshell.jvm.context.Context
import com.srilakshmikanthanp.vshell.jvm.io.Input
import com.srilakshmikanthanp.vshell.jvm.io.Output

class CdCommand(private val context: Context, private val args: List<String>) : TextableCommand {
  override fun execute(stdIn: Input, stdOut: Output, stdErr: Output): Int {
    if (args.size != 1) {
      throw CommandException(1, "cd: expected exactly one argument")
    }

    val path = context.getCurrentWorkingDirectory().resolve(args[0]).normalize()

    if (!path.toFile().isDirectory) {
      throw CommandException(1, "cd: ${args[0]}: No such directory")
    } else {
      context.setCurrentWorkingDirectory(path)
    }

    return 0
  }

  @CommandBuilderDescriptor("cd")
  class CdCommandBuilder: CommandBuilder {
    override fun build(
      context: Context,
      args: List<String>
    ): Command {
      return CdCommand(context, args)
    }
  }
}
