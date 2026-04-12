package com.srilakshmikanthanp.vshell.jvm.io

import java.io.OutputStream
import java.io.PrintWriter

class Output(
  val output: OutputStream,
  val writer: PrintWriter,
)