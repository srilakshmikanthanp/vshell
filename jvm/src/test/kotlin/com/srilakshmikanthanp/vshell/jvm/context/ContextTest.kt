package com.srilakshmikanthanp.vshell.jvm.context

import com.srilakshmikanthanp.vshell.jvm.event.SimpleEventSource
import java.nio.file.Path
import kotlin.test.*

class ContextTest {
  @Test
  fun shouldSetGetAndRemoveEnvironmentVariables() {
    val context = Context(Path.of("/home/test"), SimpleEventSource())

    context.setEnvironmentVariable("PATH", "/bin")
    assertEquals("/bin", context.getEnvironmentVariable("PATH"))
    assertTrue(context.removeEnvironmentVariable("PATH"))
    assertNull(context.getEnvironmentVariable("PATH"))
    assertFalse(context.removeEnvironmentVariable("PATH"))
  }

  @Test
  fun shouldSetGetAndRemoveLocalVariables() {
    val context = Context(Path.of("/home/test"), SimpleEventSource())
    context.setVariable("name", "vshell")
    assertEquals("vshell", context.getVariable("name"))
    assertTrue(context.removeVariable("name"))
    assertNull(context.getVariable("name"))
    assertFalse(context.removeVariable("name"))
  }

  @Test
  fun shouldResolveVariablesAndEnvironmentThroughParentContext() {
    val parent = Context(Path.of("/parent"), SimpleEventSource())
    parent.setEnvironmentVariable("HOME", "/parent")
    parent.setVariable("project", "vshell")

    val child = Context(Path.of("/child"), SimpleEventSource(), parent)
    assertEquals("/parent", child.findEnvironmentVariable("HOME"))
    assertEquals("vshell", child.findVariable("project"))
    assertNull(child.findEnvironmentVariable("MISSING"))
    assertNull(child.findVariable("MISSING"))
  }

  @Test
  fun shouldPreferCurrentContextValuesOverParentValues() {
    val parent = Context(Path.of("/parent"), SimpleEventSource())
    parent.setEnvironmentVariable("JAVA_HOME", "/parent/jdk")
    parent.setVariable("target", "parent")

    val child = Context(Path.of("/child"), SimpleEventSource(), parent)
    child.setEnvironmentVariable("JAVA_HOME", "/child/jdk")
    child.setVariable("target", "child")
    assertEquals("/child/jdk", child.findEnvironmentVariable("JAVA_HOME"))
    assertEquals("child", child.findVariable("target"))
  }


  @Test
  fun shouldReturnSnapshotsForAllVariablesMaps() {
    val parent = Context(Path.of("/parent"), SimpleEventSource())
    parent.setEnvironmentVariable("HOME", "/parent")
    parent.setVariable("project", "vshell")

    val child = Context(Path.of("/child"), SimpleEventSource(), parent)
    child.setEnvironmentVariable("HOME", "/child")

    val allEnvVars = child.getAllEnvironmentVariables()
    val allVars = child.getAllVariables()
    assertEquals(mapOf("HOME" to "/child"), allEnvVars)
    assertEquals(mapOf("project" to "vshell"), allVars)
  }
}
