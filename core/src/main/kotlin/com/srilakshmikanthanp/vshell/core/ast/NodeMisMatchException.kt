package com.srilakshmikanthanp.vshell.core.ast

import kotlin.reflect.KClass

class NodeMisMatchException(
  val expectedTypes: List<KClass<out Node>>,
  val actualNode: KClass<out Node>,
  message: String = "Expected one of ${expectedTypes.map { it.simpleName }} but got ${actualNode.simpleName}",
  cause: Throwable? = null
) : NodeException(message, cause)
