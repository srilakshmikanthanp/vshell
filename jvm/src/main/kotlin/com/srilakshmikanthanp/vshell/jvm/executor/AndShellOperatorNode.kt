package com.srilakshmikanthanp.vshell.jvm.executor

interface AndShellOperatorNode : BinaryShellOperatorNode {
  fun and(other: ExecutionShellNode): ExecutionShellNode
}

fun ExecutionShellNode.asAndShellOperatorNode(): AndShellOperatorNode {
  return this as? AndShellOperatorNode ?: throw ExecutionShellNodeMisMatchException(listOf(AndShellOperatorNode::class), this::class)
}

