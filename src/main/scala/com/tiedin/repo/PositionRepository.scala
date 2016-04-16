package com.tiedin.repo

import com.tiedin.connection.{MySqlDBComponent, DBComponent}

import scala.concurrent.Future

trait PositionRepository extends PositionTable { this: DBComponent =>

  import driver.api._

  def create(position: Position): Future[Int] = db.run { posistionTableAutoInc += position }

  def update(position: Position): Future[Int] = db.run { positionTableQuery.filter(_.positionId === position.positionId.get).update(position) }

  def getById(positionId: Int): Future[Option[Position]] = db.run { positionTableQuery.filter(_.positionId === positionId).result.headOption }

  def getAll(): Future[List[Position]] = db.run { positionTableQuery.to[List].result }

  def delete(positionId: Int): Future[Int] = db.run { positionTableQuery.filter(_.positionId === positionId).delete }

}

private[repo] trait PositionTable extends UserTable { this: DBComponent =>

  import driver.api._

  private[PositionTable] class PositionTable(tag: Tag) extends Table[Position](tag, "POSITIONS") {
    val positionId = column[Int]("POSITION_ID", O.PrimaryKey, O.AutoInc)
    val companyName = column[String]("COMPANY_NAME")
    val title = column[String]("TITLE")
    def * = (companyName, title, positionId.?) <> (Position.tupled, Position.unapply)

  }

  protected val positionTableQuery = TableQuery[PositionTable]

  protected def posistionTableAutoInc = positionTableQuery returning positionTableQuery.map(_.positionId)

}

object PositionRepository extends PositionRepository with MySqlDBComponent

case class Position(companyName: String, title: String, positionId: Option[Int] = None)
