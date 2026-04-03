package com.srilakshmikanthanp.vshell.jvm.executor

interface DivideShellOperatorNode : BinaryShellOperatorNode {
  fun divide(other: ExecutionShellNode): ExecutionShellNode
}

fun ExecutionShellNode.asDivideShellOperatorNode(): DivideShellOperatorNode {
  return this as? DivideShellOperatorNode ?: throw ExecutionShellNodeMisMatchException(listOf(DivideShellOperatorNode::class), this::class)
}

