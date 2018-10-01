

import io.gatling.core.Predef._
import io.gatling.http.Predef._


class BasicSimulation extends Simulation {

  final val baseURL = ""

  val httpConf = http
    .baseURL("http://localhost:8080")
//    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
//    .doNotTrackHeader("1")
//    .acceptLanguageHeader("en-US,en;q=0.5")
//    .acceptEncodingHeader("gzip, deflate")
//    .userAgentHeader("Mozilla/5.0 (Windows NT 5.1; rv:31.0) Gecko/20100101 Firefox/31.0")

//  val scn = scenario("BasicSimulation")
//    .exec(http("GetRequest")
//    .post("http://localhost:8080/ws/api/v1/orders/")
//      .body(ElFileBody("Buy.json")).asJSON
//      .header("Content-Type", "application/json")
//    .check(status.is(200)))
//    .pause(5)

  val scn = scenario("BasicSimulation")
  .exec(
    http("Buy")
      .post("http://localhost:8080/ws/api/v1/orders/")
      .body(StringBody("{\n  \"id\": \"ikl11444\",\n  \"orderNumber\": \"twenty one\",\n  \"sourceSystem\": \"none\",\n  \"userId\": \"3129\",\n  \"userEmail\": \"firstuser@gmail.com\"\n}"))
      .check(
        status.is(200))
      .asJSON)
    .pause(5)

  setUp(
    scn.inject(atOnceUsers(1))
  ).protocols(httpConf)
}

