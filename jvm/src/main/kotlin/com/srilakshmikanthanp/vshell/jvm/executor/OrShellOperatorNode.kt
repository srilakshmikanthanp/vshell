package com.srilakshmikanthanp.vshell.jvm.executor

interface OrShellOperatorNode : BinaryShellOperatorNode {
  fun or(other: ExecutionShellNode): ExecutionShellNode
}

fun ExecutionShellNode.asOrShellOperatorNode(): OrShellOperatorNode {
  return this as? OrShellOperatorNode ?: throw ExecutionShellNodeMisMatchException(listOf(OrShellOperatorNode::class), this::class)
}

