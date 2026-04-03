package com.srilakshmikanthanp.vshell.jvm.executor

interface ExpressionShellNode : ExecutionShellNode

fun ExecutionShellNode.asExpressionShellNode(): ExpressionShellNode {
  return this as? ExpressionShellNode ?: throw ExecutionShellNodeMisMatchException(listOf(ExpressionShellNode::class), this::class)
}
