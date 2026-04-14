package com.srilakshmikanthanp.vshell.sample.commands

import com.srilakshmikanthanp.vshell.jvm.command.Command
import com.srilakshmikanthanp.vshell.jvm.command.CommandBuilder
import com.srilakshmikanthanp.vshell.jvm.command.CommandBuilderDescriptor
import com.srilakshmikanthanp.vshell.jvm.command.builtins.AbstractTextCapableCommand
import com.srilakshmikanthanp.vshell.jvm.command.builtins.exception.CommandException
import com.srilakshmikanthanp.vshell.jvm.context.Context
import com.srilakshmikanthanp.vshell.jvm.io.Input
import com.srilakshmikanthanp.vshell.jvm.io.Output

class LsCommand(private val context: Context, private val args: List<String>) : AbstractTextCapableCommand {
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
