package com.srilakshmikanthanp.vshell.jvm.executor

import com.srilakshmikanthanp.vshell.jvm.command.Command

class CommandsShellNode(val commands: List<Command>) : ExecutionShellNode

fun ExecutionShellNode.asCommandsShellNode(): CommandsShellNode {
  return this as? CommandsShellNode ?: throw ExecutionShellNodeMisMatchException(
    expectedTypes = listOf(CommandsShellNode::class),
    actualNode = this::class
  )
}
