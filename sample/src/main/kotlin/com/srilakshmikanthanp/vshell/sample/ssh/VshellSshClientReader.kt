package com.srilakshmikanthanp.vshell.sample.ssh

import com.srilakshmikanthanp.vshell.jvm.context.Context
import com.srilakshmikanthanp.vshell.jvm.shell.VshellEndOfFileException
import com.srilakshmikanthanp.vshell.jvm.shell.VshellInterruptException
import com.srilakshmikanthanp.vshell.jvm.shell.VshellReader
import org.jline.reader.EndOfFileException
import org.jline.reader.LineReader
import org.jline.reader.UserInterruptException

class VshellSshClientReader(private val username: String, private val hostname: String, private val reader: LineReader): VshellReader {
  override fun read(context: Context): String {
    try {
      return reader.readLine("$username@$hostname:${context.getCurrentWorkingDirectory()}> ")
    } catch (e: EndOfFileException) {
      throw VshellEndOfFileException(e.partialLine, "EOF", e)
    } catch (e: UserInterruptException) {
      throw VshellInterruptException(e.partialLine, "Interrupted", e)
    }
  }
}
