package com.srilakshmikanthanp.vshell.jvm.runtime

import com.srilakshmikanthanp.vshell.parser.VshellAntlrParser
import com.srilakshmikanthanp.vshell.parser.VshellParser
import com.srilakshmikanthanp.vshell.parser.VshellSyntaxException
import com.srilakshmikanthanp.vshell.jvm.command.CommandBuilderRegistry
import com.srilakshmikanthanp.vshell.jvm.command.builtins.ExitException
import com.srilakshmikanthanp.vshell.jvm.context.Context
import com.srilakshmikanthanp.vshell.jvm.executor.CommandsShellNode
import com.srilakshmikanthanp.vshell.jvm.executor.ExecutorCommandNotFoundException
import com.srilakshmikanthanp.vshell.jvm.executor.ExecutorException
import com.srilakshmikanthanp.vshell.jvm.executor.ExecutorVisitor
import com.srilakshmikanthanp.vshell.jvm.executor.substitution.StdOutSubstitutor
import kotlinx.coroutines.Runnable
import org.apache.commons.io.input.CloseShieldInputStream
import org.apache.commons.io.output.CloseShieldOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream

class VshellRuntime(
  val context: Context,
  val reader: VshellReader,
  val stdIn: InputStream,
  val stdOut: OutputStream,
  val stdErr: OutputStream,
): Runnable {
  private val executorVisitor = ExecutorVisitor(context, StdOutSubstitutor())
  private val parser : VshellParser = VshellAntlrParser()

  override fun run() {
    while (true) {
      try {
        val commandStdOut = CloseShieldOutputStream.wrap(stdOut)
        val commandStdIn = CloseShieldInputStream.wrap(stdIn)
        val commandStdErr = CloseShieldOutputStream.wrap(stdErr)
        val input = reader.read(context)
        val tree = parser.parse(input)
        val node = tree.accept(executorVisitor) as? CommandsShellNode ?: throw VshellSyntaxException("$input is not a command")
        node.commands.forEach { it.execute(commandStdIn, commandStdOut, commandStdErr) }
      } catch (e: ExitException) {
        stdOut.write("Exiting...\n".toByteArray())
        break
      } catch (e: VshellEndOfFileException){
        stdOut.write("Closing...\n".toByteArray())
        break
      } catch (e: ExecutorCommandNotFoundException) {
        stdErr.write("No such command ${e.command}\n".toByteArray())
      } catch (e: ExecutorException) {
        stdErr.write("Error executing command: ${e.message}\n".toByteArray())
      } catch (e: VshellSyntaxException) {
        stdErr.write("Syntax error: ${e.message}\n".toByteArray())
      } catch (e: VshellInterruptedException) {
        stdErr.write("Interrupted\n".toByteArray())
      } catch (e: Exception) {
        stdErr.write("Error: ${e.message}\n".toByteArray())
      }
    }
  }
}
