package com.srilakshmikanthanp.vshell.core.ast

sealed class NumberExpressionValue(val value: Number) : LiteralExpression

fun LiteralExpression.asVShellNumberValue(): NumberExpressionValue {
  return this as? NumberExpressionValue ?: throw NodeMisMatchException(
    listOf(NumberExpressionValue::class),
    this::class
  )
}
