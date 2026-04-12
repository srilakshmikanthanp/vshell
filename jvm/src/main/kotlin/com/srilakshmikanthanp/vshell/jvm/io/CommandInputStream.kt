package com.srilakshmikanthanp.vshell.jvm.io

import org.apache.commons.io.input.ClosedInputStream
import org.apache.commons.io.input.ProxyInputStream
import java.io.InputStream

class CommandInputStream private constructor(stream: InputStream) : ProxyInputStream(stream) {
  override fun read(b: ByteArray, off: Int, len: Int): Int {
    if (len == 0) return 0
    val byte = this.read()
    if (byte == -1) return -1
    b[off] = byte.toByte()
    return 1
  }

  override fun read(): Int {
    return this.transform(super.read())
  }

  override fun close() {
    super.`in` = ClosedInputStream.INSTANCE
  }

  private fun transform(b: Int): Int {
    return when (b) {
      0x4 -> {
        close()
        -1
      }
      else -> {
        b
      }
    }
  }

  companion object {
    fun wrap(stream: InputStream): CommandInputStream {
      return CommandInputStream(stream)
    }
  }
}
