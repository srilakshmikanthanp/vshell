package com.srilakshmikanthanp.vshell.jvm.command

class CommandBuilderMapRegistry : CommandBuilderRegistry {
  private val commandBuilders = mutableMapOf<String, CommandBuilder>()

  override fun register(name: String, command: CommandBuilder) {
    commandBuilders[name] = command
  }

  override fun get(name: String): CommandBuilder? {
    return commandBuilders[name]
  }

  override fun getAll(): Map<String, CommandBuilder> {
    return commandBuilders.toMap()
  }

  override fun isExists(name: String): Boolean {
    return commandBuilders.containsKey(name)
  }

  override fun remove(name: String): CommandBuilder? {
    return commandBuilders.remove(name)
  }
}
