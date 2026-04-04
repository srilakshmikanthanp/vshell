package com.srilakshmikanthanp.vshell.parser.ast

class AndNode(
  left: ExpressionNode,
  right: ExpressionNode
) : BinaryExpressionNode(left, right) {
  override fun <R> accept(visitor: NodeVisitor<R>): R {
    return visitor.visitAnd(this)
  }
}

fun BinaryExpressionNode.asVShellAndNode(): AndNode {
  return this as? AndNode
    ?: throw NodeMisMatchException(
      listOf(AndNode::class),
      this::class
    )
}
