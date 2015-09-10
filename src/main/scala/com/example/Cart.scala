package com.example

object Cart {

  type StockItem = String
  val stockPrices = Map[StockItem, Int]("Apple" -> 60, "Orange" -> 25)

  def main(args: Array[String]): Unit = {
    println("Hello, world!")
  }

  def getPrice(in: List[StockItem]): Int = in.foldLeft(0){(basketValue, item) =>
    basketValue + stockPrices(item)
  }

  // Later we realized we want more safety on item types.
  // It is a first phase of refactoring, so let's introduce a bit better type
  /**
   * A better containter for product
   * @param itemName Name of the product
   */
  case class SaferStockItem(itemName: String) {
    // that will be validated with our stock base
    assert(stockPrices.contains(itemName))

    // stock elements may come from different systems, they are not hard-coded here,
    // sometimes we may even need to match similar items as a one
    // In a typical shop, we would rather have some codes/hashes for our products,
    // so approach with case class allow us to migrate into such scenario too
  }

  // We just introduced new type, but we do not need to modify
  // essential core of our computation system yet (low risk change)
  implicit def saferListStockItemToListStockItem(in: List[SaferStockItem]): List[StockItem] = in.map(_.itemName)

}
