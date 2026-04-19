package com.srilakshmikanthanp.vshell.jvm.command.builtins.exception

import com.srilakshmikanthanp.vshell.parser.VshellException

class CommandException(
  val exitCode: Int,
  message: String,
  cause: Throwable? = null
): VshellException(message, cause)
