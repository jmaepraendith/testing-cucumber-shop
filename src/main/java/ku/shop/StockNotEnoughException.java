package ku.shop;

public class StockNotEnoughException extends Exception {
    public StockNotEnoughException(String message) {
        super(message);
    }
}