package com.srilakshmikanthanp.vshell.jvm.executor

class BooleanLiteralShellNode(override val value: Boolean):
  LiteralShellNode(value),
  EqShellOperatorNode,
  NeqShellOperatorNode,
  AndShellOperatorNode,
  OrShellOperatorNode,
  NotShellOperatorNode {
  override fun eq(other: ExecutionShellNode): ExecutionShellNode {
    return when (other) {
      is BooleanLiteralShellNode -> BooleanLiteralShellNode(value == other.value)
      else -> throw ExecutionShellNodeMisMatchException(listOf(BooleanLiteralShellNode::class), other::class)
    }
  }

  override fun neq(other: ExecutionShellNode): ExecutionShellNode {
    return when (other) {
      is BooleanLiteralShellNode -> BooleanLiteralShellNode(value != other.value)
      else -> throw ExecutionShellNodeMisMatchException(listOf(BooleanLiteralShellNode::class), other::class)
    }
  }

  override fun and(other: ExecutionShellNode): ExecutionShellNode {
    return when (other) {
      is BooleanLiteralShellNode -> BooleanLiteralShellNode(value && other.value)
      else -> throw ExecutionShellNodeMisMatchException(listOf(BooleanLiteralShellNode::class), other::class)
    }
  }

  override fun or(other: ExecutionShellNode): ExecutionShellNode {
    return when (other) {
      is BooleanLiteralShellNode -> BooleanLiteralShellNode(value || other.value)
      else -> throw ExecutionShellNodeMisMatchException(listOf(BooleanLiteralShellNode::class), other::class)
    }
  }

  override fun not(): ExecutionShellNode {
    return BooleanLiteralShellNode(!value)
  }
}

fun LiteralShellNode.asBooleanLiteralShellNode(): BooleanLiteralShellNode {
  return this as? BooleanLiteralShellNode ?: throw ExecutionShellNodeMisMatchException(listOf(BooleanLiteralShellNode::class), this::class)
}
