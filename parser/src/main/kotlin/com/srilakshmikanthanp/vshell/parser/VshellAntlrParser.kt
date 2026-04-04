package com.srilakshmikanthanp.vshell.parser

import com.srilakshmikanthanp.vshell.core.VShellLexer
import com.srilakshmikanthanp.vshell.core.VShellParser
import com.srilakshmikanthanp.vshell.parser.ast.Node
import com.srilakshmikanthanp.vshell.parser.visitor.AstBuilderVisitor
import org.antlr.v4.runtime.BailErrorStrategy
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream

class VshellAntlrParser : VshellParser {
  override fun parse(input: String): Node {
    val stream = CharStreams.fromString(input)
    val lexer = VShellLexer(stream)
    lexer.removeErrorListeners()
    lexer.addErrorListener(VshellParserErrorListener())
    val tokenStream = CommonTokenStream(lexer)
    val parser = VShellParser(tokenStream)
    parser.errorHandler = BailErrorStrategy()
    parser.removeErrorListeners()
    parser.addErrorListener(VshellParserErrorListener())
    val parseTree = parser.commands()
    val astBuilder = AstBuilderVisitor()
    return astBuilder.visit(parseTree)
  }
}