package com.srilakshmikanthanp.vshell.jvm.shell

import com.srilakshmikanthanp.vshell.parser.VshellException

class VshellInterruptException(
  message: String,
  partialInput: String?=null,
  cause: Throwable? = null
): VshellException(message, cause)
