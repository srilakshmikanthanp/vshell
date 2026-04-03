package com.srilakshmikanthanp.vshell.jvm.executor

interface PowerShellOperatorNode : BinaryShellOperatorNode {
  fun power(other: ExecutionShellNode): ExecutionShellNode
}

fun ExecutionShellNode.asPowerShellOperatorNode(): PowerShellOperatorNode {
  return this as? PowerShellOperatorNode ?: throw ExecutionShellNodeMisMatchException(listOf(PowerShellOperatorNode::class), this::class)
}

