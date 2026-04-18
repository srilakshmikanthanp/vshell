plugins {
  kotlin("jvm") version "2.2.0"
  antlr
}

group = "com.srilakshmikanthanp.vshell.core"
version = "1.0.0"

repositories {
  mavenCentral()
}

dependencies {
  implementation(platform(libs.koin.bom))
  implementation(libs.koin.core)
  implementation(libs.kotlinx.coroutines.core)
  implementation(libs.kotlin.reflect)
  implementation(libs.antlr4.runtime)
  antlr(libs.antlr4)
  implementation(project(":vshell-core"))
  testImplementation(libs.kotlin.test)
  testImplementation("org.junit.jupiter:junit-jupiter-params")
}

tasks.test {
  useJUnitPlatform()
}

kotlin {
  jvmToolchain(17)
}

tasks.generateGrammarSource {
  arguments = arguments + listOf("-long-messages", "-no-listener", "-visitor", "-package", "com.srilakshmikanthanp.vshell.core")
  outputDirectory = file("${layout.buildDirectory.get()}/generated-src/antlr/main/java/com/srilakshmikanthanp/vshell/core")
}

tasks.compileKotlin {
  dependsOn(tasks.generateGrammarSource)
}

tasks.compileTestKotlin {
  dependsOn(tasks.generateTestGrammarSource)
}

sourceSets.main {
  java.srcDir("${layout.buildDirectory.get()}/generated-src/antlr/main/java")
}
