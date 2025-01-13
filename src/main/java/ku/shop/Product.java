package ku.shop;

public class Product {
    private double price;
    private String name;
    private int stock;

    public Product(String name, double price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public void checkStock(Product prod ,int quantity) throws StockNotEnoughException {
        if (quantity > this.getStock()) {
            String message = "Not enough stock for product: " + prod.getName() +
                    ". Available: " + prod.getStock() + ", requested: " + quantity;
            throw new StockNotEnoughException(message);
        }
    }

    public void cutStock(int quantity) {
        stock -= quantity;
    }

    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    public int getStock() {
        return stock;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPrice(double price) {
        this.price = price;
    }
}
