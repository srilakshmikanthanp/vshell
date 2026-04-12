package com.srilakshmikanthanp.vshell.sample.reader

import com.srilakshmikanthanp.vshell.jvm.context.Context
import com.srilakshmikanthanp.vshell.jvm.shell.VshellReader
import org.jline.reader.LineReader

class VshellSshReader(private val username: String, private val hostname: String, private val reader: LineReader): VshellReader {
  override fun read(context: Context): String {
    return reader.readLine("$$username@$hostname:${context.getCurrentWorkingDirectory()}> ")
  }
}
