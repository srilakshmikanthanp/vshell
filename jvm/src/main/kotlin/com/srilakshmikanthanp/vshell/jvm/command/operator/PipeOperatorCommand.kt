package com.srilakshmikanthanp.vshell.jvm.command.operator

import com.srilakshmikanthanp.vshell.jvm.command.Command
import java.io.InputStream
import java.io.OutputStream
import java.io.PipedInputStream
import java.io.PipedOutputStream
import java.util.concurrent.Executors

class PipeOperatorCommand(val left: Command, val right: Command) : Command {
  override fun execute(stdIn: InputStream, stdOut: OutputStream, stdErr: OutputStream): Int {
    val pipedOutput = PipedOutputStream()
    val pipedInput = PipedInputStream(pipedOutput, 64 * 1024)

    Executors.newVirtualThreadPerTaskExecutor().use { executor ->
      val leftFuture = executor.submit<Int> {
        pipedOutput.use { pipedOutput ->
          left.execute(stdIn, pipedOutput, stdErr)
        }
      }

      val rightFuture = executor.submit<Int> {
        pipedInput.use { pipedInput ->
          right.execute(pipedInput, stdOut, stdErr)
        }
      }

      val leftExit = leftFuture.get()
      val rightExit = rightFuture.get()

      return if (leftExit != 0) leftExit else rightExit
    }
  }
}
