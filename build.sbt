organization := "org.simple"

name := "SimpleSoftPortal"

version := "1.0"

scalaVersion := "2.11.7"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8", "-feature")

libraryDependencies ++= {
  val akkaV = "2.3.11"
  val sprayV = "1.3.3"
  val activateV = "1.7"
  
  Seq(
    "io.spray"            %%  "spray-can"         % sprayV,
    "io.spray"            %%  "spray-routing"     % sprayV,
    "io.spray"            %%  "spray-json"        % "1.3.2",
    
    "com.typesafe.akka"          %%  "akka-actor"   % akkaV,
    "com.typesafe.scala-logging" %% "scala-logging" % "3.1.0",
    
    "net.fwbrasil"        %% "activate-core"       % activateV,
    "net.fwbrasil"        %% "activate-prevayler"  % activateV,
    "net.fwbrasil"        %% "activate-spray-json" % activateV,
    
    "ch.qos.logback"      %   "logback-classic"   % "1.1.2",
        
    "io.spray"            %%  "spray-testkit"     % sprayV  % "test",
    "com.typesafe.akka"   %%  "akka-testkit"      % akkaV   % "test",
    "org.specs2"          %%  "specs2-core"       % "3.6.4" % "test"
  )
}

EclipseKeys.withSource := true
