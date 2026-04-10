package com.srilakshmikanthanp.vshell.sample.ssh

import com.srilakshmikanthanp.vshell.jvm.context.Context
import com.srilakshmikanthanp.vshell.jvm.runtime.VshellInterruptedException
import com.srilakshmikanthanp.vshell.jvm.runtime.VshellReader
import org.jline.builtins.ssh.Ssh.ShellParams
import org.jline.reader.EndOfFileException
import org.jline.reader.LineReaderBuilder
import org.jline.reader.UserInterruptException
import java.net.InetSocketAddress

class VshellSshClientReader(val params: ShellParams): VshellReader {
  private val reader = LineReaderBuilder.builder().terminal(params.terminal).build()

  override fun read(context: Context): String {
    try {
      val hostname = (params.session.localAddress as? InetSocketAddress)?.hostString ?: params.session.localAddress.toString()
      val userinfo = "${params.session.username}@${hostname}"
      return reader.readLine("$userinfo:${context.getCurrentWorkingDirectory()}> ")
    } catch (e: UserInterruptException) {
      throw VshellInterruptedException("Interrupted", e)
    } catch (e: EndOfFileException) {
      throw VshellInterruptedException("End of file", e)
    }
  }
}