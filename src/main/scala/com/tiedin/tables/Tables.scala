package com.tiedin.tables

import com.tiedin.model.User
import com.tiedin.model.Position

// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
object Tables extends {
  val profile = slick.driver.MySQLDriver
} with Tables

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait Tables {
  val profile: slick.driver.JdbcProfile
  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}

  /** DDL for all tables. Call .create to execute. */
  lazy val schema: profile.SchemaDescription = Positions.schema ++ Users.schema
  @deprecated("Use .schema instead of .ddl", "3.0")
  def ddl = schema

  /** GetResult implicit for fetching PositionsRow objects using plain SQL queries */
  implicit def GetResultPositionsRow(implicit e0: GR[Long], e1: GR[String]): GR[Position] = GR{
    prs => import prs._
    Position.tupled((<<[Long], <<[String], <<[String]))
  }
  /** Table description of table positions. Objects of this class serve as prototypes for rows in queries. */
  class Positions(_tableTag: Tag) extends Table[Position](_tableTag, "positions") {
    def * = (positionId, companyName, title) <> (Position.tupled, Position.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(positionId), Rep.Some(companyName), Rep.Some(title)).shaped.<>({r=>import r._; _1.map(_=> Position.tupled((_1.get, _2.get, _3.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column POSITION_ID SqlType(INT), AutoInc, PrimaryKey */
    val positionId: Rep[Long] = column[Long]("POSITION_ID", O.AutoInc, O.PrimaryKey)
    /** Database column COMPANY_NAME SqlType(VARCHAR), Length(45,true) */
    val companyName: Rep[String] = column[String]("COMPANY_NAME", O.Length(45,varying=true))
    /** Database column TITLE SqlType(VARCHAR), Length(45,true) */
    val title: Rep[String] = column[String]("TITLE", O.Length(45,varying=true))
  }
  /** Collection-like TableQuery object for table Positions */
  lazy val Positions = new TableQuery(tag => new Positions(tag))

  /** GetResult implicit for fetching UsersRow objects using plain SQL queries */
  implicit def GetResultUsersRow(implicit e0: GR[Long], e1: GR[String], e2: GR[Option[Long]]): GR[User] = GR{
    prs => import prs._
    User.tupled((<<[String], <<[String], <<?[Long], <<?[Long]))
  }
  /** Table description of table users. Objects of this class serve as prototypes for rows in queries. */
  class Users(_tableTag: Tag) extends Table[User](_tableTag, "users") {
    def * = (firstName, lastName, userId, positionId) <> (User.tupled, User.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(firstName), Rep.Some(lastName), Rep.Some(userId), positionId).shaped.<>({r=>import r._; _1.map(_=> User.tupled((_1.get, _2.get, _3.get, _4)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column USER_ID SqlType(INT), PrimaryKey */
    val userId: Rep[Option[Long]] = column[Option[Long]]("USER_ID", O.PrimaryKey, O.AutoInc)
    /** Database column FIRST_NAME SqlType(VARCHAR), Length(45,true) */
    val firstName: Rep[String] = column[String]("FIRST_NAME", O.Length(45,varying=true))
    /** Database column LAST_NAME SqlType(VARCHAR), Length(45,true) */
    val lastName: Rep[String] = column[String]("LAST_NAME", O.Length(45,varying=true))
    /** Database column POSITION_ID SqlType(INT), Default(None) */
    val positionId: Rep[Option[Long]] = column[Option[Long]]("POSITION_ID", O.Default(None))

    /** Foreign key referencing Positions (database name POSITION_FK) */
    lazy val positionsFk = foreignKey("POSITION_FK", positionId, Positions)(r => Rep.Some(r.positionId), onUpdate=ForeignKeyAction.Cascade, onDelete=ForeignKeyAction.Cascade)
  }
  /** Collection-like TableQuery object for table Users */
  lazy val Users = new TableQuery(tag => new Users(tag))
}