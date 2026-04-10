package com.srilakshmikanthanp.vshell.jvm.runtime

class VshellInterruptedException(
  message: String,
  cause: Throwable? = null
) : RuntimeException(message, cause)
