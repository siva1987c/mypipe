import sbt._
import Keys._

object ApplicationBuild extends Build {
  override lazy val settings = super.settings ++
    Seq(
      name := "mypipe",
      version := "0.0.1",
      organization := "mypipe",
      scalaVersion := "2.10.3",
      resolvers ++= Seq(Resolver.mavenLocal,
        "Sonatype OSS Releases" at "http://oss.sonatype.org/content/repositories/releases/",
        "Sonatype OSS Snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/",
        "Typesafe Snapshots" at "http://repo.typesafe.com/typesafe/snapshots/",
        "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/")
    )

  val appDependencies = Seq(
    "org.specs2" %% "specs2" % "1.13",
    "com.typesafe.akka" %% "akka-actor" % "2.2.3",
		"com.github.shyiko" % "mysql-binlog-connector-java" % "0.1.0-SNAPSHOT"
  )

  lazy val root = Project(id = "mypipe",
    base = file("."),
    settings = Project.defaultSettings ++ Seq(
      libraryDependencies ++= appDependencies
    ) ++ Format.settings
  )
}

object Format {
  import com.typesafe.sbt.SbtScalariform._

  lazy val settings = scalariformSettings ++ Seq(
    ScalariformKeys.preferences := formattingPreferences
  )

  lazy val formattingPreferences = {
    import scalariform.formatter.preferences._
    FormattingPreferences().
      setPreference(AlignParameters, true).
      setPreference(AlignSingleLineCaseStatements, true).
      setPreference(DoubleIndentClassDeclaration, true).
      setPreference(IndentLocalDefs, true).
      setPreference(IndentPackageBlocks, true).
      setPreference(IndentSpaces, 2).
      setPreference(MultilineScaladocCommentsStartOnFirstLine, true).
      setPreference(PreserveSpaceBeforeArguments, false).
      setPreference(PreserveDanglingCloseParenthesis, false).
      setPreference(RewriteArrowSymbols, true).
      setPreference(SpaceBeforeColon, false).
      setPreference(SpaceInsideBrackets, false).
      setPreference(SpacesWithinPatternBinders, true)
  }
}

