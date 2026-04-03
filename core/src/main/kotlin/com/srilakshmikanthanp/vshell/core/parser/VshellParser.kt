package com.srilakshmikanthanp.vshell.core.parser

import com.srilakshmikanthanp.vshell.core.ast.Node

interface VshellParser {
  fun parse(input: String): Node
}
