package com.srilakshmikanthanp.vshell.core.ast

class BooleanExpressionValue(val value: Boolean) : LiteralExpression {
  override fun <R> accept(visitor: NodeVisitor<R>): R {
    return visitor.visitBoolean(this)
  }
}

fun LiteralExpression.asVShellBooleanValue(): BooleanExpressionValue {
  return this as? BooleanExpressionValue
    ?: throw NodeMisMatchException(
      listOf(BooleanExpressionValue::class),
      this::class
    )
}
