package com.ffiaux.scala.model

/**
  * Created by ffiaux on 31/05/2016.
  */
class Employee(id: String, firstName: String, lastName: String, age: Int, email: String, phoneNumber: String, salary: Double) {
	var _id: String = id
	var _firstName: String = firstName
	var _lastName: String = lastName
	var _age: Int = age
	var _email: String = email
	var _phoneNumber: String = phoneNumber
	var _salary: Double = salary

	override def toString(): String = _id + " " + _firstName + " " + _lastName + " " + _age.toString + " " + _email + " " + _phoneNumber + " " + _salary.toString;
}
