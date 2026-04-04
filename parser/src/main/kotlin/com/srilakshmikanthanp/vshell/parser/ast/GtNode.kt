package com.srilakshmikanthanp.vshell.parser.ast

class GtNode(left: ExpressionNode, right: ExpressionNode) : BinaryExpressionNode(left, right) {
  override fun <R> accept(visitor: NodeVisitor<R>): R {
    return visitor.visitGt(this)
  }
}

fun BinaryExpressionNode.asVShellGtNode(): GtNode {
  return this as? GtNode
    ?: throw NodeMisMatchException(
      listOf(GtNode::class),
      this::class
    )
}
