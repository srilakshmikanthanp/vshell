package com.srilakshmikanthanp.vshell.core.ast

class PipeCommandNode(val left: CommandNode, val right: CommandNode) : CommandNode {
  override fun <R> accept(visitor: NodeVisitor<R>): R {
    return visitor.visitPipeCommand(this)
  }
}
