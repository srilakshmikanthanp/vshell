package com.srilakshmikanthanp.vshell.parser.ast

class PowerNode(left: ExpressionNode, right: ExpressionNode) : BinaryExpressionNode(left, right) {
  override fun <R> accept(visitor: NodeVisitor<R>): R {
    return visitor.visitPower(this)
  }
}

fun BinaryExpressionNode.asVShellPowerNode(): PowerNode {
  return this as? PowerNode ?: throw NodeMisMatchException(listOf(PowerNode::class), this::class)
}
