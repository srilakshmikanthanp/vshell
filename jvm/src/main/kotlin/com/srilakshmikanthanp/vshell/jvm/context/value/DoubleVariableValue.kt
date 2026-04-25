package com.srilakshmikanthanp.vshell.jvm.context.value

class DoubleVariableValue(val value: Double) : SimpleVariableValue {
  override fun toString(): String {
    return value.toString()
  }
}
