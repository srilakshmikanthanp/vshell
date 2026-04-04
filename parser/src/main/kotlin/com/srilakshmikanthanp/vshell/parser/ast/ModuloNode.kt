package com.srilakshmikanthanp.vshell.parser.ast

class ModuloNode(left: ExpressionNode, right: ExpressionNode) : BinaryExpressionNode(left, right) {
  override fun <R> accept(visitor: NodeVisitor<R>): R {
    return visitor.visitModulo(this)
  }
}

fun BinaryExpressionNode.asVShellModuloNode(): ModuloNode {
  return this as? ModuloNode ?: throw NodeMisMatchException(
    listOf(ModuloNode::class),
    this::class
  )
}
