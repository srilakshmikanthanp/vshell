package com.srilakshmikanthanp.vshell.jvm.executor

import kotlin.reflect.KClass

class ExecutionShellNodeMisMatchException(
  val expectedTypes: List<KClass<out ExecutionShellNode>>,
  val actualNode: KClass<out ExecutionShellNode>,
  message: String = "Expected one of ${expectedTypes.map { it.simpleName }} but got ${actualNode.simpleName}",
  cause: Throwable? = null
) : ExecutionShellNodeException(message, cause)
