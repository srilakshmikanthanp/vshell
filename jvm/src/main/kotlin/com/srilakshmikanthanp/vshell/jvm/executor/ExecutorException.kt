package com.srilakshmikanthanp.vshell.jvm.executor

open class ExecutorException(
  message: String,
  cause: Throwable? = null
) : Exception(message, cause)
