sbtPlugin := true

organization := "com.typesafe.sbt"
name := "sbt-multi-jvm"

bintrayRepository := "sbt-plugins"
bintrayOrganization := Some("sbt-multi-jvm")

publishMavenStyle := false
licenses += ("Apache-2.0", url("https://www.apache.org/licenses/LICENSE-2.0.html"))
crossSbtVersions := Seq("0.13.16", "1.0.0-RC3")

//addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.14.5")
libraryDependencies += {
  val currentSbtVersion = (sbtBinaryVersion in pluginCrossBuild).value
  Defaults.sbtPluginExtra("com.eed3si9n" % "sbt-assembly" % "0.14.5", currentSbtVersion, scalaBinaryVersion.value)
}

scalacOptions ++= List(
  "-unchecked",
  "-deprecation",
  "-language:_",
  "-encoding", "UTF-8"
)
scalacOptions ++= {
  CrossVersion.partialVersion(scalaVersion.value) match {
    case Some((2, n)) if n < 12 => Seq("-target:jvm-1.6")
    case _                      => Nil
  }
}

versionWithGit
git.baseVersion := "0.3.12"
