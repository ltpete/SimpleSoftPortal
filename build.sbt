lazy val root = (project in file(".")).settings(
  name := "SimpleSoftPortal",
  version := "1.0",
  scalaVersion := "2.11.7",
  
  libraryDependencies ++= Seq(
    "net.fwbrasil" %% "activate-core" % "1.7",
    "net.fwbrasil" %% "activate-prevayler" % "1.7"
  )
    
)
