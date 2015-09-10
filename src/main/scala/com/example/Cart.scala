package com.example

object Cart {

  type stockItem = String
  val stockPrices = Map[stockItem, Int]("Apple" -> 60, "Orange" -> 25)

  def main(args: Array[String]): Unit = {
    println("Hello, world!")
  }

  def getPrice(in: List[stockItem]): Int = in.foldLeft(0){(basketValue, item) =>
    basketValue + stockPrices(item)
  }

}
