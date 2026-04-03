package com.srilakshmikanthanp.vshell.jvm.executor

abstract class NumberLiteralShellNode(override val value: Number):
  LiteralShellNode(value),
  UnaryMinusShellOperatorNode,
  UnaryPlusShellOperatorNode,
  PlusShellOperatorNode,
  MinusShellOperatorNode,
  MultiplyShellOperatorNode,
  DivideShellOperatorNode,
  ModuloShellOperatorNode,
  PowerShellOperatorNode,
  EqShellOperatorNode,
  NeqShellOperatorNode,
  GtShellOperatorNode,
  GteShellOperatorNode,
  LtShellOperatorNode,
  LteShellOperatorNode {
  override fun power(other: ExecutionShellNode): ExecutionShellNode {
    return when (other) {
      is NumberLiteralShellNode -> NumericLiteralShellNode(Math.pow(this.value.toDouble(), other.value.toDouble()))
      else -> throw ExecutionShellNodeMisMatchException(listOf(NumberLiteralShellNode::class), other::class)
    }
  }
}

fun LiteralShellNode.asNumberLiteralShellNode(): NumberLiteralShellNode {
  return this as? NumberLiteralShellNode ?: throw ExecutionShellNodeMisMatchException(listOf(NumberLiteralShellNode::class), this::class)
}
