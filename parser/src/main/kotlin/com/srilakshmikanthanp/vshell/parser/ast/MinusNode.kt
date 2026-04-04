package com.srilakshmikanthanp.vshell.parser.ast

class MinusNode(left: ExpressionNode, right: ExpressionNode) : BinaryExpressionNode(left, right) {
  override fun <R> accept(visitor: NodeVisitor<R>): R {
    return visitor.visitMinus(this)
  }
}

fun BinaryExpressionNode.asVShellMinusNode(): MinusNode {
  return this as? MinusNode
    ?: throw NodeMisMatchException(
      listOf(MinusNode::class),
      this::class
    )
}
