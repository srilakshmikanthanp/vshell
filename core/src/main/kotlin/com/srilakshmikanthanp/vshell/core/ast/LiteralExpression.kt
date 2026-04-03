package com.srilakshmikanthanp.vshell.core.ast

sealed interface LiteralExpression: ExpressionNode

fun ExpressionNode.asVShellLiteralExpression(): LiteralExpression {
  return this as? LiteralExpression
    ?: throw NodeMisMatchException(
      listOf(LiteralExpression::class),
      this::class
    )
}
