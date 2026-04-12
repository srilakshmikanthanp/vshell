package com.srilakshmikanthanp.vshell.jvm.command.builtins

import com.srilakshmikanthanp.vshell.jvm.command.Command
import com.srilakshmikanthanp.vshell.jvm.command.CommandBuilder
import com.srilakshmikanthanp.vshell.jvm.command.CommandBuilderDescriptor
import com.srilakshmikanthanp.vshell.jvm.context.Context

class ExportCommand(context: Context, args: List<String>) : VariableCommand(context, args) {
  override fun setVariable(name: String, value: String) {
    context.setEnvironmentVariable(name, value)
  }

  override fun getVariables(): Map<String, String> {
    return context.getAllEnvironmentVariables()
  }

  @CommandBuilderDescriptor("export", aliases = ["EXPORT"])
  class ExportCommandBuilder : CommandBuilder {
    override fun build(
      context: Context,
      args: List<String>
    ): Command {
      return ExportCommand(context, args)
    }
  }
}
