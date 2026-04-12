package com.srilakshmikanthanp.vshell.jvm.command.builtins

import com.srilakshmikanthanp.vshell.jvm.command.Command
import com.srilakshmikanthanp.vshell.jvm.command.CommandBuilder
import com.srilakshmikanthanp.vshell.jvm.command.CommandBuilderDescriptor
import com.srilakshmikanthanp.vshell.jvm.context.Context
import com.srilakshmikanthanp.vshell.jvm.shell.VshellRepl
import com.srilakshmikanthanp.vshell.jvm.shell.VshellReader
import java.io.InputStream
import java.io.OutputStream

class VshellCommand(private val reader: VshellReader, private val context: Context, private val args: List<String>): Command {
  override fun execute(stdIn: InputStream, stdOut: OutputStream, stdErr: OutputStream): Int {
    context.eventSource.enterScope().use {
      VshellRepl(reader, context, stdIn, stdOut, stdErr).run()
      return 0
    }
  }

  @CommandBuilderDescriptor("vshell")
  class VshellCommandBuilder(private val reader: VshellReader) : CommandBuilder {
    override fun build(context: Context, args: List<String>): Command {
      return VshellCommand(this.reader, Context.withParentContext(context), args)
    }
  }
}
