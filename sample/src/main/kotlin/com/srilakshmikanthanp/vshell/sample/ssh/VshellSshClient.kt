package com.srilakshmikanthanp.vshell.sample.ssh

import com.srilakshmikanthanp.vshell.jvm.command.CommandBuilderMapRegistry
import com.srilakshmikanthanp.vshell.jvm.command.builtins.EchoCommand
import com.srilakshmikanthanp.vshell.jvm.command.builtins.ExitCommand
import com.srilakshmikanthanp.vshell.jvm.command.builtins.ExportCommand
import com.srilakshmikanthanp.vshell.jvm.command.builtins.SetCommand
import com.srilakshmikanthanp.vshell.jvm.command.builtins.VshellCommand
import com.srilakshmikanthanp.vshell.jvm.context.Context
import com.srilakshmikanthanp.vshell.jvm.event.Event
import com.srilakshmikanthanp.vshell.jvm.event.SimpleEventBus
import com.srilakshmikanthanp.vshell.sample.commands.*
import org.jline.builtins.ssh.Ssh.ShellParams
import org.jline.reader.LineReaderBuilder
import org.jline.terminal.Terminal.Signal
import java.net.InetSocketAddress
import java.nio.file.Path

class VshellSshClient(private val params: ShellParams): Runnable {
  private val context = Context(Path.of("/home/${params.session.username}"))
  private val commandBuilderRegistry =  CommandBuilderMapRegistry()
  private val eventBus =  SimpleEventBus()
  private val hostname = (params.session.localAddress as? InetSocketAddress)?.hostString ?: params.session.localAddress.toString()
  private val reader = VshellSshClientReader(params.session.username, hostname, LineReaderBuilder.builder().terminal(params.terminal).build())

  init {
    params.terminal.handle(Signal.QUIT) {
      eventBus.dispatch(Event.TERMINATE_EVENT)
    }

    params.terminal.handle(Signal.INT) {
      eventBus.dispatch(Event.INTERRUPT_EVENT)
    }

    mapOf(
      "USERNAME" to params.session.username,
      "HOSTNAME" to hostname,
    ).forEach { (key, value) ->
      context.setEnvironmentVariable(key, value)
    }

    params.env.forEach { (key, value) ->
      context.setEnvironmentVariable(key, value)
    }

    listOf(
      VshellCommand.VshellCommandBuilder(commandBuilderRegistry, reader, eventBus),
      EchoCommand.EchoCommandBuilder(),
      CatCommand.CatCommandBuilder(),
      CdCommand.CdCommandBuilder(),
      LsCommand.LsCommandBuilder(),
      ExitCommand.ExitCommandBuilder(),
      ExportCommand.ExportCommandBuilder(),
      SetCommand.SetCommandBuilder(),
    ).forEach {
      commandBuilderRegistry.register(it)
    }
  }

  override fun run() {
    try {
      val command = VshellCommand(commandBuilderRegistry, reader, context, eventBus, listOf())
      val stdOut = params.terminal.output()
      val stdIn = params.terminal.input()
      val stdErr = params.terminal.output()
      command.execute(stdIn, stdOut, stdErr)
    } finally {
      params.session.close()
    }
  }
}
