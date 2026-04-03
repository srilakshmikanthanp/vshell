package com.srilakshmikanthanp.vshell.jvm.executor

interface MinusShellOperatorNode : BinaryShellOperatorNode {
  fun minus(other: ExecutionShellNode): ExecutionShellNode
}

fun ExecutionShellNode.asMinusShellOperatorNode(): MinusShellOperatorNode {
  return this as? MinusShellOperatorNode ?: throw ExecutionShellNodeMisMatchException(listOf(MinusShellOperatorNode::class), this::class)
}

