import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

  val appName = "play-cdn"
  val appVersion = "1.0-SNAPSHOT"

  val appDependencies = Seq()

  val main = PlayProject(appName, appVersion, appDependencies, mainLang = SCALA).settings(
    organization := "egraphs",
    
    mappings in (Compile, packageBin) ~= { (ms: Seq[(File, String)]) =>
      ms filterNot {
        case (file, toPath) =>
          toPath == "routes" ||
            toPath == "Routes.class" ||
            toPath == "Routes$.class" ||
            toPath == "Routes$$anonfun$routes$1.class" ||
            toPath == "Routes$$anonfun$routes$1$$anonfun$apply$1.class" ||
            toPath == "Routes$$anonfun$routes$1$$anonfun$apply$3.class" ||
            toPath == "Routes$$anonfun$routes$1$$anonfun$apply$1$$anonfun$apply$2.class" ||
            toPath == "Routes$$anonfun$routes$1$$anonfun$apply$3$$anonfun$apply$4.class" ||
            toPath == "controllers/javascript/ReverseAssets.class" ||
            toPath == "controllers/ref/ReverseAssets.class" ||
            toPath == "controllers/ref/ReverseAssets$$anonfun$at$1.class" ||
            toPath == "controllers/ReverseAssets.class" ||
            toPath == "controllers/routes.class" ||
            toPath == "controllers/routes$javascript.class" ||
            toPath == "controllers/routes$ref.class"
      }
    })

}
