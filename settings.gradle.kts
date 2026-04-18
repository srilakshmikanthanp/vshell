plugins {
  id("org.gradle.toolchains.foojay-resolver-convention") version "0.9.0"
}

rootProject.name = "vshell"

include("core", "parser", "jvm", "sample")

project(":core").name = "vshell-core"
project(":parser").name = "vshell-parser"
project(":jvm").name = "vshell-jvm"
project(":sample").name = "vshell-sample"
