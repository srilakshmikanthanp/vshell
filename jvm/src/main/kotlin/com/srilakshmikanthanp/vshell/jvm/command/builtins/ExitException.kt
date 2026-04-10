package com.srilakshmikanthanp.vshell.jvm.command.builtins

class ExitException(
  val status: Int,
  message: String? = null,
  cause: Throwable? = null
): Exception(message, cause)
