package com.srilakshmikanthanp.vshell.jvm.context.value

class StringVariableValue(val value: String) : SimpleVariableValue {
  override fun toString(): String {
    return value
  }
}
