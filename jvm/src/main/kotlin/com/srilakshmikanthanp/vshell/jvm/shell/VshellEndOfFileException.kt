package com.srilakshmikanthanp.vshell.jvm.shell

class VshellEndOfFileException(
  partialInput: String?=null,
  message: String?=null,
  cause: Throwable? = null,
) : RuntimeException(message, cause)
