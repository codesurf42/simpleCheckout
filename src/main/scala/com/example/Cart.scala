package com.example

object Cart {

  type StockItem = String
  val stockPrices = Map[StockItem, Int]("Apple" -> 60, "Orange" -> 25)

  def main(args: Array[String]): Unit = {
    println("Hello, world!")
  }

  def getPrice(in: List[StockItem]): Int = {
    // we could reduce it, but sooner or later we may need to print full receipt
    val counted = in.groupBy(e => e).map(e => (e._1, e._2.size))
    val afterPromos = promoOranges3F2(
      promoApplesB1G1(counted))

    // promotions based on quantity can be stacked up
    afterPromos.foldLeft(0){(basketValue, entry) =>
      basketValue + stockPrices(entry._1) * entry._2
    }
  }

  def promoApplesB1G1(original: Map[StockItem, Int]): Map[StockItem, Int] = {
    val item = "Apple"
    val newQntOpt = original.
      get(item).
      map(qnt => qnt / 2 + qnt % 2)
    newQntOpt.fold(original){ v =>
      original ++ Map(item -> v)
    }
  }

  // they are quite generic, it would be simple to parametrize them by values
  // delivered by some external system or database
  def promoOranges3F2(original: Map[StockItem, Int]): Map[StockItem, Int] = {
    val item = "Orange"
    val newQntOpt = original.
      get(item).
      map(qnt => qnt / 3 * 2 + qnt % 3)
    newQntOpt.fold(original){ v =>
      original ++ Map(item -> v)
    }
  }


  // Later we realized we want more safety on item types.
  // It is a first phase of refactoring, so let's introduce a bit better type
  /**
   * A better containter for product
   * @param itemName Name of the product
   */
  case class SaferStockItem(itemName: String) {
    // that will be validated with our stock base
    assert(stockPrices.contains(itemName), "not a valid name for item")

    // stock elements may come from different systems, they are not hard-coded here,
    // sometimes we may even need to match similar items as a one
    // In a typical shop, we would rather have some codes/hashes for our products,
    // so approach with case class allow us to migrate into such scenario too
  }

  // We just introduced new type, but we do not need to modify
  // essential core of our computation system yet (low risk change)
  implicit def saferListStockItemToListStockItem(in: List[SaferStockItem]): List[StockItem] = in.map(_.itemName)

}
