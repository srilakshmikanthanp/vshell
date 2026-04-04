package com.srilakshmikanthanp.vshell.sample.commands

import com.srilakshmikanthanp.vshell.jvm.command.Command
import com.srilakshmikanthanp.vshell.jvm.context.Context
import java.io.InputStream
import java.io.OutputStream

class EchoCommand(private val context: Context, private val args: List<String>) : Command {
  override fun execute(
    stdIn: InputStream,
    stdOut: OutputStream,
    stdErr: OutputStream
  ): Int {
    stdOut.write(args.joinToString(" ", postfix = "\n").toByteArray())
    return 0
  }
}
