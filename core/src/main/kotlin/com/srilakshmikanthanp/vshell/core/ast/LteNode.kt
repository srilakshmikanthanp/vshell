package com.srilakshmikanthanp.vshell.core.ast

class LteNode(left: ExpressionNode, right: ExpressionNode) : BinaryExpressionNode(left, right) {
  override fun <R> accept(visitor: NodeVisitor<R>): R {
    return visitor.visitLte(this)
  }
}

fun BinaryExpressionNode.asVShellLteNode(): LteNode {
  return this as? LteNode
    ?: throw NodeMisMatchException(
      listOf(LteNode::class),
      this::class
    )
}
