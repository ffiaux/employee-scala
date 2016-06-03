package com.ffiaux.scala

import scala.io.Source

/**
  * Created by ffiaux on 02/06/2016.
  */
object ReadFromStdin {
	def main(args: Array[String]) {
		var line: Int = 0
		do {
			Console.println("Menu")
			Console.println("1 - Listar")
			Console.println("2 - Relatorio")
			Console.println("3 - Sair")

			println("--> Escolha a opcao")
			line = scala.io.StdIn.readInt()
		} while(line != 3);
	}
}
