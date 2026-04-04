package com.srilakshmikanthanp.vshell.parser.ast

class DivideNode(left: ExpressionNode, right: ExpressionNode) : BinaryExpressionNode(left, right) {
  override fun <R> accept(visitor: NodeVisitor<R>): R {
    return visitor.visitDivide(this)
  }
}

fun BinaryExpressionNode.asVShellDivideNode(): DivideNode {
  return this as? DivideNode
    ?: throw NodeMisMatchException(
      listOf(DivideNode::class),
      this::class
    )
}
