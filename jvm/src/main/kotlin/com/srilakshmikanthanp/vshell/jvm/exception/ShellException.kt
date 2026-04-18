package com.srilakshmikanthanp.vshell.jvm.exception

open class ShellException(
  message: String? = null,
  cause: Throwable? = null
): RuntimeException(message, cause)
