package com.srilakshmikanthanp.vshell.jvm.event

import java.util.concurrent.ConcurrentLinkedDeque
import java.util.concurrent.CopyOnWriteArrayList

class SimpleEventSource : EventSource {
  private val scopes = ConcurrentLinkedDeque<CopyOnWriteArrayList<EventHandler>>()
  private val scope get() = scopes.peekLast() ?: throw IllegalStateException("Empty Scope")

  override fun subscribe(handler: EventHandler): AutoCloseable {
    val handlers = this.scope
    scope.add(handler)
    return AutoCloseable { handlers.remove(handler) }
  }

  override fun dispatch(event: Event) {
    val handlers = this.scope
    handlers.forEach { it.event(event) }
  }

  override fun enterScope(): AutoCloseable {
    val handlers = CopyOnWriteArrayList<EventHandler>()
    scopes.addLast(handlers)
    return AutoCloseable { scopes.remove(handlers) }
  }
}
