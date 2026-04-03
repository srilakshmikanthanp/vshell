package com.srilakshmikanthanp.vshell.jvm.command

import com.srilakshmikanthanp.vshell.jvm.context.Context

fun interface CommandBuilder {
  fun build(context: Context, args: List<String>): Command
}
