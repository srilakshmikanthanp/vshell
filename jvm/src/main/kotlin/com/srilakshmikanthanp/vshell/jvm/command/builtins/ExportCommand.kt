package com.srilakshmikanthanp.vshell.jvm.command.builtins

import com.srilakshmikanthanp.vshell.jvm.command.Command
import com.srilakshmikanthanp.vshell.jvm.command.CommandBuilder
import com.srilakshmikanthanp.vshell.jvm.command.CommandBuilderDescriptor
import com.srilakshmikanthanp.vshell.jvm.context.Context
import com.srilakshmikanthanp.vshell.jvm.context.ContextVariables

class ExportCommand(context: Context, args: List<String>) : VariableCommand(context, args) {
  override fun getContextVariables(): ContextVariables {
    return context.environmentVariables
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
