package com.srilakshmikanthanp.vshell.jvm.event

fun interface EventHandler {
  fun event(event: Event)
}
