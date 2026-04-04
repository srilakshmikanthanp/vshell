package com.srilakshmikanthanp.vshell.parser.ast

class TemplateStringNode(val segments: List<ExpressionNode>) : ExpressionNode {
  override fun <R> accept(visitor: NodeVisitor<R>): R {
    return visitor.visitTemplateString(this)
  }
}
