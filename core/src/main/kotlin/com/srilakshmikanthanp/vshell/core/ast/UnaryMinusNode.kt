package com.srilakshmikanthanp.vshell.core.ast

class UnaryMinusNode(expression: ExpressionNode) : UnaryExpressionNode(expression) {
  override fun <R> accept(visitor: NodeVisitor<R>): R {
    return visitor.visitUnaryMinus(this)
  }
}

fun UnaryExpressionNode.asVShellUnaryMinusNode(): UnaryMinusNode {
  return this as? UnaryMinusNode ?: throw NodeMisMatchException(listOf(UnaryMinusNode::class), this::class)
}
