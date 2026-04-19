package com.srilakshmikanthanp.vshell.jvm.executor

import com.srilakshmikanthanp.vshell.parser.VshellException

open class ExecutorException(
  message: String,
  cause: Throwable? = null
) : VshellException(message, cause)
