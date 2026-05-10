package com.srilakshmikanthanp.vshell.jvm.context.value

class IntegerVariableValue(override val value: Long) : NumericValue(value) {
  override fun toString(): String {
    return value.toString()
  }
}
