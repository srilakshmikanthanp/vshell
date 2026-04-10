package com.srilakshmikanthanp.vshell.jvm.command.builtins

import java.io.OutputStream
import java.io.PrintWriter

class Output(
  val output: OutputStream,
  val writer: PrintWriter,
)
