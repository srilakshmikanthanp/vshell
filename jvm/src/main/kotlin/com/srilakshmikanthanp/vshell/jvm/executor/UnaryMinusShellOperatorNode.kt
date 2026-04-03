package com.srilakshmikanthanp.vshell.jvm.executor

interface UnaryMinusShellOperatorNode: ExecutionShellNode {
  fun minus(): UnaryMinusShellOperatorNode
}

fun ExecutionShellNode.asUnaryMinusShellOperatorNode(): UnaryMinusShellOperatorNode {
  return this as? UnaryMinusShellOperatorNode ?: throw ExecutionShellNodeMisMatchException(listOf(UnaryMinusShellOperatorNode::class), this::class)
}
