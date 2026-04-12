package com.srilakshmikanthanp.vshell.jvm.io

import java.io.InputStream
import java.io.OutputStream

class CommandInputStream private constructor(private val stream: InputStream) : InputStream() {
  override fun read(b: ByteArray, off: Int, len: Int): Int {
    if (len == 0) return 0
    val byte = this.read()
    if (byte == -1) return -1
    b[off] = byte.toByte()
    return 1
  }

  override fun read(): Int {
    return when (val b = stream.read()) {
      0x4 -> (-1).also { stream.close() }
      else -> b
    }
  }

  override fun readAllBytes(): ByteArray {
    return stream.readBytes()
  }

  override fun readNBytes(len: Int): ByteArray? {
    return stream.readNBytes(len)
  }

  override fun readNBytes(b: ByteArray?, off: Int, len: Int): Int {
    return stream.readNBytes(b, off, len)
  }

  override fun skip(n: Long): Long {
    return stream.skip(n)
  }

  override fun skipNBytes(n: Long) {
    stream.skipNBytes(n)
  }

  override fun available(): Int {
    return stream.available()
  }

  override fun read(b: ByteArray?): Int {
    return stream.read(b)
  }

  override fun close() {
    super.close()
  }

  override fun mark(readlimit: Int) {
    stream.mark(readlimit)
  }

  override fun reset() {
    stream.reset()
  }

  override fun markSupported(): Boolean {
    return stream.markSupported()
  }

  override fun transferTo(out: OutputStream?): Long {
    return stream.transferTo(out)
  }

  companion object {
    fun wrap(stream: InputStream): CommandInputStream {
      return CommandInputStream(stream)
    }
  }
}
