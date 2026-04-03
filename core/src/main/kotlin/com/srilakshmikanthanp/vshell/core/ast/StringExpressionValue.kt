package com.srilakshmikanthanp.vshell.core.ast

class StringExpressionValue(val value: String) : LiteralExpression {
  override fun <R> accept(visitor: NodeVisitor<R>): R {
    return visitor.visitString(this)
  }
}

fun LiteralExpression.asVShellStringValue(): StringExpressionValue {
  return this as? StringExpressionValue ?: throw NodeMisMatchException(
    listOf(StringExpressionValue::class),
    this::class
  )
}
