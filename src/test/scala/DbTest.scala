import com.typesafe.slick.testkit.util.{TestDB, ExternalJdbcTestDB, DriverTest, Testkit}
import org.junit.runner.RunWith
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.time.{Seconds, Span}
import org.scalatest.{BeforeAndAfter, FunSuite}
import slick.dbio.DBIO
import slick.driver.MySQLDriver
import slick.jdbc.{ResultSetInvoker, ResultSetAction}

import scala.concurrent.ExecutionContext

@RunWith(classOf[Testkit])
class UserDAOTest extends DriverTest(UserDAOTest.tdb)

object UserDAOTest {
    def tdb = new ExternalJdbcTestDB("mypostgres") {
        val driver = MySQLDriver
        override def localTables(implicit ec: ExecutionContext): DBIO[Vector[String]] =
            ResultSetAction[(String,String,String, String)](_.conn.getMetaData().getTables("", "public", null, null)).map { ts =>
                ts.filter(_._4.toUpperCase == "TABLE").map(_._3).sorted
            }

        override def getLocalSequences(implicit session: profile.Backend#Session) = {
            val tables = ResultSetInvoker[(String,String,String, String)](_.conn.getMetaData().getTables("", "public", null, null))
            tables.buildColl[List].filter(_._4.toUpperCase == "SEQUENCE").map(_._3).sorted
        }
        override def capabilities = super.capabilities - TestDB.capabilities.jdbcMetaGetFunctions
    }

}