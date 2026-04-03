package com.srilakshmikanthanp.vshell.core.ast

sealed class UnaryExpressionNode(val expression: ExpressionNode) : ExpressionNode

fun ExpressionNode.asVShellUnaryExpressionNode(): UnaryExpressionNode {
  return this as? UnaryExpressionNode ?: throw NodeMisMatchException(listOf(UnaryExpressionNode::class), this::class)
}
