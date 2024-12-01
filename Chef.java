package Lab7;

//Simulates the task of preparing orders
public class Chef implements Runnable {
	private final OrderQueue orderQueue;
    private volatile boolean isRunning = true; // Controls thread execution

    public Chef(OrderQueue orderQueue) {
        this.orderQueue = orderQueue;
    }

    // Stops the thread execution gracefully
    public void stop() {
        isRunning = false;
    }

    @Override
    public void run() {
        try {
            while (isRunning) {
                Order order = orderQueue.dequeueOrder();
                prepareOrder(order);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Chef has stopped.");
    }

    // Simulates the process of preparing an order
    private void prepareOrder(Order order) throws InterruptedException {
        System.out.println("Chef is preparing: " + order.getOrderId() + " - " + order.getFoodItem());
        Thread.sleep(2000); // Simulates preparation time
    }
}
