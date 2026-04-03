package com.srilakshmikanthanp.vshell.jvm.executor

class NumericLiteralShellNode (override val value: Double): NumberLiteralShellNode(value) {
  override fun minus(other: ExecutionShellNode): ExecutionShellNode {
    return when (other) {
      is NumberLiteralShellNode -> NumericLiteralShellNode(this.value - other.value.toDouble())
      else -> throw ExecutionShellNodeMisMatchException(listOf(NumericLiteralShellNode::class, IntegerLiteralShellNode::class), other::class)
    }
  }

  override fun plus(other: ExecutionShellNode): ExecutionShellNode {
    return when (other) {
      is NumberLiteralShellNode -> NumericLiteralShellNode(this.value + other.value.toDouble())
      is StringLiteralShellNode -> StringLiteralShellNode(this.value.toString() + other.value)
      else -> throw ExecutionShellNodeMisMatchException(listOf(NumericLiteralShellNode::class, IntegerLiteralShellNode::class), other::class)
    }
  }

  override fun multiply(other: ExecutionShellNode): ExecutionShellNode {
    return when (other) {
      is NumberLiteralShellNode -> NumericLiteralShellNode(this.value * other.value.toDouble())
      else -> throw ExecutionShellNodeMisMatchException(listOf(NumericLiteralShellNode::class, IntegerLiteralShellNode::class), other::class)
    }
  }

  override fun divide(other: ExecutionShellNode): ExecutionShellNode {
    val divisor = when (other) {
      is NumberLiteralShellNode -> other.value.toDouble()
      else -> throw ExecutionShellNodeMisMatchException(listOf(NumericLiteralShellNode::class), other::class)
    }

    if (divisor == 0.0) throw ExecutorException("Divisor cannot be zero")
    return NumericLiteralShellNode(this.value / divisor)
  }

  override fun modulo(other: ExecutionShellNode): ExecutionShellNode {
    return when (other) {
      is NumberLiteralShellNode -> NumericLiteralShellNode(this.value % other.value.toDouble())
      else -> throw ExecutionShellNodeMisMatchException(listOf(NumericLiteralShellNode::class), other::class)
    }
  }

  override fun eq(other: ExecutionShellNode): ExecutionShellNode {
    return when (other) {
      is NumberLiteralShellNode -> BooleanLiteralShellNode(this.value == other.value.toDouble())
      else -> throw ExecutionShellNodeMisMatchException(listOf(NumericLiteralShellNode::class), other::class)
    }
  }

  override fun neq(other: ExecutionShellNode): ExecutionShellNode {
    return when (other) {
      is NumberLiteralShellNode -> BooleanLiteralShellNode(this.value != other.value.toDouble())
      else -> throw ExecutionShellNodeMisMatchException(listOf(NumericLiteralShellNode::class), other::class)
    }
  }

  override fun gt(other: ExecutionShellNode): ExecutionShellNode {
    return when (other) {
      is NumberLiteralShellNode -> BooleanLiteralShellNode(this.value > other.value.toDouble())
      else -> throw ExecutionShellNodeMisMatchException(listOf(NumericLiteralShellNode::class), other::class)
    }
  }

  override fun gte(other: ExecutionShellNode): ExecutionShellNode {
    return when (other) {
      is NumberLiteralShellNode -> BooleanLiteralShellNode(this.value >= other.value.toDouble())
      else -> throw ExecutionShellNodeMisMatchException(listOf(NumericLiteralShellNode::class), other::class)
    }
  }

  override fun lt(other: ExecutionShellNode): ExecutionShellNode {
    return when (other) {
      is NumberLiteralShellNode -> BooleanLiteralShellNode(this.value < other.value.toDouble())
      else -> throw ExecutionShellNodeMisMatchException(listOf(NumericLiteralShellNode::class), other::class)
    }
  }

  override fun lte(other: ExecutionShellNode): ExecutionShellNode {
    return when (other) {
      is NumberLiteralShellNode -> BooleanLiteralShellNode(this.value <= other.value.toDouble())
      else -> throw ExecutionShellNodeMisMatchException(listOf(NumericLiteralShellNode::class), other::class)
    }
  }

  override fun minus(): UnaryMinusShellOperatorNode {
    return NumericLiteralShellNode(-value)
  }

  override fun plus(): UnaryMinusShellOperatorNode {
    return NumericLiteralShellNode(+value)
  }
}

fun NumberLiteralShellNode.asNumericLiteralShellNode(): NumericLiteralShellNode {
  return this as? NumericLiteralShellNode ?: throw ExecutionShellNodeMisMatchException(listOf(NumericLiteralShellNode::class), this::class)
}
