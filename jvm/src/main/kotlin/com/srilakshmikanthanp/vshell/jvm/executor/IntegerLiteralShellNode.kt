package com.srilakshmikanthanp.vshell.jvm.executor

class IntegerLiteralShellNode (override val value: Int): NumberLiteralShellNode(value) {
  override fun minus(other: ExecutionShellNode): ExecutionShellNode {
    return when (other) {
      is NumericLiteralShellNode -> NumericLiteralShellNode(this.value.toDouble() - other.value)
      is IntegerLiteralShellNode -> IntegerLiteralShellNode(this.value - other.value)
      else -> throw ExecutionShellNodeMisMatchException(listOf(NumericLiteralShellNode::class, IntegerLiteralShellNode::class), other::class)
    }
  }

  override fun plus(other: ExecutionShellNode): ExecutionShellNode {
    return when (other) {
      is NumericLiteralShellNode -> NumericLiteralShellNode(this.value.toDouble() + other.value)
      is IntegerLiteralShellNode -> IntegerLiteralShellNode(this.value + other.value)
      is StringLiteralShellNode -> StringLiteralShellNode(this.value.toString() + other.value)
      else -> throw ExecutionShellNodeMisMatchException(listOf(NumericLiteralShellNode::class, IntegerLiteralShellNode::class), other::class)
    }
  }

  override fun multiply(other: ExecutionShellNode): ExecutionShellNode {
    return when (other) {
      is NumericLiteralShellNode -> NumericLiteralShellNode(this.value.toDouble() * other.value)
      is IntegerLiteralShellNode -> IntegerLiteralShellNode(this.value * other.value)
      else -> throw ExecutionShellNodeMisMatchException(listOf(NumericLiteralShellNode::class, IntegerLiteralShellNode::class), other::class)
    }
  }

  override fun divide(other: ExecutionShellNode): ExecutionShellNode {
    val divisor = when (other) {
      is IntegerLiteralShellNode -> other.value.toDouble()
      is NumericLiteralShellNode -> other.value
      else -> throw ExecutionShellNodeMisMatchException(listOf(NumericLiteralShellNode::class, IntegerLiteralShellNode::class, StringLiteralShellNode::class), other::class)
    }

    if (divisor == 0.0) throw ExecutorException("Division by zero is not allowed")
    return NumericLiteralShellNode(this.value.toDouble() / divisor)
  }

  override fun modulo(other: ExecutionShellNode): ExecutionShellNode {
    return when (other) {
      is NumericLiteralShellNode -> NumericLiteralShellNode(this.value.toDouble() % other.value)
      is IntegerLiteralShellNode -> IntegerLiteralShellNode(this.value % other.value)
      else -> throw ExecutionShellNodeMisMatchException(listOf(NumericLiteralShellNode::class, IntegerLiteralShellNode::class), other::class)
    }
  }

  override fun eq(other: ExecutionShellNode): ExecutionShellNode {
    return when (other) {
      is NumericLiteralShellNode -> BooleanLiteralShellNode(this.value.toDouble() == other.value)
      is IntegerLiteralShellNode -> BooleanLiteralShellNode(this.value == other.value)
      else -> throw ExecutionShellNodeMisMatchException(listOf(NumericLiteralShellNode::class, IntegerLiteralShellNode::class), other::class)
    }
  }

  override fun neq(other: ExecutionShellNode): ExecutionShellNode {
    return when (other) {
      is NumericLiteralShellNode -> BooleanLiteralShellNode(this.value.toDouble() != other.value)
      is IntegerLiteralShellNode -> BooleanLiteralShellNode(this.value != other.value)
      else -> throw ExecutionShellNodeMisMatchException(listOf(NumericLiteralShellNode::class, IntegerLiteralShellNode::class), other::class)
    }
  }

  override fun gt(other: ExecutionShellNode): ExecutionShellNode {
    return when (other) {
      is NumericLiteralShellNode -> BooleanLiteralShellNode(this.value.toDouble() > other.value)
      is IntegerLiteralShellNode -> BooleanLiteralShellNode(this.value > other.value)
      else -> throw ExecutionShellNodeMisMatchException(listOf(NumericLiteralShellNode::class, IntegerLiteralShellNode::class), other::class)
    }
  }

  override fun gte(other: ExecutionShellNode): ExecutionShellNode {
    return when (other) {
      is NumericLiteralShellNode -> BooleanLiteralShellNode(this.value.toDouble() >= other.value)
      is IntegerLiteralShellNode -> BooleanLiteralShellNode(this.value >= other.value)
      else -> throw ExecutionShellNodeMisMatchException(listOf(NumericLiteralShellNode::class, IntegerLiteralShellNode::class), other::class)
    }
  }

  override fun lt(other: ExecutionShellNode): ExecutionShellNode {
    return when (other) {
      is NumericLiteralShellNode -> BooleanLiteralShellNode(this.value.toDouble() < other.value)
      is IntegerLiteralShellNode -> BooleanLiteralShellNode(this.value < other.value)
      else -> throw ExecutionShellNodeMisMatchException(listOf(NumericLiteralShellNode::class, IntegerLiteralShellNode::class), other::class)
    }
  }

  override fun lte(other: ExecutionShellNode): ExecutionShellNode {
    return when (other) {
      is NumericLiteralShellNode -> BooleanLiteralShellNode(this.value.toDouble() <= other.value)
      is IntegerLiteralShellNode -> BooleanLiteralShellNode(this.value <= other.value)
      else -> throw ExecutionShellNodeMisMatchException(listOf(NumericLiteralShellNode::class, IntegerLiteralShellNode::class), other::class)
    }
  }

  override fun minus(): UnaryMinusShellOperatorNode {
    return IntegerLiteralShellNode(-value)
  }

  override fun plus(): UnaryMinusShellOperatorNode {
    return IntegerLiteralShellNode(+value)
  }
}

fun NumberLiteralShellNode.asIntegerLiteralShellNode(): IntegerLiteralShellNode {
  return this as? IntegerLiteralShellNode ?: throw ExecutionShellNodeMisMatchException(listOf(IntegerLiteralShellNode::class), this::class)
}
