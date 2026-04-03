package com.srilakshmikanthanp.vshell.jvm.executor

interface NeqShellOperatorNode : BinaryShellOperatorNode {
  fun neq(other: ExecutionShellNode): ExecutionShellNode
}

fun ExecutionShellNode.asNeqShellOperatorNode(): NeqShellOperatorNode {
  return this as? NeqShellOperatorNode ?: throw ExecutionShellNodeMisMatchException(listOf(NeqShellOperatorNode::class), this::class)
}

