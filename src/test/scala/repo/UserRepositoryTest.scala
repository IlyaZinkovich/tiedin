package repo

import com.tiedin.repo.{User, UserRepository}
import connection.H2DBComponent
import org.scalatest.FunSuite
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.time.{ Millis, Seconds, Span }

/**
 * @author sky
 */
class UserRepositoryTest extends FunSuite with UserRepository with H2DBComponent with ScalaFutures {

  implicit val defaultPatience = PatienceConfig(timeout = Span(5, Seconds), interval = Span(500, Millis))

  test("Add new user") {
    val response = create(User("Gary", "Nelson"))
    whenReady(response) { userId =>
      assert(userId === 2)
    }
  }

  test("Update Bab Purson") {
    val response = update(User("Bob", "Person", Some(1), Some(1)))
    whenReady(response) { res =>
      assert(res === 1)
    }
  }

  test("Delete Bab") {
    val response = delete(1)
    whenReady(response) { res =>
      assert(res === 1)
    }
  }

  test("Get user list") {
    val user = getAll()
    whenReady(user) { result =>
      assert(result === List(User("Bab", "Purson", Some(1), Some(1))))
    }
  }

}