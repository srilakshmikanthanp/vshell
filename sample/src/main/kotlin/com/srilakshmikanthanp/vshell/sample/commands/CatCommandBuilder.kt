package com.srilakshmikanthanp.vshell.sample.commands

import com.srilakshmikanthanp.vshell.jvm.command.Command
import com.srilakshmikanthanp.vshell.jvm.command.CommandBuilder
import com.srilakshmikanthanp.vshell.jvm.command.CommandBuilderDescriptor
import com.srilakshmikanthanp.vshell.jvm.context.Context

@CommandBuilderDescriptor(command = "cat")
class CatCommandBuilder: CommandBuilder {
  override fun build(
    context: Context,
    args: List<String>
  ): Command {
    return CatCommand(context, args)
  }
}
