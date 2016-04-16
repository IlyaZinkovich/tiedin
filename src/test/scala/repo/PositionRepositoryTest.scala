package repo

import com.tiedin.repo.{Position, PositionRepository}
import connection.H2DBComponent
import org.scalatest.FunSuite
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.time.{Millis, Seconds, Span}

/**
 * @author sky
 */
class PositionRepositoryTest extends FunSuite with PositionRepository with H2DBComponent with ScalaFutures {

  implicit val defaultPatience = PatienceConfig(timeout = Span(5, Seconds), interval = Span(500, Millis))

  test("Add new position") {
    val response = create(Position("EPAM Systems", "Junior Test Automation Engineer"))
    whenReady(response) { positionId =>
      assert(positionId === 3)
    }
  }

  test("Update position") {
    val response = update(Position("Exadel", "Junior Software Testing Engineer", Some(1)))
    whenReady(response) { res =>
      assert(res === 1)
    }
  }

  test("Delete position") {
    val response = delete(2)
    whenReady(response) { res =>
      assert(res === 2)
    }
  }

  test("Get position list") {
    val positionList = getAll()
    whenReady(positionList) { result =>
      assert(result === List(Position("EPAM Systems", "Software Engineer", Some(1)),
        Position("Adform", "Senior Software Engineer", Some(2))))
    }
  }

}
