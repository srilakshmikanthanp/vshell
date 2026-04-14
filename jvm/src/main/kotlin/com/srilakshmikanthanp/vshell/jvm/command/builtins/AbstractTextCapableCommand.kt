package com.srilakshmikanthanp.vshell.jvm.command.builtins

import com.srilakshmikanthanp.vshell.jvm.command.Command
import com.srilakshmikanthanp.vshell.jvm.command.builtins.exception.CommandException
import com.srilakshmikanthanp.vshell.jvm.io.Input
import com.srilakshmikanthanp.vshell.jvm.io.Output
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.io.OutputStream
import java.io.PrintWriter

interface AbstractTextCapableCommand: Command {
  public override fun execute(stdIn: InputStream, stdOut: OutputStream, stdErr: OutputStream): Int {
    BufferedReader(InputStreamReader(stdIn)).use { stdInReader ->
      PrintWriter(stdOut).use { stdOutWriter ->
        PrintWriter(stdErr).use { stdErrWriter ->
          return try {
            execute(
              stdOut = Output(stdOut, stdOutWriter),
              stdIn = Input(stdIn, stdInReader),
              stdErr = Output(stdErr, stdErrWriter)
            )
          } catch (e: CommandException) {
            stdErrWriter.println(e.message)
            e.exitCode
          }
        }
      }
    }
  }

  public abstract fun execute(stdIn: Input, stdOut: Output, stdErr: Output): Int
}
