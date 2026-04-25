package com.srilakshmikanthanp.vshell.jvm.context.value

class IntegerVariableValue(val value: Long) : SimpleVariableValue {
  override fun toString(): String {
    return value.toString()
  }
}
