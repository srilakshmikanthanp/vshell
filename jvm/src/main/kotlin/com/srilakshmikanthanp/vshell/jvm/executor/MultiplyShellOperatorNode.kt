package com.srilakshmikanthanp.vshell.jvm.executor

interface MultiplyShellOperatorNode : BinaryShellOperatorNode {
  fun multiply(other: ExecutionShellNode): ExecutionShellNode
}

fun ExecutionShellNode.asMultiplyShellOperatorNode(): MultiplyShellOperatorNode {
  return this as? MultiplyShellOperatorNode ?: throw ExecutionShellNodeMisMatchException(listOf(MultiplyShellOperatorNode::class), this::class)
}

