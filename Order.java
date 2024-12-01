package Lab7;

//Represents a customer order
public class Order {
	private final int orderId;
    private final String foodItem;

    public Order(int orderId, String foodItem) {
        this.orderId = orderId;
        this.foodItem = foodItem;
    }

    public int getOrderId() {
        return orderId;
    }

    public String getFoodItem() {
        return foodItem;
    }
}

