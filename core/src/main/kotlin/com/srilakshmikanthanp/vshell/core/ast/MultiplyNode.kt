package com.srilakshmikanthanp.vshell.core.ast

class MultiplyNode(left: ExpressionNode, right: ExpressionNode) : BinaryExpressionNode(left, right) {
  override fun <R> accept(visitor: NodeVisitor<R>): R {
    return visitor.visitMultiply(this)
  }
}

fun BinaryExpressionNode.asVShellMultiplyNode(): MultiplyNode {
  return this as? MultiplyNode
    ?: throw NodeMisMatchException(
      listOf(MultiplyNode::class),
      this::class
    )
}
