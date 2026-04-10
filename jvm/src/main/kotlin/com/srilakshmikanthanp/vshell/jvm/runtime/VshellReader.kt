package com.srilakshmikanthanp.vshell.jvm.runtime

import com.srilakshmikanthanp.vshell.jvm.context.Context

fun interface VshellReader {
  fun read(context: Context): String
}
