package com.srilakshmikanthanp.vshell.jvm.command

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class CommandBuilderDescriptor(
  val command: String,
  val aliases: Array<String> = [],
)
