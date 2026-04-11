package com.srilakshmikanthanp.vshell.jvm.command.builtins

class CommandException(
  val exitCode: Int,
  message: String,
  cause: Throwable? = null
): IllegalArgumentException(message, cause)
