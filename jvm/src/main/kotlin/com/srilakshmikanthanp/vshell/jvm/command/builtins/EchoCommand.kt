package com.srilakshmikanthanp.vshell.jvm.command.builtins

import com.srilakshmikanthanp.vshell.jvm.command.Command
import com.srilakshmikanthanp.vshell.jvm.command.CommandBuilder
import com.srilakshmikanthanp.vshell.jvm.command.CommandBuilderDescriptor
import com.srilakshmikanthanp.vshell.jvm.context.Context
import com.srilakshmikanthanp.vshell.jvm.io.Input
import com.srilakshmikanthanp.vshell.jvm.io.Output

class EchoCommand(private val context: Context, private val args: List<String>) : AbstractTextCapableCommand {
  override fun execute(stdIn: Input, stdOut: Output, stdErr: Output): Int {
    stdOut.writer.println(args.joinToString(" "))
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
