package com.srilakshmikanthanp.vshell.sample.commands

import com.srilakshmikanthanp.vshell.jvm.command.Command
import com.srilakshmikanthanp.vshell.jvm.command.CommandBuilder
import com.srilakshmikanthanp.vshell.jvm.command.CommandBuilderDescriptor
import com.srilakshmikanthanp.vshell.jvm.context.Context
import java.io.InputStream
import java.io.OutputStream
import java.io.PrintWriter

class EchoCommand(private val context: Context, private val args: List<String>) : Command {
  override fun execute(stdIn: InputStream, stdOut: OutputStream, stdErr: OutputStream): Int {
    PrintWriter(stdOut).use { out ->
      out.println(args.joinToString(" "))
    }
    return 0
  }

  @CommandBuilderDescriptor("echo")
  class EchoCommandBuilder: CommandBuilder {
    override fun build(
      context: Context,
      args: List<String>
    ): Command {
      return EchoCommand(context, args)
    }
  }
}
