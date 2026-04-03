package com.srilakshmikanthanp.vshell.jvm.executor

interface PlusShellOperatorNode : BinaryShellOperatorNode {
  fun plus(other: ExecutionShellNode): ExecutionShellNode
}

fun ExecutionShellNode.asPlusShellOperatorNode(): PlusShellOperatorNode {
  return this as? PlusShellOperatorNode ?: throw ExecutionShellNodeMisMatchException(listOf(PlusShellOperatorNode::class), this::class)
}

