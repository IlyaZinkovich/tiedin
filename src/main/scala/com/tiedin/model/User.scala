package com.tiedin.model


/** Entity class storing rows of table Users
  *  @param userId Database column USER_ID SqlType(INT), PrimaryKey
  *  @param firstName Database column FIRST_NAME SqlType(VARCHAR), Length(45,true)
  *  @param lastName Database column LAST_NAME SqlType(VARCHAR), Length(45,true)
  *  @param positionId Database column POSITION_ID SqlType(INT), Default(None) */
case class User(firstName: String, lastName: String, userId: Option[Long] = None, positionId: Option[Long] = None)