package com.srilakshmikanthanp.vshell.jvm.executor.substitution

import com.srilakshmikanthanp.vshell.jvm.command.Command
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.io.OutputStream

class StdOutSubstitutor : CommandSubstitutor {
  override fun substitute(command: Command): String {
    val stdOut = ByteArrayOutputStream()
    command.execute(InputStream.nullInputStream(), stdOut, OutputStream.nullOutputStream())
    return stdOut.toString()
  }
}
