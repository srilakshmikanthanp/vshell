package com.srilakshmikanthanp.vshell.sample.ssh

import org.jline.builtins.ssh.Ssh.ShellParams
import java.util.function.Consumer

class VshellSshClientSupplier : Consumer<ShellParams> {
  override fun accept(params: ShellParams) {
    VshellSshClient(params).run()
  }
}
