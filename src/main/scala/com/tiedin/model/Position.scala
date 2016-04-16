package com.tiedin.model


/** Entity class storing rows of table Positions
  *  @param positionId Database column POSITION_ID SqlType(INT), AutoInc, PrimaryKey
  *  @param companyName Database column COMPANY_NAME SqlType(VARCHAR), Length(45,true)
  *  @param title Database column TITLE SqlType(VARCHAR), Length(45,true) */
case class Position(positionId: Long, companyName: String, title: String)