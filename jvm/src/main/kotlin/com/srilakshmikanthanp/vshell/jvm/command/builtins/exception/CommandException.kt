package com.srilakshmikanthanp.vshell.jvm.command.builtins.exception

class CommandException(
  val exitCode: Int,
  message: String,
  cause: Throwable? = null
): IllegalArgumentException(message, cause)