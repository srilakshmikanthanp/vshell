package com.srilakshmikanthanp.vshell.core.ast

sealed interface Node {
  fun <R> accept(visitor: NodeVisitor<R>): R
}
