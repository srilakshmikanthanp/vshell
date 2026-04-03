package com.srilakshmikanthanp.vshell.jvm.executor

interface LtShellOperatorNode : BinaryShellOperatorNode {
  fun lt(other: ExecutionShellNode): ExecutionShellNode
}

fun ExecutionShellNode.asLtShellOperatorNode(): LtShellOperatorNode {
  return this as? LtShellOperatorNode ?: throw ExecutionShellNodeMisMatchException(listOf(LtShellOperatorNode::class), this::class)
}

