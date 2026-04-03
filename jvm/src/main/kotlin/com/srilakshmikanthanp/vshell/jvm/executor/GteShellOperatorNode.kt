package com.srilakshmikanthanp.vshell.jvm.executor

interface GteShellOperatorNode : BinaryShellOperatorNode {
  fun gte(other: ExecutionShellNode): ExecutionShellNode
}

fun ExecutionShellNode.asGteShellOperatorNode(): GteShellOperatorNode {
  return this as? GteShellOperatorNode ?: throw ExecutionShellNodeMisMatchException(listOf(GteShellOperatorNode::class), this::class)
}

