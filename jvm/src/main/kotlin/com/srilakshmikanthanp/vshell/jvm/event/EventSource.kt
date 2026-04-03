package com.srilakshmikanthanp.vshell.jvm.event

interface EventSource {
  fun subscribe(handler: EventHandler): AutoCloseable
  fun dispatch(event: Event)
}
