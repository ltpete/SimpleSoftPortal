lazy val root = (project in file(".")).settings(
  name := "SimpleSoftPortal",
  version := "1.0",
  scalaVersion := "2.11.7",
  
  scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8"),
  
  libraryDependencies ++= {
    val akkaV = "2.3.9"
    val sprayV = "1.3.3"
    val activateV = "1.7"
    Seq(
      "io.spray"            %%  "spray-can"         % sprayV,
      "io.spray"            %%  "spray-routing"     % sprayV,
      
      "com.typesafe.akka"   %%  "akka-actor"        % akkaV,
      
      "net.fwbrasil"        %% "activate-core"      % activateV,
      "net.fwbrasil"        %% "activate-prevayler" % activateV,
      
      "io.spray"            %%  "spray-testkit"     % sprayV  % "test",
      "com.typesafe.akka"   %%  "akka-testkit"      % akkaV   % "test",
      "org.specs2"          %%  "specs2-core"       % "3.6.4" % "test"
    )
  }
    
)

EclipseKeys.withSource := true
