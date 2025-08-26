import scala.collection.Seq

ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / scalaVersion := "3.3.4"

lazy val root = (project in file("."))
  .settings(
    name := "BIG-FINAL-FRESH2DAY",

    // JavaFX platform detection
    libraryDependencies ++= {
      val osName = System.getProperty("os.name") match {
        case n if n.startsWith("Linux")   => "linux"
        case n if n.startsWith("Mac")     =>
          val arch = System.getProperty("os.arch")
          if (arch == "aarch64" || arch == "arm64") "mac-aarch64" else "mac"
        case n if n.startsWith("Windows") => "win"
        case _                            => throw new Exception("Unknown platform!")
      }
      // ✅ must return a Seq here
      Seq("base", "controls", "fxml", "graphics", "media", "swing", "web")
        .map(m => "org.openjfx" % s"javafx-$m" % "21.0.4" classifier osName)
    } ++ Seq(
      // ✅ Add ScalaFX + SQLite here in the same Seq
      "org.scalafx" %% "scalafx" % "21.0.0-R32",
      "org.xerial" % "sqlite-jdbc" % "3.45.3.0"
    )
  )
