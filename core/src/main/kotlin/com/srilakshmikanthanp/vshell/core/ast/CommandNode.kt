package com.srilakshmikanthanp.vshell.core.ast

sealed interface CommandNode : Node {
  fun pipe(other: CommandNode): CommandNode {
    return PipeCommandNode(this, other)
  }

  fun and(other: CommandNode): CommandNode {
    return AndCommandNode(this, other)
  }

  fun or(other: CommandNode): CommandNode {
    return OrCommandNode(this, other)
  }
}

fun Node.asCommandNode(): CommandNode {
  return this as? CommandNode
    ?: throw NodeMisMatchException(
      listOf(CommandNode::class),
      this::class
    )
}
