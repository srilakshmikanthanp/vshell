package com.srilakshmikanthanp.vshell.sample.ssh

import org.apache.sshd.server.auth.password.PasswordAuthenticator
import org.apache.sshd.server.session.ServerSession

class VshellSshServerPasswordAuthenticator(
  private val username: String,
  private val password: String
) : PasswordAuthenticator {
  override fun authenticate(
    username: String?,
    password: String?,
    session: ServerSession?
  ): Boolean {
    return username == this.username && password == this.password
  }
}
