package com.srilakshmikanthanp.vshell.jvm.context

import com.srilakshmikanthanp.vshell.jvm.event.EventSource
import java.nio.file.Path

class Context(
  private val homeDirectory: Path,
  private val eventSource: EventSource,
  private val parentContext: Context? = null
) {
  private val environmentVariables: MutableMap<String, String> = mutableMapOf()
  private val localVariables: MutableMap<String, String> = mutableMapOf()
  private var currentWorkingDirectory: Path = homeDirectory

  fun setEnvironmentVariable(key: String, value: String) {
    this.environmentVariables[key] = value
  }

  fun removeEnvironmentVariable(key: String): Boolean {
    return this.environmentVariables.remove(key) != null
  }

  fun getEnvironmentVariable(key: String): String? {
    return this.environmentVariables[key]
  }

  fun findEnvironmentVariable(name: String): String? {
    return this.environmentVariables[name] ?: this.parentContext?.findEnvironmentVariable(name)
  }

  fun getAllEnvironmentVariables(): Map<String, String> {
    return (this.parentContext?.getAllEnvironmentVariables() ?: emptyMap()) + this.environmentVariables.toMap()
  }

  fun setVariable(key: String, value: String) {
    this.localVariables[key] = value
  }

  fun removeVariable(key: String): Boolean {
    return this.localVariables.remove(key) != null
  }

  fun getVariable(key: String): String? {
    return this.localVariables[key]
  }

  fun getAllVariables(): Map<String, String> {
    return (this.parentContext?.getAllVariables() ?: emptyMap()) + this.localVariables.toMap()
  }

  fun getCurrentWorkingDirectory(): Path {
    return this.currentWorkingDirectory
  }

  fun setCurrentWorkingDirectory(path: Path) {
    this.currentWorkingDirectory = path
  }

  fun getHomeDirectory(): Path {
    return this.homeDirectory
  }

  fun findVariable(name: String): String? {
    return this.localVariables[name] ?: this.parentContext?.findVariable(name)
  }

  fun getParentContext(): Context? {
    return this.parentContext
  }

  fun getEventSource(): EventSource {
    return this.eventSource
  }

  fun findReference(name: String): String? {
    return this.findVariable(name) ?: this.findEnvironmentVariable(name)
  }
}
