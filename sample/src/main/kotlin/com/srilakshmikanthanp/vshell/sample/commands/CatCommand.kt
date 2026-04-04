package com.srilakshmikanthanp.vshell.sample.commands

import com.srilakshmikanthanp.vshell.jvm.command.Command
import com.srilakshmikanthanp.vshell.jvm.command.CommandBuilder
import com.srilakshmikanthanp.vshell.jvm.command.CommandBuilderDescriptor
import com.srilakshmikanthanp.vshell.jvm.context.Context
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.nio.file.Files
import kotlin.io.path.Path
import kotlin.io.path.exists
import kotlin.io.path.isRegularFile

class CatCommand(private val context: Context, private val args: List<String>): Command {
  override fun execute(stdIn: InputStream, stdOut: OutputStream, stdErr: OutputStream): Int {
    try {
      args.stream().map { context.getCurrentWorkingDirectory().resolve(Path(it.trim())) }.forEach {
        if (!it.exists()) {
          stdOut.write("cat: ${it.fileName}: No such file\n".toByteArray())
        } else if (!it.isRegularFile()) {
          stdOut.write("cat: ${it.fileName}: Not a file\n".toByteArray())
        } else {
          stdOut.write(Files.readAllBytes(it))
        }
      }
      return 0
    } catch (e: IOException) {
      return -1
    }
  }

  @CommandBuilderDescriptor(command = "cat")
  class CatCommandBuilder: CommandBuilder {
    override fun build(
      context: Context,
      args: List<String>
    ): Command {
      return CatCommand(context, args)
    }
  }
}
