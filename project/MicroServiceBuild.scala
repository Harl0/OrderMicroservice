import sbt._

object MicroServiceBuild extends Build with MicroService {

  import scala.util.Properties.envOrElse

  val appName = "OrderMicroservice"
  val appVersion = envOrElse("OrderMicroservice", "999-SNAPSHOT")

  override lazy val appDependencies: Seq[ModuleID] = AppDependencies()
}

private object AppDependencies {

  import play.sbt.PlayImport._

  private val microserviceBootstrapVersion = "5.8.0"
  private val playHealthVersion = "2.0.0"
  private val playConfigVersion = "3.0.0"
  private val logbackJsonLoggerVersion = "3.1.0"
  private val playReactiveMongoVersion = "5.1.0"
  private val akkaXMLParserVersion = "0.13.0"
  private val serviceContractCacheVersion = "0.10.0"
  private val hmrcTestVersion = "2.2.0"

  val compile = Seq(
    ws,
    cache,
    "uk.gov.hmrc" %% "microservice-bootstrap" % microserviceBootstrapVersion,
    "uk.gov.hmrc" %% "play-health" % playHealthVersion,
    "uk.gov.hmrc" %% "play-config" % playConfigVersion,
    "uk.gov.hmrc" %% "logback-json-logger" % logbackJsonLoggerVersion,
    "uk.gov.hmrc" %% "play-reactivemongo" % playReactiveMongoVersion,
    "uk.gov.hmrc" %% "akka-xml-parser" % akkaXMLParserVersion,
    "uk.gov.hmrc" %% "service-contract-cache" % serviceContractCacheVersion,
    "org.scalatestplus.play" % "scalatestplus-play_2.11" % "1.5.1"
  )

  trait TestDependencies {
    lazy val test: Seq[ModuleID] = ???
  }

  object Test {
    def apply() = new TestDependencies {
      override lazy val test = Seq(
        "uk.gov.hmrc" %% "hmrctest" % hmrcTestVersion % "it,test",
        "org.mockito" % "mockito-core" % "1.9.0" % "test",
        "uk.gov.hmrc" %% "reactivemongo-test" % "1.6.0" % "it"
      )
    }.test
  }

  def apply() = compile ++ Test()
}

