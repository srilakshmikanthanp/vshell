package com.srilakshmikanthanp.vshell.core.ast

class IdentifierNode(val name: String) : ExpressionNode {
  override fun <R> accept(visitor: NodeVisitor<R>): R {
    return visitor.visitIdentifier(this)
  }
}

fun ExpressionNode.asVShellIdentifierNode(): IdentifierNode {
  return this as? IdentifierNode
    ?: throw NodeMisMatchException(
      listOf(IdentifierNode::class),
      this::class
    )
}
