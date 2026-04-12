package com.srilakshmikanthanp.vshell.jvm.shell

class VshellEndOfFileException(
  partialInput: String,
  message: String,
  cause: Throwable? = null,
) : RuntimeException(message, cause)
