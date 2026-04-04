package com.srilakshmikanthanp.vshell.sample

import com.srilakshmikanthanp.vshell.sample.ssh.VShellSshServer

fun main(args: Array<String>) {
  val username = System.getProperty("user.name")
  val password = if (args.isEmpty()) "password" else args[0]
  val ssh = VShellSshServer(username, password, 2222)
  ssh.start()
  Thread.currentThread().join()
}
