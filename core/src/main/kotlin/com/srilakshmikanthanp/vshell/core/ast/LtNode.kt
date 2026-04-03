package com.srilakshmikanthanp.vshell.core.ast

class LtNode(left: ExpressionNode, right: ExpressionNode) : BinaryExpressionNode(left, right) {
  override fun <R> accept(visitor: NodeVisitor<R>): R {
    return visitor.visitLt(this)
  }
}

fun BinaryExpressionNode.asVShellLtNode(): LtNode {
  return this as? LtNode
    ?: throw NodeMisMatchException(
      listOf(LtNode::class),
      this::class
    )
}
