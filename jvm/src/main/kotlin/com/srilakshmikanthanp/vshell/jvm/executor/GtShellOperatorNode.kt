package com.srilakshmikanthanp.vshell.jvm.executor

interface GtShellOperatorNode : BinaryShellOperatorNode {
  fun gt(other: ExecutionShellNode): ExecutionShellNode
}

fun ExecutionShellNode.asGtShellOperatorNode(): GtShellOperatorNode {
  return this as? GtShellOperatorNode ?: throw ExecutionShellNodeMisMatchException(listOf(GtShellOperatorNode::class), this::class)
}

