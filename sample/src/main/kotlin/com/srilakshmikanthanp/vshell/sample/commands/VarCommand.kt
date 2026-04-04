package com.srilakshmikanthanp.vshell.sample.commands

import com.srilakshmikanthanp.vshell.jvm.command.Command
import com.srilakshmikanthanp.vshell.jvm.context.Context
import java.io.InputStream
import java.io.OutputStream
import java.io.PrintWriter

abstract class VarCommand(protected val context: Context, protected val args: List<String>) : Command {
  override fun execute(stdIn: InputStream, stdOut: OutputStream, stdErr: OutputStream): Int {
    PrintWriter(stdErr).use { err ->
      args.forEach {
        val nameValue = it.split('=')
        if (nameValue.size != 2) {
          err.println("Invalid argument: $it. Expected format: name=value")
          return -1
        }
        val name = nameValue[0].trim()
        val value = nameValue[1].trim()
        setVariable(name, value)
        return 0
      }
    }
    PrintWriter(stdOut).use { out ->
      getVariables().forEach { (key, value) ->
        out.println("$key=$value")
      }
      return 0
    }
  }

  abstract fun setVariable(name: String, value: String)
  abstract fun getVariables(): Map<String, String>
}
