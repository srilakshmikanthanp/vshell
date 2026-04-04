package com.srilakshmikanthanp.vshell.sample.commands

import com.srilakshmikanthanp.vshell.jvm.command.Command
import com.srilakshmikanthanp.vshell.jvm.command.CommandBuilder
import com.srilakshmikanthanp.vshell.jvm.command.CommandBuilderDescriptor
import com.srilakshmikanthanp.vshell.jvm.context.Context

class SetCommand(context: Context, args: List<String>) : VarCommand(context, args) {
  override fun setVariable(name: String, value: String) {
    context.setVariable(name, value)
  }

  override fun getVariables(): Map<String, String> {
    return context.getAllVariables()
  }

  @CommandBuilderDescriptor("set")
  class SetCommandBuilder : CommandBuilder {
    override fun build(
      context: Context,
      args: List<String>
    ): Command {
      return SetCommand(context, args)
    }
  }
}
