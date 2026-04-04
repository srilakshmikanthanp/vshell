package com.srilakshmikanthanp.vshell.sample.ssh

import com.srilakshmikanthanp.vshell.parser.VshellSyntaxException
import com.srilakshmikanthanp.vshell.jvm.command.CommandBuilderMapRegistry
import com.srilakshmikanthanp.vshell.jvm.context.Context
import com.srilakshmikanthanp.vshell.jvm.evaluator.VshellEvaluator
import com.srilakshmikanthanp.vshell.jvm.event.Event
import com.srilakshmikanthanp.vshell.jvm.event.SimpleEventSource
import com.srilakshmikanthanp.vshell.jvm.executor.ExecutorCommandNotFoundException
import com.srilakshmikanthanp.vshell.jvm.executor.ExecutorException
import com.srilakshmikanthanp.vshell.sample.commands.CatCommandBuilder
import com.srilakshmikanthanp.vshell.sample.commands.CdCommandBuilder
import com.srilakshmikanthanp.vshell.sample.commands.EchoCommandBuilder
import com.srilakshmikanthanp.vshell.sample.commands.ExitCommandBuilder
import com.srilakshmikanthanp.vshell.sample.commands.LsCommandBuilder
import org.jline.builtins.ssh.Ssh.ShellParams
import org.jline.reader.EndOfFileException
import org.jline.reader.LineReaderBuilder
import org.jline.reader.UserInterruptException
import org.jline.terminal.Terminal.Signal
import java.net.InetSocketAddress
import java.nio.file.Path

class VshellSshClient(private val params: ShellParams): Runnable {
  private val commandBuilderRegistry: CommandBuilderMapRegistry = CommandBuilderMapRegistry()
  private val context = Context(Path.of("/home/${params.session.username}"), SimpleEventSource())
  private val evaluator = VshellEvaluator(commandBuilderRegistry, context, params.terminal.input(), params.terminal.output(), params.terminal.output())
  private val reader = LineReaderBuilder.builder().terminal(params.terminal).build()

  init {
    params.terminal.handle(Signal.QUIT) {
      context.getEventSource().dispatch(Event.TERMINATE_EVENT)
    }

    params.terminal.handle(Signal.INT) {
      context.getEventSource().dispatch(Event.INTERRUPT_EVENT)
    }

    params.env.forEach { (key, value) ->
      context.setEnvironmentVariable(key, value)
    }

    listOf(
      CatCommandBuilder(),
      LsCommandBuilder(),
      CdCommandBuilder(),
      EchoCommandBuilder(),
      ExitCommandBuilder(),
    ).forEach {
      commandBuilderRegistry.register(it)
    }
  }

  private fun evaluate() {
    try {
      val hostname = (params.session.localAddress as? InetSocketAddress)?.hostString ?: params.session.localAddress.toString()
      val userinfo = "${params.session.username}@${hostname}"
      val line = reader.readLine("$userinfo:${context.getCurrentWorkingDirectory()}> ")
      evaluator.evaluate(line)
    } catch (e: UserInterruptException) {
      params.terminal.writer().println("^C")
    } catch (e: ExecutorCommandNotFoundException) {
      params.terminal.writer().println("No such command ${e.command}")
    } catch (e: ExecutorException) {
      params.terminal.writer().println("Error executing command: ${e.message}")
    } catch (e: VshellSyntaxException) {
      params.terminal.writer().println("Syntax error: ${e.message}")
    }
  }

  private fun loop() {
    while (true) {
      try {
        evaluate()
      } catch (e: EndOfFileException) {
        params.terminal.writer().println("^D")
        params.terminal.writer().flush()
        break
      } catch (e: Exception) {
        params.terminal.writer().println("Error: ${e.message}")
      }
    }
  }

  override fun run() {
    try {
      this.loop()
    } finally {
      params.session.close()
    }
  }
}
