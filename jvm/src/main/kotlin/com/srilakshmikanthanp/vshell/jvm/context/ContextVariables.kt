package com.srilakshmikanthanp.vshell.jvm.context

import java.util.function.Supplier

class ContextVariables {
  private val variables = mutableMapOf<String, Supplier<String>>()

  fun set(key: String, value: Supplier<String>) {
    variables[key] = value
  }

  fun set(key: String, value: String) {
    this.set(key) { value }
  }

  fun get(key: String): Supplier<String>? {
    return variables[key]
  }

  fun getAll(): Map<String, Supplier<String>> {
    return variables.toMap()
  }
}
