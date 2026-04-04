package com.srilakshmikanthanp.vshell.sample.commands

import com.srilakshmikanthanp.vshell.jvm.command.Command
import com.srilakshmikanthanp.vshell.jvm.context.Context
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.nio.file.Files
import kotlin.io.path.Path
import kotlin.io.path.exists

class CatCommand(private val context: Context, private val args: List<String>): Command {
  override fun execute(
    stdIn: InputStream,
    stdOut: OutputStream,
    stdErr: OutputStream
  ): Int {
    try {
      args.stream().map { context.getCurrentWorkingDirectory().resolve(Path(it.trim())) }.forEach {
        stdOut.write(if (it.exists()) Files.readAllBytes(it) else "cat: ${it.fileName}: No such file\n".toByteArray())
      }
      return 0
    } catch (e: IOException) {
      return -1
    }
  }
}
