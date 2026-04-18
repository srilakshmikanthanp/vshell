plugins {
  kotlin("jvm") version "2.2.0"
  antlr
}

group = "com.srilakshmikanthanp.vshell.jvm"
version = "1.0.0"

repositories {
  mavenCentral()
}

dependencies {
  implementation(platform(libs.koin.bom))
  implementation(libs.koin.core)
  implementation(project(":vshell-parser"))
  implementation(libs.kotlinx.coroutines.core)
  implementation(libs.kotlin.reflect)
  implementation(libs.antlr4.runtime)
  implementation(libs.commons.io)
  antlr(libs.antlr4)
  testImplementation(libs.kotlin.test)
}

tasks.test {
  useJUnitPlatform()
}

kotlin {
  jvmToolchain(21)
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
