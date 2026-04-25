package com.srilakshmikanthanp.vshell.jvm.context.value

sealed interface VariableValue {
  override fun toString(): String
}

fun VariableValue.resolve(): SimpleVariableValue {
  return when (this) {
    is FunctionalVariableValue -> this.supplier()
    is SimpleVariableValue -> this
  }
}
