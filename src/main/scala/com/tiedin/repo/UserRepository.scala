package com.tiedin.repo

import com.tiedin.connection.{MySqlDBComponent, DBComponent}

import scala.concurrent.Future

trait UserRepository extends UserTable { this: DBComponent =>

  import driver.api._

  /**
   * @param user
   * create new user
   */
  def create(user: User): Future[Int] = db.run { userTableAutoInc += user }

  /**
   * @param user
   * update existing user
   */
  def update(user: User): Future[Int] = db.run { userTableQuery.filter(_.userId === user.userId.get).update(user) }

  /**
   * @param userId
   * Get user by id
   */
  def getById(userId: Int): Future[Option[User]] = db.run { userTableQuery.filter(_.userId === userId).result.headOption }

  /**
   * @return
   * Get all users
   */
  def getAll(): Future[List[User]] = db.run { userTableQuery.to[List].result }

  /**
   * @param userId
   * delete user by id
   */
  def delete(userId: Int): Future[Int] = db.run { userTableQuery.filter(_.userId === userId).delete }

}

private[repo] trait UserTable { this: DBComponent =>

  import driver.api._

  private[UserTable] class UserTable(tag: Tag) extends Table[User](tag, "USERS") {
    val userId = column[Int]("USER_ID", O.PrimaryKey, O.AutoInc)
    val firstName = column[String]("FIRST_NAME")
    val lastName = column[String]("LAST_NAME")
    val positionId = column[Int]("POSITION_ID")
    def * = (firstName, lastName, positionId.?, userId.?) <> (User.tupled, User.unapply)
    def position = foreignKey("user_position_fk", positionId, userTableQuery)(_.userId)
  }

  protected val userTableQuery = TableQuery[UserTable]

  protected def userTableAutoInc = userTableQuery returning userTableQuery.map(_.userId)

}

object UserRepository extends UserRepository with MySqlDBComponent

case class User(firstName: String, lastName: String, positionId: Option[Int] = None, userId: Option[Int] = None)
