package com.srilakshmikanthanp.vshell.parser.ast

class IntegerExpressionValue(override val value: Long) : NumberExpressionValue(value) {
  override fun <R> accept(visitor: NodeVisitor<R>): R {
    return visitor.visitInteger(this)
  }
}

fun NumberExpressionValue.asVShellIntegerValue(): IntegerExpressionValue {
  return this as? IntegerExpressionValue
    ?: throw NodeMisMatchException(
      listOf(IntegerExpressionValue::class),
      this::class
    )
}
