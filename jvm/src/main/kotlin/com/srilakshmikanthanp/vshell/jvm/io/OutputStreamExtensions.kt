package com.srilakshmikanthanp.vshell.jvm.io

import java.io.OutputStream

fun OutputStream.println(message: String) {
  this.write("$message\n".toByteArray())
}
