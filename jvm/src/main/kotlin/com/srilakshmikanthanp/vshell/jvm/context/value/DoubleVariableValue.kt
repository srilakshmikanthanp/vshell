package com.srilakshmikanthanp.vshell.jvm.context.value

class DoubleVariableValue(override val value: Double) : NumericValue(value) {
  override fun toString(): String {
    return value.toString()
  }
}
