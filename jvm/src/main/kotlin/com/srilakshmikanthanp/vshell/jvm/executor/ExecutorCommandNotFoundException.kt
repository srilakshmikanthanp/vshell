package com.srilakshmikanthanp.vshell.jvm.executor

class ExecutorCommandNotFoundException(
  val command: String,
  message: String,
  cause: Throwable? = null
) : ExecutorException(message, cause)
