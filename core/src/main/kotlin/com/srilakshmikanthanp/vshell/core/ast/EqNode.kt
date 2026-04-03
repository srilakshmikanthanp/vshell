package com.srilakshmikanthanp.vshell.core.ast

class EqNode(left: ExpressionNode, right: ExpressionNode) : BinaryExpressionNode(left, right) {
  override fun <R> accept(visitor: NodeVisitor<R>): R {
    return visitor.visitEq(this)
  }
}

fun BinaryExpressionNode.asVShellEqNode(): EqNode {
  return this as? EqNode
    ?: throw NodeMisMatchException(
      listOf(EqNode::class),
      this::class
    )
}
