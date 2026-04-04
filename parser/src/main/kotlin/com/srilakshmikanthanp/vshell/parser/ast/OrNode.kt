package com.srilakshmikanthanp.vshell.parser.ast

class OrNode(left: ExpressionNode, right: ExpressionNode) : BinaryExpressionNode(left, right) {
  override fun <R> accept(visitor: NodeVisitor<R>): R {
    return visitor.visitOr(this)
  }
}

fun BinaryExpressionNode.asVShellOrNode(): OrNode {
  return this as? OrNode ?: throw NodeMisMatchException(listOf(OrNode::class), this::class)
}
