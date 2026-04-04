package com.srilakshmikanthanp.vshell.parser.ast

class NotNode(expression: ExpressionNode) : UnaryExpressionNode(expression) {
  override fun <R> accept(visitor: NodeVisitor<R>): R {
    return visitor.visitNot(this)
  }
}

fun UnaryExpressionNode.asVShellNotNode(): NotNode {
  return this as? NotNode ?: throw NodeMisMatchException(listOf(NotNode::class), this::class)
}
