package com.srilakshmikanthanp.vshell.jvm.executor

interface NotShellOperatorNode : ExecutionShellNode {
  fun not(): ExecutionShellNode
}

fun ExecutionShellNode.asNotShellOperatorNode(): NotShellOperatorNode {
  return this as? NotShellOperatorNode ?: throw ExecutionShellNodeMisMatchException(listOf(NotShellOperatorNode::class), this::class)
}

