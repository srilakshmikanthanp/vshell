package com.srilakshmikanthanp.vshell.sample.commands

import com.srilakshmikanthanp.vshell.jvm.command.Command
import com.srilakshmikanthanp.vshell.jvm.context.Context
import com.srilakshmikanthanp.vshell.jvm.event.Event
import java.io.InputStream
import java.io.OutputStream
import java.util.concurrent.Executors

class LsCommand(private val context: Context, private val args: List<String>) : Command {
  override fun execute(stdIn: InputStream, stdOut: OutputStream, stdErr: OutputStream): Int {
    val process = ProcessBuilder(mutableListOf("ls") + args)
        .directory(context.getCurrentWorkingDirectory().toFile())
        .start()

    context.getEventSource().subscribe {
      when (it) {
        Event.TERMINATE_EVENT -> process.destroyForcibly()
        Event.INTERRUPT_EVENT -> process.destroy()
      }
    }.use {
      Executors.newVirtualThreadPerTaskExecutor().use { executor ->
        val stdErrTransfer = executor.submit { process.errorStream.transferTo(stdErr) }
        val stdOutTransfer = executor.submit { process.inputStream.transferTo(stdOut) }
        stdOutTransfer.get()
        stdErrTransfer.get()
        process.waitFor()
        return process.exitValue()
      }
    }
  }
}
