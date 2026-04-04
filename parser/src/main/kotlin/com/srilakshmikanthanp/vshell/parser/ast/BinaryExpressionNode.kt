package com.srilakshmikanthanp.vshell.parser.ast

sealed class BinaryExpressionNode(
  val left: ExpressionNode,
  val right: ExpressionNode
) : ExpressionNode

fun ExpressionNode.asVShellBinaryExpressionNode(): BinaryExpressionNode {
  return this as? BinaryExpressionNode
    ?: throw NodeMisMatchException(
      listOf(BinaryExpressionNode::class),
      this::class
    )
}
