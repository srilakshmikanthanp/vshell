package com.srilakshmikanthanp.vshell.core.ast

class CommandsNode (
  val commands: List<CommandNode>
) : Node {
  override fun <R> accept(visitor: NodeVisitor<R>): R {
    return visitor.visitCommands(this)
  }
}

fun Node.asVShellCommandsNode(): CommandsNode {
  return this as? CommandsNode
    ?: throw NodeMisMatchException(
      listOf(CommandsNode::class),
      this::class
    )
}
