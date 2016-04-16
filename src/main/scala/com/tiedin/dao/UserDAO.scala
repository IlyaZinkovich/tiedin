package com.tiedin.dao

import java.sql.SQLException

import com.tiedin.config.Configuration
import com.tiedin.model.{Failure, FailureType, User}
import com.tiedin.tables.Tables._
import slick.driver.MySQLDriver.api._

import scala.concurrent.Future


object UserDAO extends Configuration {

    // the base query for the Users table
    val users = Users

    private val db = Database.forURL(url = "jdbc:mysql://%s:%d/%s".format(dbHost, dbPort, dbName),
        user = dbUser, password = dbPassword, driver = "com.mysql.jdbc.Driver")

    /**
     * Saves user entity to the database.
     *
     * @param user entity to create
     * @return saved user entity
     */
    def create(user: User): Either[Failure, Future[User]] = {
        val insertQuery = users returning users.map(_.userId) into ((item, id) => item.copy(userId = id))
        try {
            val action = insertQuery += user
            Right(db.run(action))
        } catch {
            case e: SQLException =>
                Left(databaseError(e))
        }
    }
    
    /**
     * Updates user entity with specified one.
     *
     * @param id       id of the user to update.
     * @param user updated user entity
     * @return updated user entity
     */
    def update(id: Long, user: User): Either[Failure, Boolean] = {
        try {
            users.filter(_.userId === id).update(user)
            Right(true)
        } catch {
            case e: SQLException =>
                Left(databaseError(e))
        }
    }

    /**
     * Deletes user from database.
     *
     * @param id id of the user to delete
     * @return deleted user entity
     */
    def delete(id: Long): Either[Failure, Boolean] = {
        try {
            users.filter(_.userId === id).delete
            Right(true)
        } catch {
            case e: SQLException =>
                Left(databaseError(e))
        }
    }

    /**
     * Retrieves specific user from database.
     *
     * @param id id of the user to retrieve
     * @return user entity with specified id
     */
    def get(id: Long): Either[Failure, Future[Option[User]]] = {
        def filterQuery(id: Long): Query[Users, User, Seq] =
            users.filter(_.userId === id)
        try {
            Right(db.run(filterQuery(id).result.headOption))
        } catch {
            case e: SQLException =>
                Left(databaseError(e))
        }
    }

    /**
     * Produce database error description.
     *
     * @param e SQL Exception
     * @return database error description
     */
    protected def databaseError(e: SQLException) =
        Failure("%d: %s".format(e.getErrorCode, e.getMessage), FailureType.DatabaseFailure)

    /**
     * Produce user not found error description.
     *
     * @param userId id of the user
     * @return not found error description
     */
    protected def notFoundError(userId: Long) =
        Failure("User with userId=%d does not exist".format(userId), FailureType.NotFound)
}
