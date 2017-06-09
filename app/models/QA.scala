package models

import play.api.libs.json.{JsValue, Json}

import scala.io.Source

case class QA(num:Int) {
  val source: String = Source.fromFile("app/assets/jsons/sources.json").getLines.mkString
  val json: JsValue = Json.parse(source)
  val question=(json \ num.toString \ "question").get.as[String]
  val a=(json \ num.toString \ "a").get.as[String]
  val b=(json \ num.toString \ "b").get.as [String]
  val c=(json \ num.toString \ "c").get.as[String]
  val d=(json \ num.toString \ "d").get.as[String]
  val answer=(json \ num.toString \ "answer").get.as[String]

}
