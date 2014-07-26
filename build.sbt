import com.typesafe.sbt.SbtNativePackager._
import NativePackagerKeys._

name := "sbt-native-package-server"

version := "1.0"

libraryDependencies ++= Seq(
    "org.apache.logging.log4j" % "log4j-api" % "2.0-rc1",
    "org.apache.logging.log4j" % "log4j-core" % "2.0-rc1",
    "com.typesafe" % "config" % "1.2.1"
)

// what class starts the server
mainClass in Compile := Some("core.Server")

// include the server settings
packageArchetype.java_server

// global package settings
packageDescription := "Custom application configuration"

// Maintainer for all packages
maintainer := "Nepomuk Seiler <nepomuk.seiler@mukis.de>"

packageSummary in Linux := "Logging Server Application"

// Map the default config files to the universal mappings
mappings in Universal <++= sourceDirectory  map { src =>
    val resources = src / "main" / "resources"
    val log4j = resources / "log4j2.xml"
    val reference = resources / "reference.conf"
    Seq(log4j -> "conf/log4j2.xml", reference -> "conf/application.conf")
}
