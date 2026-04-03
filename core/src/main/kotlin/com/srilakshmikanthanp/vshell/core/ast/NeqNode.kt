package com.srilakshmikanthanp.vshell.core.ast

class NeqNode(left: ExpressionNode, right: ExpressionNode) : BinaryExpressionNode(left, right) {
  override fun <R> accept(visitor: NodeVisitor<R>): R {
    return visitor.visitNeq(this)
  }
}

fun BinaryExpressionNode.asVShellNeqNode(): NeqNode {
  return this as? NeqNode
    ?: throw NodeMisMatchException(
      listOf(NeqNode::class),
      this::class
    )
}
