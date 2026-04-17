package com.srilakshmikanthanp.vshell.sample.ssh

import org.jline.builtins.ssh.Ssh

class VshellSshServer(private val username: String, private val password: String, private val port: Int) {
  fun start() {
    val ssh = Ssh(
      VshellSshClientSupplier(),
      null,
      VshellSshServerSupplier(VshellSshServerPasswordAuthenticator(username, password)),
      null
    )

    ssh.sshd(
      System.out,
      System.err,
      arrayOf(
        "--ip=127.0.0.1",
        "--port=$port",
        "start"
      )
    )
  }
}
