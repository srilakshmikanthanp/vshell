package com.srilakshmikanthanp.vshell.jvm.command.builtins

class InvalidCommandArgumentException(
  val exitCode: Int,
  message: String,
  cause: Throwable? = null
): IllegalArgumentException(message, cause)
