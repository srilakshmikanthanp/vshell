package com.srilakshmikanthanp.vshell.parser.ast

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
