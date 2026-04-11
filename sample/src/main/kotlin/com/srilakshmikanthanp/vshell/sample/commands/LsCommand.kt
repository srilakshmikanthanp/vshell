package com.srilakshmikanthanp.vshell.sample.commands

import com.srilakshmikanthanp.vshell.jvm.command.Command
import com.srilakshmikanthanp.vshell.jvm.command.CommandBuilder
import com.srilakshmikanthanp.vshell.jvm.command.CommandBuilderDescriptor
import com.srilakshmikanthanp.vshell.jvm.command.builtins.CommandException
import com.srilakshmikanthanp.vshell.jvm.command.builtins.Input
import com.srilakshmikanthanp.vshell.jvm.command.builtins.Output
import com.srilakshmikanthanp.vshell.jvm.command.builtins.TextCapableCommand
import com.srilakshmikanthanp.vshell.jvm.context.Context

class LsCommand(private val context: Context, private val args: List<String>) : TextCapableCommand {
  override fun execute(stdIn: Input, stdOut: Output, stdErr: Output): Int {
    val path = if (args.isEmpty()) {
      context.getCurrentWorkingDirectory()
    } else {
      context.getCurrentWorkingDirectory().resolve(args[0]).normalize()
    }

    if (!path.toFile().isDirectory) {
      stdErr.writer.println("ls: ${args[0]}: No such directory")
      return 1
    }

    val files = path.toFile().listFiles() ?: throw CommandException(1, "ls: ${args[0]}: Unable to list directory")
    files.sortedBy { it.name }.forEach { stdOut.writer.println(it.name) }

    return 0
  }

  @CommandBuilderDescriptor("ls", ["dir"])
  class LsCommandBuilder: CommandBuilder {
    override fun build(
      context: Context,
      args: List<String>
    ): Command {
      return LsCommand(context, args)
    }
  }
}
