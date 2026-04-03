package com.srilakshmikanthanp.vshell.core.ast

class PlusNode(left: ExpressionNode, right: ExpressionNode) : BinaryExpressionNode(left, right) {
  override fun <R> accept(visitor: NodeVisitor<R>): R {
    return visitor.visitPlus(this)
  }
}

fun BinaryExpressionNode.asVShellPlusNode(): PlusNode {
  return this as? PlusNode ?: throw NodeMisMatchException(listOf(PlusNode::class), this::class)
}
