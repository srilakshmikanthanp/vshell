package com.srilakshmikanthanp.vshell.jvm.executor

import com.srilakshmikanthanp.vshell.jvm.command.Command

class CommandShellNode(val command: Command) : ExecutionShellNode

fun ExecutionShellNode.asCommandShellNode(): CommandShellNode {
  return this as? CommandShellNode ?: throw ExecutionShellNodeMisMatchException(
    expectedTypes = listOf(CommandShellNode::class),
    actualNode = this::class
  )
}
