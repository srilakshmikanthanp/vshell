package com.srilakshmikanthanp.vshell.jvm.event

interface EventDispatcher {
  fun dispatch(event: Event)
}
