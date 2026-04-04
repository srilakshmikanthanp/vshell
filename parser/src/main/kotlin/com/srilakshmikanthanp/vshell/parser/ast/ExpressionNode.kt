package com.srilakshmikanthanp.vshell.parser.ast

sealed interface ExpressionNode : Node

fun Node.asVShellExpressionNode(): ExpressionNode {
  return this as? ExpressionNode
    ?: throw NodeMisMatchException(
      listOf(ExpressionNode::class),
      this::class
    )
}
