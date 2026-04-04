package com.srilakshmikanthanp.vshell.parser.ast

sealed interface Node {
  fun <R> accept(visitor: NodeVisitor<R>): R
}
