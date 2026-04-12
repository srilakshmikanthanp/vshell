package com.srilakshmikanthanp.vshell.jvm.shell

import com.srilakshmikanthanp.vshell.jvm.context.Context
import kotlin.jvm.Throws

interface VshellReader {
  @Throws(VshellEndOfFileException::class, VshellInterruptException::class)
  fun read(context: Context): String
}
