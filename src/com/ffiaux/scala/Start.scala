package com.ffiaux.scala

import com.ffiaux.scala.model.Employee

import scala.collection.mutable.ListBuffer
import scala.io.Source

/**
  * Created by ffiaux on 31/05/2016.
  */
object Start {
	def main(args: Array[String]) {
		val empList = loadFuncioarios
		menu(empList)
	}

	def menu(empList: ListBuffer[Employee]) : Unit = {
		var opcao: Int = 0
		do {
			Console.println("Menu")
			Console.println("1 - Listar")
			Console.println("2 - Relatorio")
			Console.println("3 - Sair")

			println("--> Escolha a opcao")
			opcao = scala.io.StdIn.readInt()
			if ( opcao == 1 ) {
				empList.foreach(emp => println(emp.toString()))
				printInputMessage("Pressione qualquer tecla para continuar...")
			}
			else if ( opcao == 2 ) {
				relatorioFuncionarios(empList)
				printInputMessage("Pressione qualquer tecla para continuar...")
			}

		} while( opcao != 3 );

		printInputMessage("Pressione qualquer tecla para finalizar...")
	}

	def relatorioFuncionarios(empList: ListBuffer[Employee]) : Unit = {
		var idMaiorSalario = getIdMaiorSalario(empList)
		var idMenorSalario = getIdMenorSalario(empList)

		println("--- Maior salario: " + printEmployee(idMaiorSalario, empList))
		println("--- Menor salario: " + printEmployee(idMenorSalario, empList))

		val mediaSalarial: Double = getMediaSalarial(empList)
		println("--- Media salarial: " + "%1.2f".formatLocal(new java.util.Locale("en"), mediaSalarial))

		val mediaIdade: Int = getMediaIdade(empList)
		println("--- Media idade: " + mediaIdade)

		println("--- Acima media salarial: " + getTotalFuncionariosAcimaMediaSalarial(empList, mediaSalarial))
		println("--- Abaixo media salarial: " + getTotalFuncionariosAbaixoMediaSalarial(empList, mediaSalarial))

		println("--- Acima media idade: " + getTotalFuncionariosAcimaMediaIdade(empList, mediaIdade))
		println("--- Abaixo media idade: " + getTotalFuncionariosAbaixoMediaIdade(empList, mediaIdade))
	}

	val printEmployee = (id: String, empList: ListBuffer[Employee]) => empList(id.toInt - 1).toString()
	val printInputMessage = (msg: String) => {
		println(msg)
		scala.io.StdIn.readLine()
	}

	def loadFuncioarios() : ListBuffer[Employee] = {
		val empList = ListBuffer[Employee]()

		for( line <- Source.fromFile("C:\\Users\\ffiaux\\Desktop\\IN_200_Funcionarios.txt").getLines() ) {
			val values = line.mkString.split(" ")
			val emp = new Employee(values(0).trim(), values(1).trim(), values(2).trim(), values(3).trim().toInt, values(4).trim(), values(5).trim(), values(6).trim().toDouble)
			empList += emp
		}

		empList
	}

	def getMediaSalarial(empList: ListBuffer[Employee]) : Double = {
		var mediaSalarial: Double = 0.0
		for(emp <- empList) {
			mediaSalarial += emp._salary
		}

		mediaSalarial / empList.size
	}

	def getTotalFuncionariosAcimaMediaSalarial(empList: ListBuffer[Employee], mediaSalarial: Double) : Int = {
		var total: Int = 0
		for(emp <- empList) {
			if (emp._salary > mediaSalarial)
			{
				total+=1;
			}
		}

		total
	}

	def getTotalFuncionariosAcimaMediaIdade(empList: ListBuffer[Employee], mediaIdade: Int) : Int = {
		var total: Int = 0
		for(emp <- empList) {
			if (emp._age > mediaIdade)
			{
				total+=1;
			}
		}

		total
	}

	def getTotalFuncionariosAbaixoMediaSalarial(empList: ListBuffer[Employee], mediaSalarial: Double) : Int = {
		var total: Int = 0
		for(emp <- empList) {
			if (emp._salary < mediaSalarial) {
				total+=1;
			}
		}

		total
	}

	def getTotalFuncionariosAbaixoMediaIdade(empList: ListBuffer[Employee], mediaIdade: Int) : Int = {
		var total: Int = 0
		for(emp <- empList) {
			if (emp._age < mediaIdade) {
				total+=1;
			}
		}

		total
	}

	def getMediaIdade(empList: ListBuffer[Employee]) : Int = {
		var mediaIdade: Int = 0
		for(emp <- empList) {
			mediaIdade += emp._age
		}

		mediaIdade / empList.size
	}

	def getIdMaiorSalario(empList: ListBuffer[Employee]) : String = {
		var idMaiorSalario: String = empList.head._id
		var maiorSalario: Double = empList.head._salary

		for(emp <- empList) {
			if(emp._salary > maiorSalario) {
				maiorSalario = emp._salary
				idMaiorSalario = emp._id
			}
		}

		idMaiorSalario
	}

	def getIdMenorSalario( empList: ListBuffer[Employee] ) : String = {
		var idMenorSalario: String = empList.head._id
		var menorSalario: Double = empList.head._salary

		for(emp <- empList) {
			if(emp._salary < menorSalario) {
				menorSalario = emp._salary
				idMenorSalario = emp._id
			}
		}

		idMenorSalario
	}
}