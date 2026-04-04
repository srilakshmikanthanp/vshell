package com.srilakshmikanthanp.vshell.parser

import org.antlr.v4.runtime.BaseErrorListener
import org.antlr.v4.runtime.RecognitionException
import org.antlr.v4.runtime.Recognizer

class VshellParserErrorListener : BaseErrorListener() {
  override fun syntaxError(
    recognizer: Recognizer<*, *>?,
    offendingSymbol: Any?,
    line: Int,
    charPositionInLine: Int,
    msg: String?,
    e: RecognitionException?
  ) {
    throw VshellSyntaxException("Error at $line:$charPositionInLine $msg")
  }
}
