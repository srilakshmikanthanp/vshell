package com.srilakshmikanthanp.vshell.jvm.command.builtins

import java.io.BufferedReader
import java.io.InputStream

class Input(
  val input: InputStream,
  val reader: BufferedReader,
)
