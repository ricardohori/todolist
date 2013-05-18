import models.{Task, AppDB}
import org.specs2.mutable._

import play.api.test._
import play.api.test.Helpers._

import org.squeryl.PrimitiveTypeMode.inTransaction

/**
 * add your integration spec here.
 * An integration test will fire up a whole play application in a real (or headless) browser
 */
class TaskSpec extends Specification {

  "A Task" should{
    "be creatable" in {
      running(FakeApplication(additionalConfiguration = inMemoryDatabase())) {
        inTransaction {
          val task = AppDB.taskTable insert Task("foo")
          task.id mustNotEqual(0)
        }
      }
    }
  }
}