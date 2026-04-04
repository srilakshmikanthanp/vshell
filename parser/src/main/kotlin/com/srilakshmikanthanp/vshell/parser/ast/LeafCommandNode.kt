package com.srilakshmikanthanp.vshell.parser.ast

class LeafCommandNode(val identifierNode: IdentifierNode, val arguments: List<Node>) : CommandNode {
  override fun <R> accept(visitor: NodeVisitor<R>): R {
    return visitor.visitLeafCommand(this)
  }
}
