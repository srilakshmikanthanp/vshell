package com.srilakshmikanthanp.vshell.jvm.context

import java.nio.file.Path
import java.util.function.Supplier

class Context(
  val homeDirectory: Path,
  val environmentVariables: ContextVariables = ContextVariables(),
  val localVariables: ContextVariables = ContextVariables(),
  val parentContext: Context? = null
) {
  private var currentWorkingDirectory: Path = homeDirectory
  private var lastCommandExitStatus: Int = 0
  private val specialVariables = ContextVariables()

  init {
    specialVariables.set(LAST_EXIT_STATUS_VARIABLE_NAME) { this.getLastCommandExitStatus().toString() }
  }

  fun findEnvironmentVariable(name: String): Supplier<String>? {
    return this.environmentVariables.get(name) ?: this.parentContext?.findEnvironmentVariable(name)
  }

  fun findVariable(name: String): Supplier<String>? {
    return this.localVariables.get(name) ?: this.parentContext?.findVariable(name)
  }

  fun findSpecialVariable(name: String): Supplier<String>? {
    return specialVariables.get(name)
  }

  fun getCurrentWorkingDirectory(): Path {
    return this.currentWorkingDirectory
  }

  fun setCurrentWorkingDirectory(path: Path) {
    this.currentWorkingDirectory = path
  }

  fun findReference(name: String): Supplier<String>? {
    return this.findSpecialVariable(name) ?: this.findVariable(name) ?: this.findEnvironmentVariable(name)
  }

  fun setLastCommandExitStatus(status: Int) {
    this.lastCommandExitStatus = status
  }

  fun getLastCommandExitStatus(): Int {
    return this.lastCommandExitStatus
  }

  companion object {
    const val LAST_EXIT_STATUS_VARIABLE_NAME = "_rc"

    fun withParentContext(parentContext: Context): Context {
      return Context(homeDirectory = parentContext.homeDirectory, parentContext = parentContext)
    }
  }
}
