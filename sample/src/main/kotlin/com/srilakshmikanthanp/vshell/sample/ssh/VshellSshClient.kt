package com.srilakshmikanthanp.vshell.sample.ssh

import com.srilakshmikanthanp.vshell.jvm.command.CommandBuilderMapRegistry
import com.srilakshmikanthanp.vshell.jvm.command.builtins.ExitCommand
import com.srilakshmikanthanp.vshell.jvm.context.Context
import com.srilakshmikanthanp.vshell.jvm.event.Event
import com.srilakshmikanthanp.vshell.jvm.event.SimpleEventSource
import com.srilakshmikanthanp.vshell.jvm.runtime.VshellRuntime
import com.srilakshmikanthanp.vshell.sample.commands.*
import org.jline.builtins.ssh.Ssh.ShellParams
import org.jline.reader.LineReaderBuilder
import org.jline.terminal.Terminal.Signal
import java.nio.file.Path

class VshellSshClient(private val params: ShellParams): Runnable {
  private val context = Context(Path.of("/home/${params.session.username}"), CommandBuilderMapRegistry(), SimpleEventSource())
  private val runtime = VshellRuntime(context, VshellSshClientReader(params), params.terminal.input(), params.terminal.output(), params.terminal.output())

  init {
    params.terminal.handle(Signal.QUIT) {
      context.eventSource.dispatch(Event.TERMINATE_EVENT)
    }

    params.terminal.handle(Signal.INT) {
      context.eventSource.dispatch(Event.INTERRUPT_EVENT)
    }

    params.env.forEach { (key, value) ->
      context.setEnvironmentVariable(key, value)
    }

    listOf(
      EchoCommand.EchoCommandBuilder(),
      CatCommand.CatCommandBuilder(),
      CdCommand.CdCommandBuilder(),
      LsCommand.LsCommandBuilder(),
      ExitCommand.ExitCommandBuilder(),
      ExportCommand.ExportCommandBuilder(),
      SetCommand.SetCommandBuilder()
    ).forEach {
      context.commandBuilderRegistry.register(it)
    }
  }

  override fun run() {
    try {
      this.runtime.run()
    } finally {
      params.session.close()
    }
  }
}
