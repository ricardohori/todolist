package models

import anorm._
import anorm.SqlParser._
import org.squeryl.{Schema, KeyedEntity}
import org.squeryl.PrimitiveTypeMode._

/**
 * Created with IntelliJ IDEA.
 * User: rfh
 * Date: 5/18/13
 * Time: 1:38 AM
 * To change this template use File | Settings | File Templates.
 */
case class Task(label: String) extends KeyedEntity[Long]{
  val id: Long = 0
}

object AppDB extends Schema {
  val taskTable = table[Task]("task")
}

object Task {

  def all(): List[Task] = inTransaction {
    from(AppDB.taskTable)(taskTable =>
      select(taskTable)
    ).toList
  }

  def create(task: Task){
    inTransaction {
      AppDB.taskTable insert task
    }
  }

  def delete(id: Long) {
    inTransaction {
      AppDB.taskTable delete id
    }
  }

  val task = {
    get[Long]("id") ~
      get[String]("label") map {
      case id~label => Task(label)
    }
  }

}
