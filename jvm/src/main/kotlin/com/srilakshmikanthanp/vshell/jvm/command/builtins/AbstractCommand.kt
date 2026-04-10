package com.srilakshmikanthanp.vshell.jvm.command.builtins

import com.srilakshmikanthanp.vshell.jvm.command.Command
import java.io.InputStream
import java.io.InputStreamReader
import java.io.OutputStream
import java.io.PrintWriter

interface AbstractCommand: Command {
  override fun execute(stdIn: InputStream, stdOut: OutputStream, stdErr: OutputStream): Int {
    InputStreamReader(stdIn).use { stdInReader ->
      PrintWriter(stdOut).use { stdOutWriter ->
        PrintWriter(stdErr).use { stdErrWriter ->
          return execute(Input(stdIn, stdInReader), Output(stdOut, stdOutWriter), Output(stdErr, stdErrWriter))
        }
      }
    }
  }

  fun execute(stdIn: Input, stdOut: Output, stdErr: Output): Int
}
