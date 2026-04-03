package com.srilakshmikanthanp.vshell.jvm.command

import java.io.InputStream
import java.io.OutputStream

interface Command {
  fun execute(stdIn: InputStream, stdOut: OutputStream, stdErr: OutputStream): Int
}
