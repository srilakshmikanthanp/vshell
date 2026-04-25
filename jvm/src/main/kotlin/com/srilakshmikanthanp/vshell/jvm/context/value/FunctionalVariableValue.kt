package com.srilakshmikanthanp.vshell.jvm.context.value

class FunctionalVariableValue(val supplier: () -> SimpleVariableValue): VariableValue {
  override fun toString(): String {
    return supplier().toString()
  }
}
