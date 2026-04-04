package com.srilakshmikanthanp.vshell.parser.ast

class AndCommandNode(
  val left: CommandNode,
  val right: CommandNode
) : CommandNode {
  override fun <R> accept(visitor: NodeVisitor<R>): R {
    return visitor.visitAndCommand(this)
  }
}
