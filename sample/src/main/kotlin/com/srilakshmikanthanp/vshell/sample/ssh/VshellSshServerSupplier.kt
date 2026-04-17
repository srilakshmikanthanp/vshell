package com.srilakshmikanthanp.vshell.sample.ssh

import org.apache.sshd.server.SshServer
import org.apache.sshd.server.auth.password.PasswordAuthenticator
import org.apache.sshd.server.keyprovider.SimpleGeneratorHostKeyProvider
import java.util.function.Supplier

class VshellSshServerSupplier(private val passwordAuthenticator: PasswordAuthenticator) : Supplier<SshServer> {
  override fun get(): SshServer {
    val server: SshServer = SshServer.setUpDefaultServer()
    server.passwordAuthenticator = passwordAuthenticator
    server.keyPairProvider = SimpleGeneratorHostKeyProvider()
    return server
  }
}
