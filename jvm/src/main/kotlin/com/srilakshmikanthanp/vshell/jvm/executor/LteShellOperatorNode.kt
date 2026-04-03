package com.srilakshmikanthanp.vshell.jvm.executor

interface LteShellOperatorNode : BinaryShellOperatorNode {
  fun lte(other: ExecutionShellNode): ExecutionShellNode
}

fun ExecutionShellNode.asLteShellOperatorNode(): LteShellOperatorNode {
  return this as? LteShellOperatorNode ?: throw ExecutionShellNodeMisMatchException(listOf(LteShellOperatorNode::class), this::class)
}

