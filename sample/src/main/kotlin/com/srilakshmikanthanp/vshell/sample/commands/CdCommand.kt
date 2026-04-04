package com.srilakshmikanthanp.vshell.sample.commands

import com.srilakshmikanthanp.vshell.jvm.command.Command
import com.srilakshmikanthanp.vshell.jvm.context.Context
import java.io.InputStream
import java.io.OutputStream
import java.io.PrintWriter

class CdCommand(private val context: Context, private val args: List<String>) : Command {
  override fun execute(
    stdIn: InputStream,
    stdOut: OutputStream,
    stdErr: OutputStream
  ): Int {
    PrintWriter(stdErr).use { err ->
      if (args.size != 1) {
        err.println("cd: expected exactly one argument")
        return 1
      }

      val path = context.getCurrentWorkingDirectory().resolve(args[0]).normalize()

      if (!path.toFile().isDirectory) {
        err.println("cd: ${args[0]}: No such directory")
        return 1
      }

      context.setCurrentWorkingDirectory(path)
      return 0
    }
  }
}
