package com.srilakshmikanthanp.vshell.parser.ast

class NumericExpressionValue(value: Double) : NumberExpressionValue(value) {
  override fun <R> accept(visitor: NodeVisitor<R>): R {
    return visitor.visitNumeric(this)
  }
}

fun NumberExpressionValue.asVShellNumericValue(): NumericExpressionValue {
  return this as? NumericExpressionValue ?: throw NodeMisMatchException(
    listOf(NumericExpressionValue::class),
    this::class
  )
}
