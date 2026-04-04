package com.srilakshmikanthanp.vshell.parser.ast

sealed class NumberExpressionValue(open val value: Number) : LiteralExpression

fun LiteralExpression.asVShellNumberValue(): NumberExpressionValue {
  return this as? NumberExpressionValue ?: throw NodeMisMatchException(
    listOf(NumberExpressionValue::class),
    this::class
  )
}
