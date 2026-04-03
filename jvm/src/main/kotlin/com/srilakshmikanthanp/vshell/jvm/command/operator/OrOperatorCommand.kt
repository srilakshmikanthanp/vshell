package com.srilakshmikanthanp.vshell.jvm.command.operator

import com.srilakshmikanthanp.vshell.jvm.command.Command
import java.io.InputStream
import java.io.OutputStream

class OrOperatorCommand(val left: Command, val right: Command) : Command {
  override fun execute(
    stdIn: InputStream,
    stdOut: OutputStream,
    stdErr: OutputStream
  ): Int {
    val leftExitCode = left.execute(stdIn, stdOut, stdErr)
    if (leftExitCode == 0) return 0
    return right.execute(stdIn, stdOut, stdErr)
  }
}
