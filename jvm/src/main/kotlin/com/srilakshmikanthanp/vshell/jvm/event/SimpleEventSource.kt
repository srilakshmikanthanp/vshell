package com.srilakshmikanthanp.vshell.jvm.event

import java.util.concurrent.CopyOnWriteArrayList

class SimpleEventSource : EventSource {
  private val handlers = CopyOnWriteArrayList<EventHandler>()

  override fun subscribe(handler: EventHandler): AutoCloseable {
    handlers.add(handler)
    return AutoCloseable { handlers.remove(handler) }
  }

  override fun dispatch(event: Event) {
    this.handlers.forEach { it.event(event) }
  }
}
