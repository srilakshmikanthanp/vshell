plugins {
  application
  kotlin("jvm") version "2.2.0"
  antlr
}

group = "com.srilakshmikanthanp.vshell.sample"
version = "1.0.0"

repositories {
  mavenCentral()
}

dependencies {
  implementation(platform(libs.koin.bom))
  implementation(libs.koin.core)
  implementation(project(":vshell-core"))
  implementation(project(":vshell-parser"))
  implementation(project(":vshell-jvm"))
  implementation(libs.kotlinx.coroutines.core)
  implementation(libs.kotlin.reflect)
  implementation(libs.jline)
  implementation(libs.apache.sshd.common)
  implementation(libs.apache.sshd.core)
  implementation(libs.apache.sshd.scp)
  implementation(libs.apache.sshd.sftp)
  implementation(libs.jline.remote.ssh)
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

application {
  mainClass.set("com.srilakshmikanthanp.vshell.sample.MainKt")
}
