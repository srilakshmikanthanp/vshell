package com.srilakshmikanthanp.vshell.jvm.command

interface CommandBuilderRegistry {
  fun register(name: String, aliases: Set<String>, command: CommandBuilder)
  fun register(name: String, command: CommandBuilder)
  fun register(command: CommandBuilder)
  fun get(name: String): CommandBuilder?
  fun getAll(): Set<String>
  fun isExists(name: String): Boolean
  fun remove(name: String): CommandBuilder?
}
