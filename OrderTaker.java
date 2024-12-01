package Lab7;

//Simulates the task of taking customer orders
public class OrderTaker implements Runnable {
	private final OrderQueue orderQueue;
    private volatile boolean isRunning = true; // Controls thread execution

    public OrderTaker(OrderQueue orderQueue) {
        this.orderQueue = orderQueue;
    }

    // Stops the thread execution gracefully
    public void stop() {
        isRunning = false;
    }

    @Override
    public void run() {
        int orderId = 1;
        String[] menuItems = {"Pizza", "Burger", "Pasta", "Biryani", "Shawarma", "Waffles"};
        try {
            while (isRunning) {
                String foodItem = getFoodItemFromMenu(menuItems, orderId);
                Order order = new Order(orderId++, foodItem);
                orderQueue.enqueueOrder(order);
                Thread.sleep(1000); // Simulates delay between taking orders
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Order Taker has stopped.");
    }

    // Utility method to cycle through menu items
    private String getFoodItemFromMenu(String[] menuItems, int orderId) {
        return menuItems[orderId % menuItems.length];
    }
}

