package com.srilakshmikanthanp.vshell.jvm.context

import com.srilakshmikanthanp.vshell.jvm.context.value.VariableValue

class ContextVariables {
  private val variables = mutableMapOf<String, VariableValue>()

  fun set(key: String, value: VariableValue) {
    variables[key] = value
  }

  fun get(key: String): VariableValue? {
    return variables[key]
  }

  fun getAll(): Map<String, VariableValue> {
    return variables.toMap()
  }
}
