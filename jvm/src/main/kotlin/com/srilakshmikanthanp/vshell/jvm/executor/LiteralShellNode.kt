package com.srilakshmikanthanp.vshell.jvm.executor

open class LiteralShellNode(open val value: Any): ExpressionShellNode

fun ExpressionShellNode.asLiteralShellNode(): LiteralShellNode {
  return this as? LiteralShellNode ?: throw ExecutionShellNodeMisMatchException(listOf(LiteralShellNode::class), this::class)
}