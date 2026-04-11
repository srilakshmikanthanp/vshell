package com.srilakshmikanthanp.vshell.sample.commands

import com.srilakshmikanthanp.vshell.jvm.command.Command
import com.srilakshmikanthanp.vshell.jvm.command.CommandBuilder
import com.srilakshmikanthanp.vshell.jvm.command.CommandBuilderDescriptor
import com.srilakshmikanthanp.vshell.jvm.command.builtins.CommandException
import com.srilakshmikanthanp.vshell.jvm.command.builtins.Input
import com.srilakshmikanthanp.vshell.jvm.command.builtins.Output
import com.srilakshmikanthanp.vshell.jvm.command.builtins.TextCapableCommand
import com.srilakshmikanthanp.vshell.jvm.context.Context
import java.nio.file.Files
import kotlin.io.path.Path
import kotlin.io.path.exists
import kotlin.io.path.isRegularFile

class CatCommand(private val context: Context, private val args: List<String>): TextCapableCommand {
  override fun execute(
    stdIn: Input,
    stdOut: Output,
    stdErr: Output
  ): Int {
    args.stream().map { context.getCurrentWorkingDirectory().resolve(Path(it.trim())) }.forEach {
      if (!it.exists()) {
        throw CommandException(1, "cat: ${it.fileName}: No such file or directory")
      } else if (!it.isRegularFile()) {
        throw CommandException(1, "cat: ${it.fileName}: Is a directory")
      } else {
        stdOut.output.write(Files.readAllBytes(it))
      }
    }
    return 0
  }

  @CommandBuilderDescriptor(command = "cat")
  class CatCommandBuilder: CommandBuilder {
    override fun build(
      context: Context,
      args: List<String>
    ): Command {
      return CatCommand(context, args)
    }
  }
}
