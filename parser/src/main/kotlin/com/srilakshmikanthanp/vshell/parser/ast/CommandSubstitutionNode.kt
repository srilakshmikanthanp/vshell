package com.srilakshmikanthanp.vshell.parser.ast

class CommandSubstitutionNode(val commandNode: CommandNode) : ExpressionNode {
  override fun <R> accept(visitor: NodeVisitor<R>): R {
    return visitor.visitCommandSubstitution(this)
  }
}

fun ExpressionNode.asVShellCommandSubstitutionNode(): CommandSubstitutionNode {
  return this as? CommandSubstitutionNode
    ?: throw NodeMisMatchException(
      listOf(CommandSubstitutionNode::class),
      this::class
    )
}
