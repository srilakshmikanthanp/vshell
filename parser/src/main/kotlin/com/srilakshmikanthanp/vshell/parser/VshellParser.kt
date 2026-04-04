package com.srilakshmikanthanp.vshell.parser

import com.srilakshmikanthanp.vshell.parser.ast.Node

interface VshellParser {
  fun parse(input: String): Node
}
