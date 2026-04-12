package com.srilakshmikanthanp.vshell.jvm.shell

import com.srilakshmikanthanp.vshell.jvm.command.builtins.exception.ExitException
import com.srilakshmikanthanp.vshell.jvm.context.Context
import com.srilakshmikanthanp.vshell.jvm.executor.CommandsShellNode
import com.srilakshmikanthanp.vshell.jvm.executor.ExecutorCommandNotFoundException
import com.srilakshmikanthanp.vshell.jvm.executor.ExecutorException
import com.srilakshmikanthanp.vshell.jvm.executor.ExecutorVisitor
import com.srilakshmikanthanp.vshell.jvm.executor.substitution.StdOutSubstitutor
import com.srilakshmikanthanp.vshell.jvm.io.println
import com.srilakshmikanthanp.vshell.parser.VshellAntlrParser
import com.srilakshmikanthanp.vshell.parser.VshellParser
import com.srilakshmikanthanp.vshell.parser.VshellSyntaxException
import org.apache.commons.io.input.CloseShieldInputStream
import org.apache.commons.io.output.CloseShieldOutputStream
import java.io.InputStream
import java.io.OutputStream

class VshellRepl(
  val reader: VshellReader,
  val context: Context,
  val stdIn: InputStream,
  val stdOut: OutputStream,
  val stdErr: OutputStream
): Runnable {
  private val executorVisitor = ExecutorVisitor(context, StdOutSubstitutor())
  private val parser : VshellParser = VshellAntlrParser()

  private fun evaluate(input: String) {
    val commandStdOut = CloseShieldOutputStream.wrap(stdOut)
    val commandStdIn = CloseShieldInputStream.wrap(stdIn)
    val commandStdErr = CloseShieldOutputStream.wrap(stdErr)
    val tree = parser.parse(input)
    val node = tree.accept(executorVisitor) as? CommandsShellNode ?: throw VshellSyntaxException("$input is not a command")
    node.commands.forEach { it.execute(commandStdIn, commandStdOut, commandStdErr) }
  }

  override fun run() {
    while (true) {
      try {
        this.evaluate(reader.read(context))
      } catch (e: VshellEndOfFileException) {
        stdOut.println("End of Input")
        break
      } catch (e: ExitException) {
        stdOut.println("Exit: ${e.message}")
        break
      } catch (e: ExecutorCommandNotFoundException) {
        stdErr.println("No such command ${e.command}")
      } catch (e: ExecutorException) {
        stdErr.println("Error executing command: ${e.message}")
      } catch (e: VshellSyntaxException) {
        stdErr.println("Syntax error: ${e.message}")
      } catch (e: VshellInterruptException) {
        stdOut.println("Interrupted")
      } catch (e: Exception) {
        stdErr.println("Error: ${e.message}")
      }
    }
  }
}
