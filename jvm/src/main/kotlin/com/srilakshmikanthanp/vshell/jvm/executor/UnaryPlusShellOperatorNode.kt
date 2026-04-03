package com.srilakshmikanthanp.vshell.jvm.executor

interface UnaryPlusShellOperatorNode: ExecutionShellNode {
  fun plus(): UnaryMinusShellOperatorNode
}

fun ExecutionShellNode.asUnaryPlusShellOperatorNode(): UnaryPlusShellOperatorNode {
  return this as? UnaryPlusShellOperatorNode ?: throw ExecutionShellNodeMisMatchException(listOf(UnaryMinusShellOperatorNode::class), this::class)
}
