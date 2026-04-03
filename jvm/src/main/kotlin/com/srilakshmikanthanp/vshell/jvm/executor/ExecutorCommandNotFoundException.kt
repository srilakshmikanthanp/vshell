package com.srilakshmikanthanp.vshell.jvm.executor

class ExecutorCommandNotFoundException(
  val command: String,
  cause: Throwable? = null
) : ExecutorException("Command not found: $command", cause)
