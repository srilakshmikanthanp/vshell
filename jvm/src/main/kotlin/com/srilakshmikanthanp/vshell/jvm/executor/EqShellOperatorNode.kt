package com.srilakshmikanthanp.vshell.jvm.executor

interface EqShellOperatorNode : BinaryShellOperatorNode {
  fun eq(other: ExecutionShellNode): ExecutionShellNode
}

fun ExecutionShellNode.asEqShellOperatorNode(): EqShellOperatorNode {
  return this as? EqShellOperatorNode ?: throw ExecutionShellNodeMisMatchException(listOf(EqShellOperatorNode::class), this::class)
}

