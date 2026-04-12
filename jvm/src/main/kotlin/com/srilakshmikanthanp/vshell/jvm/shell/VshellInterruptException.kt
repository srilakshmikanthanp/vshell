package com.srilakshmikanthanp.vshell.jvm.shell

class VshellInterruptException(
  partialInput: String,
  message: String,
  cause: Throwable? = null
): RuntimeException(message, cause)