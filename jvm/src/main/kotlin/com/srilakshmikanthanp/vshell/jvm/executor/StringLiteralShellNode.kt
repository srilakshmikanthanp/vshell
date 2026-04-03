package com.srilakshmikanthanp.vshell.jvm.executor

class StringLiteralShellNode(override val value: String):
  LiteralShellNode(value),
  UnaryPlusShellOperatorNode,
  PlusShellOperatorNode,
  EqShellOperatorNode,
  NeqShellOperatorNode,
  GtShellOperatorNode,
  GteShellOperatorNode,
  LtShellOperatorNode,
  LteShellOperatorNode {
  override fun plus(other: ExecutionShellNode): ExecutionShellNode {
    return when (other) {
      is StringLiteralShellNode -> StringLiteralShellNode(this.value + other.value)
      is NumberLiteralShellNode -> StringLiteralShellNode(this.value + other.value.toString())
      else -> throw ExecutionShellNodeMisMatchException(listOf(StringLiteralShellNode::class, NumberLiteralShellNode::class), other::class)
    }
  }

  override fun eq(other: ExecutionShellNode): ExecutionShellNode {
    return BooleanLiteralShellNode(
      when (other) {
        is StringLiteralShellNode -> this.value == other.value
        else -> throw ExecutionShellNodeMisMatchException(listOf(StringLiteralShellNode::class, NumberLiteralShellNode::class), other::class)
      }
    )
  }

  override fun neq(other: ExecutionShellNode): ExecutionShellNode {
    return BooleanLiteralShellNode(
      when (other) {
        is StringLiteralShellNode -> this.value != other.value
        else -> throw ExecutionShellNodeMisMatchException(listOf(StringLiteralShellNode::class, NumberLiteralShellNode::class), other::class)
      }
    )
  }

  override fun gt(other: ExecutionShellNode): ExecutionShellNode {
    return BooleanLiteralShellNode(
      when (other) {
        is StringLiteralShellNode -> this.value > other.value
        else -> throw ExecutionShellNodeMisMatchException(listOf(StringLiteralShellNode::class, NumberLiteralShellNode::class), other::class)
      }
    )
  }

  override fun gte(other: ExecutionShellNode): ExecutionShellNode {
    return BooleanLiteralShellNode(
      when (other) {
        is StringLiteralShellNode -> this.value >= other.value
        else -> throw ExecutionShellNodeMisMatchException(listOf(StringLiteralShellNode::class, NumberLiteralShellNode::class), other::class)
      }
    )
  }

  override fun lt(other: ExecutionShellNode): ExecutionShellNode {
    return BooleanLiteralShellNode(
      when (other) {
        is StringLiteralShellNode -> this.value < other.value
        else -> throw ExecutionShellNodeMisMatchException(listOf(StringLiteralShellNode::class, NumberLiteralShellNode::class), other::class)
      }
    )
  }

  override fun lte(other: ExecutionShellNode): ExecutionShellNode {
    return BooleanLiteralShellNode(
      when (other) {
        is StringLiteralShellNode -> this.value <= other.value
        else -> throw ExecutionShellNodeMisMatchException(listOf(StringLiteralShellNode::class, NumberLiteralShellNode::class), other::class)
      }
    )
  }

  override fun plus(): UnaryMinusShellOperatorNode {
    return NumericLiteralShellNode(this.value.toDoubleOrNull() ?: throw ExecutorException("Cannot apply unary plus operator to string literal '${this.value}'"))
  }
}

fun LiteralShellNode.asStringLiteralShellNode(): StringLiteralShellNode {
  return this as? StringLiteralShellNode ?: throw ExecutionShellNodeMisMatchException(listOf(StringLiteralShellNode::class), this::class)
}
