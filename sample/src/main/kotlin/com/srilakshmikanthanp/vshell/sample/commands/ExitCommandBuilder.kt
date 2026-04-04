package com.srilakshmikanthanp.vshell.sample.commands

import com.srilakshmikanthanp.vshell.jvm.command.Command
import com.srilakshmikanthanp.vshell.jvm.command.CommandBuilder
import com.srilakshmikanthanp.vshell.jvm.command.CommandBuilderDescriptor
import com.srilakshmikanthanp.vshell.jvm.context.Context

@CommandBuilderDescriptor("exit", aliases = ["quit"])
class ExitCommandBuilder: CommandBuilder {
  override fun build(
    context: Context,
    args: List<String>
  ): Command {
    return ExitCommand(context, args)
  }
}
