package com.srilakshmikanthanp.vshell.jvm.command

interface CommandBuilderRegistry {
  fun register(name: String, command: CommandBuilder)
  fun get(name: String): CommandBuilder?
  fun getAll(): Map<String, CommandBuilder>
  fun isExists(name: String): Boolean
  fun remove(name: String): CommandBuilder?
}
