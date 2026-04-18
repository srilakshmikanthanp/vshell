package com.srilakshmikanthanp.vshell.jvm.command.builtins.exception

import com.srilakshmikanthanp.vshell.jvm.exception.ShellException

class ExitException(
  val status: Int,
  message: String? = null,
  cause: Throwable? = null
): ShellException(message, cause)