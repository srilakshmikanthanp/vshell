package com.srilakshmikanthanp.vshell.sample.commands

import com.srilakshmikanthanp.vshell.jvm.command.Command
import com.srilakshmikanthanp.vshell.jvm.command.CommandBuilder
import com.srilakshmikanthanp.vshell.jvm.command.CommandBuilderDescriptor
import com.srilakshmikanthanp.vshell.jvm.context.Context
import java.io.InputStream
import java.io.OutputStream

class ExitCommand(private val context: Context, private val args: List<String>) : Command {
  override fun execute(stdIn: InputStream, stdOut: OutputStream, stdErr: OutputStream): Int {
    throw ExitException("Exit Command has invoked.")
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