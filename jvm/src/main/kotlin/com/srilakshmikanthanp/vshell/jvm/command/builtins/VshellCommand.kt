package com.srilakshmikanthanp.vshell.jvm.command.builtins

import com.srilakshmikanthanp.vshell.jvm.command.Command
import com.srilakshmikanthanp.vshell.jvm.command.CommandBuilder
import com.srilakshmikanthanp.vshell.jvm.command.CommandBuilderDescriptor
import com.srilakshmikanthanp.vshell.jvm.command.CommandBuilderRegistry
import com.srilakshmikanthanp.vshell.jvm.context.Context
import com.srilakshmikanthanp.vshell.jvm.event.EventScope
import com.srilakshmikanthanp.vshell.jvm.shell.VshellReader
import com.srilakshmikanthanp.vshell.jvm.shell.VshellRepl
import java.io.InputStream
import java.io.OutputStream

class VshellCommand(
  private val commandBuilderRegistry: CommandBuilderRegistry,
  private val reader: VshellReader,
  private val context: Context,
  private val eventScope: EventScope,
  private val args: List<String>
): Command {
  override fun execute(stdIn: InputStream, stdOut: OutputStream, stdErr: OutputStream): Int {
    eventScope.enterScope().use {
      VshellRepl(commandBuilderRegistry, reader, context, stdIn, stdOut, stdErr).run()
      return 0
    }
  }

  @CommandBuilderDescriptor("vshell")
  class VshellCommandBuilder(
    private val commandBuilderRegistry: CommandBuilderRegistry,
    private val reader: VshellReader,
    private val eventScope: EventScope
  ) : CommandBuilder {
    override fun build(context: Context, args: List<String>): Command {
      return VshellCommand(commandBuilderRegistry, this.reader, Context.withParentContext(context), eventScope, args)
    }
  }
}
