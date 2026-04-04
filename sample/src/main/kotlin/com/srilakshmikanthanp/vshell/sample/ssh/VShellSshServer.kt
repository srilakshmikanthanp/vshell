package com.srilakshmikanthanp.vshell.sample.ssh

import org.apache.sshd.server.SshServer
import org.apache.sshd.server.auth.password.PasswordAuthenticator
import org.apache.sshd.server.keyprovider.SimpleGeneratorHostKeyProvider
import org.apache.sshd.server.session.ServerSession
import org.jline.builtins.ssh.Ssh
import org.jline.builtins.ssh.Ssh.ShellParams
import java.util.function.Supplier

class VShellSshServer(private val username: String, private val password: String, private val port: Int) {
  private val passwordAuthenticator: PasswordAuthenticator = PasswordAuthenticator { username: String, password: String, session: ServerSession ->
    username == this@VShellSshServer.username && password == this@VShellSshServer.password
  }

  private val serverSupplier: Supplier<SshServer> = Supplier<SshServer> {
    val server: SshServer = SshServer.setUpDefaultServer()
    server.passwordAuthenticator = this@VShellSshServer.passwordAuthenticator
    server.keyPairProvider = SimpleGeneratorHostKeyProvider()
    server
  }

  private val shellSupplier = { params: ShellParams ->
    VshellSshClient(params).run()
  }

  fun start() {
    val ssh = Ssh(
      shellSupplier,
      null,
      serverSupplier,
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
