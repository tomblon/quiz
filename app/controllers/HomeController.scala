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

  var counter=0
  var questionsNumber=0

  /**
    * Create an Action to render an HTML page with a welcome message.
    * The configuration in the `routes` file means that this method
    * will be called when the application receives a `GET` request with
    * a path of `/`.
    */
  def printQuestion(n:Int) = Action {
    try {
      val entity: QA = new QA(n)
      Ok(views.html.subpage(n, entity.question, entity.a, entity.b, entity.c ,entity.d))
    }
    catch{
      case ex:NoSuchElementException => Ok(views.html.end("Your score:"+counter+"/"+questionsNumber))
    }
  }

  val form:Form[Answer]=Form(
    mapping(
      "answerCode" -> text
    )(Answer.apply)(Answer.unapply)
  )

  def checkAnswer(n:Int)=Action {implicit request =>
    questionsNumber+=1
    val entity:QA=new QA(n)
    val answer=form.bindFromRequest().get
    if (answer.answerCode==entity.answer) {
        counter+=1
        //Ok("good answer" + counter)
    }
    Redirect(routes.HomeController.printQuestion(n+1))
  }

  def checkAnswerOnClick(n:Int, answer: String )=Action {implicit request =>
    questionsNumber+=1
    val entity:QA=new QA(n)
    if (answer == entity.answer) {
      counter+=1
      //Ok("good answer" + counter)
    }
    Redirect(routes.HomeController.printQuestion(n+1))
  }

  def index() = Action {
    counter=0
    questionsNumber=0
    Ok(views.html.index("Start quiz"))
  }
}
