package com.srilakshmikanthanp.vshell.parser.ast

class PipeCommandNode(val left: CommandNode, val right: CommandNode) : CommandNode {
  override fun <R> accept(visitor: NodeVisitor<R>): R {
    return visitor.visitPipeCommand(this)
  }
}
