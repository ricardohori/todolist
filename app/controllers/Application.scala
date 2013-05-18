package controllers

import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import models.Task

object Application extends Controller {
  
  def index = Action {
    Redirect(routes.Application.tasks)
  }

  def tasks = Action {
    Ok(views.html.index(Task.all(), taskForm))
  }

  def newTask = Action { implicit request =>
    taskForm.bindFromRequest.value map { task =>
      Task.create(task)
      Redirect(routes.Application.index())
    } getOrElse BadRequest
  }

  def deleteTask(id: Long) = Action {
    Task.delete(id)
    Redirect(routes.Application.tasks)
  }

  val taskForm = Form(
    mapping(
      "label" -> nonEmptyText
    )(Task.apply)(Task.unapply)
  )

}