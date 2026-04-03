package com.srilakshmikanthanp.vshell.jvm.executor

interface ModuloShellOperatorNode : BinaryShellOperatorNode {
  fun modulo(other: ExecutionShellNode): ExecutionShellNode
}

fun ExecutionShellNode.asModuloShellOperatorNode(): ModuloShellOperatorNode {
  return this as? ModuloShellOperatorNode ?: throw ExecutionShellNodeMisMatchException(listOf(ModuloShellOperatorNode::class), this::class)
}

