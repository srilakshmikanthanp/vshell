package com.srilakshmikanthanp.vshell.jvm.executor

abstract class LiteralShellNode(open val value: Any): ExpressionShellNode {
  override fun toString(): String {
    return value.toString()
  }
}

fun ExpressionShellNode.asLiteralShellNode(): LiteralShellNode {
  return this as? LiteralShellNode ?: throw ExecutionShellNodeMisMatchException(listOf(LiteralShellNode::class), this::class)
}