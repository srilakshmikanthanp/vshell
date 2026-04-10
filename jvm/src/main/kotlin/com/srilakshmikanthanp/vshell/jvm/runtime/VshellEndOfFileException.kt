package com.srilakshmikanthanp.vshell.jvm.runtime

class VshellEndOfFileException(
  message: String,
  cause: Throwable? = null
) : RuntimeException(message, cause)