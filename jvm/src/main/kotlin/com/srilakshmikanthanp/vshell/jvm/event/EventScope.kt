package com.srilakshmikanthanp.vshell.jvm.event

interface EventScope {
  fun enterScope(): AutoCloseable
}
