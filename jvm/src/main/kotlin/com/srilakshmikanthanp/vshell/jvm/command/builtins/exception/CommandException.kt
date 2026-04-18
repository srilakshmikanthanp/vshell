package com.srilakshmikanthanp.vshell.jvm.command.builtins.exception

import com.srilakshmikanthanp.vshell.jvm.exception.ShellException

class CommandException(
  val exitCode: Int,
  message: String,
  cause: Throwable? = null
): ShellException(message, cause)