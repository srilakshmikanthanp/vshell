package com.srilakshmikanthanp.vshell.parser.ast

class GteNode(left: ExpressionNode, right: ExpressionNode) : BinaryExpressionNode(left, right) {
  override fun <R> accept(visitor: NodeVisitor<R>): R {
    return visitor.visitGte(this)
  }
}

fun BinaryExpressionNode.asVShellGteNode(): GteNode {
  return this as? GteNode
    ?: throw NodeMisMatchException(
      listOf(GteNode::class),
      this::class
    )
}
