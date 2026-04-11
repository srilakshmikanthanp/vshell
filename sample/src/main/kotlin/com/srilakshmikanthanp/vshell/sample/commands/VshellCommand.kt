package com.srilakshmikanthanp.vshell.sample.commands

import com.srilakshmikanthanp.vshell.jvm.command.Command
import com.srilakshmikanthanp.vshell.jvm.command.CommandBuilder
import com.srilakshmikanthanp.vshell.jvm.command.CommandBuilderDescriptor
import com.srilakshmikanthanp.vshell.jvm.command.builtins.ExitException
import com.srilakshmikanthanp.vshell.jvm.context.Context
import com.srilakshmikanthanp.vshell.jvm.evaluator.VshellEvaluator
import com.srilakshmikanthanp.vshell.jvm.io.println
import org.jline.reader.EndOfFileException
import org.jline.reader.LineReader
import org.jline.reader.UserInterruptException
import java.io.InputStream
import java.io.OutputStream

class VshellCommand(
  private val reader: LineReader,
  private val hostname: String,
  private val username: String,
  private val context: Context,
  private val args: List<String>,
): Command {
  private fun loop(evaluator: VshellEvaluator) {
    while (true) {
      try {
        evaluator.evaluate(reader.readLine("$$username@$hostname:${evaluator.context.getCurrentWorkingDirectory()}> "))
      } catch (e: EndOfFileException) {
        evaluator.stdOut.println("End of file")
        break
      } catch (e: ExitException) {
        evaluator.stdOut.println("Exit: ${e.message}")
        break
      } catch (e: UserInterruptException) {
        evaluator.stdOut.println("Interrupted")
      } catch (e: Exception) {
        evaluator.stdErr.println("Error: ${e.message}")
      }
    }
  }

  override fun execute(
    stdIn: InputStream,
    stdOut: OutputStream,
    stdErr: OutputStream
  ): Int {
    context.eventSource.enterScope().use {
      this.loop(VshellEvaluator(context, stdIn, stdOut, stdErr))
      return 0
    }
  }

  @CommandBuilderDescriptor("vshell")
  class VshellCommandBuilder(
    private val reader: LineReader,
    private val hostname: String,
    private val username: String
  ) : CommandBuilder {
    override fun build(context: Context, args: List<String>): Command {
      return VshellCommand(this.reader, this.hostname, this.username, Context.withParentContext(context), args)
    }
  }
}
