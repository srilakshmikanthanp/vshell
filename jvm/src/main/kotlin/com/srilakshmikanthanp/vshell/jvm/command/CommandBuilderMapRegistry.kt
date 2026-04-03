package com.srilakshmikanthanp.vshell.jvm.command

class CommandBuilderMapRegistry : CommandBuilderRegistry {
  private val commandBuilders = mutableMapOf<String, CommandBuilder>()

  override fun register(name: String, aliases: Set<String>, command: CommandBuilder) {
    setOf(name, *aliases.toTypedArray()).forEach {
      if (commandBuilders.containsKey(it)) {
        throw IllegalArgumentException("CommandBuilder with name $it already exists")
      } else {
        commandBuilders[it] = command
      }
    }
  }

  override fun register(name: String, command: CommandBuilder) {
    register(name, emptySet(), command)
  }

  override fun register(command: CommandBuilder) {
    val descriptor = command::class.java.getAnnotation(CommandBuilderDescriptor::class.java) ?: throw IllegalArgumentException("CommandBuilder ${command::class.java.name} is missing CommandBuilderDescriptor annotation")
    register(descriptor.command, descriptor.aliases.toSet(), command)
  }

  override fun get(name: String): CommandBuilder? {
    return commandBuilders[name]
  }

  override fun getAll(): Set<String> {
    return commandBuilders.keys
  }

  override fun isExists(name: String): Boolean {
    return commandBuilders.containsKey(name)
  }

  override fun remove(name: String): CommandBuilder? {
    return commandBuilders.remove(name)
  }
}
