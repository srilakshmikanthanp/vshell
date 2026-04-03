package com.srilakshmikanthanp.vshell.core.ast

class TemplateStringNode(val segments: List<ExpressionNode>) : ExpressionNode {
  override fun <R> accept(visitor: NodeVisitor<R>): R {
    return visitor.visitTemplateString(this)
  }
}
