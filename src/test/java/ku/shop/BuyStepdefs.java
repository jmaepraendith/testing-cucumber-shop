package ku.shop;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BuyStepdefs {

    private ProductCatalog catalog;
    private Order order;
    private Exception exception;

    @Test
    public void testAddItemWithInsufficientStock() {
        Product prod = new Product("Bread", 20.50, 5);
        Order order = new Order();

        Exception exception = assertThrows(StockNotEnoughException.class, () -> {
            order.addItem(prod, 20);
        });

        String expectedMessage = "Not enough stock for product: Bread. Available: 5, requested: 20";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }


    @Given("the store is ready to service customers")
    public void the_store_is_ready_to_service_customers() {
        catalog = new ProductCatalog();
        order = new Order();
    }

    @Given("a product {string} with price {float} and stock of {int} exists")
    public void a_product_exists(String name, double price, int stock) {
        catalog.addProduct(name, price, stock);
    }

    @When("I buy {string} with quantity {int}")
    public void i_buy_with_quantity(String name, int quantity) {
        Product prod = catalog.getProduct(name);
        try {
            prod.checkStock(prod, quantity);
            order.addItem(prod, quantity);
        } catch (StockNotEnoughException e) {
            exception = e;
        }

    }

    @Then("Not enough stock for product: {string}. Available: {int}, requested: {int}")
    public void not_enough_stock_for_product(String name, int stock, int quantity) {
        String expectedMessage = "Not enough stock for product: " + name + ". Available: " + stock + ", requested: " + quantity;
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Then("total should be {float}")
    public void total_should_be(double total) {
        assertEquals(total, order.getTotal());
    }
}

