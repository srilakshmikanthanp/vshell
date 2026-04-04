package com.srilakshmikanthanp.vshell.parser.ast

class UnaryPlusNode(expression: ExpressionNode) : UnaryExpressionNode(expression) {
  override fun <R> accept(visitor: NodeVisitor<R>): R {
    return visitor.visitUnaryPlus(this)
  }
}

fun UnaryExpressionNode.asVShellUnaryPlusNode(): UnaryPlusNode {
  return this as? UnaryPlusNode ?: throw NodeMisMatchException(listOf(UnaryPlusNode::class), this::class)
}
