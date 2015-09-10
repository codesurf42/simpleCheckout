import com.example.Cart
import com.example.Cart.{SaferStockItem, StockItem}
import org.scalatest._

class CartSpec extends FlatSpec with Matchers {
  "Cart" should "have tests" in {
    true should === (true)
  }

  "Cart" should "compute prices" in {
    val basket1 = List[StockItem]("Orange", "Apple")
    Cart.getPrice(basket1) shouldBe (Cart.stockPrices("Apple") + Cart.stockPrices("Orange"))

    val basket2 = List[StockItem]("Orange", "Apple", "Orange", "Orange")
    Cart.getPrice(basket2) shouldBe (Cart.stockPrices("Apple") + 3 * Cart.stockPrices("Orange"))
  }

  "Cart" should "computer prices with extended types" in {
    val basket3 = List[SaferStockItem](
      SaferStockItem("Orange"),
       SaferStockItem("Apple")
      )
    Cart.getPrice(basket3) shouldBe (Cart.stockPrices("Apple") + Cart.stockPrices("Orange"))
  }
}
