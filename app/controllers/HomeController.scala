package controllers

import javax.inject._

import models.{Answer, QA}
import play.api._
import play.api.data.Forms._
import play.api.data._
import play.api.libs.json
import play.api.libs.json.{JsValue, Json}
import play.api.mvc._
import scala.io.Source

/**
  * This controller creates an `Action` to handle HTTP requests to the
  * application's home page.
  */
@Singleton
class HomeController @Inject() extends Controller {

  val counter=1

  /**
    * Create an Action to render an HTML page with a welcome message.
    * The configuration in the `routes` file means that this method
    * will be called when the application receives a `GET` request with
    * a path of `/`.
    */
  def lolfn() = Action {

    val entity:QA=new QA(1)
    checkAnswer(1)
    Ok(entity.question+"\n"+entity.a+" "+entity.b+" "+entity.c+" "+entity.d)
  }


  val form:Form[Answer]=Form(
    mapping(
      "answerCode" -> text
    )(Answer.apply)(Answer.unapply)
  )

  def checkAnswer(n:Int)=Action {implicit request =>
    val answer=form.bindFromRequest().get
    // Ok(answer.answerCode)
    Redirect(routes.HomeController.checkAnswer(n+1))
  }
/*
  def subpage_handler() = Action{
    Ok(views.html.subpage())
  }
*/

  def index() = Action {
    Ok(views.html.index("Your new application is ready."))


  }

}
