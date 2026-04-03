package com.srilakshmikanthanp.vshell.jvm.executor.substitution

import com.srilakshmikanthanp.vshell.jvm.command.Command

interface CommandSubstitutor {
  fun substitute(command: Command): String
}
