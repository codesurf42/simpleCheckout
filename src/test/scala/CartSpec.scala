import com.example.Cart
import com.example.Cart.stockItem
import org.scalatest._

class CartSpec extends FlatSpec with Matchers {
  "Cart" should "have tests" in {
    true should === (true)
  }

  "Cart" should "compute prices" in {
    val basket1 = List[stockItem]("Orange", "Apple")
    Cart.getPrice(basket1) shouldBe (Cart.stockPrices("Apple") + Cart.stockPrices("Orange"))

    val basket2 = List[stockItem]("Orange", "Apple", "Orange", "Orange")
    Cart.getPrice(basket2) shouldBe (Cart.stockPrices("Apple") + 3 * Cart.stockPrices("Orange"))
  }
}
