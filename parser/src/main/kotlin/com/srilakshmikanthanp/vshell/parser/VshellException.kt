package com.srilakshmikanthanp.vshell.parser

open class VshellException(
  override val message: String,
  cause: Throwable? = null
): RuntimeException(message, cause)