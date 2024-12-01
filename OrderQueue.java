package Lab7;
import java.util.LinkedList;
import java.util.Queue;

//Shared resource to manage orders between threads
public class OrderQueue {
	private final Queue<Order> orderQueue = new LinkedList<>();
    private final int maxCapacity;

    public OrderQueue(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    // Adds an order to the queue (thread-safe)
    public synchronized void enqueueOrder(Order order) throws InterruptedException {
        while (orderQueue.size() == maxCapacity) {
            wait(); // Wait if the queue is full
        }
        orderQueue.add(order);
        System.out.println("Order Added: " + order.getOrderId() + " - " + order.getFoodItem());
        notifyAll(); // Notify waiting threads
    }

    // Retrieves an order from the queue (thread-safe)
    public synchronized Order dequeueOrder() throws InterruptedException {
        while (orderQueue.isEmpty()) {
            wait(); // Wait if the queue is empty
        }
        Order order = orderQueue.poll();
        notifyAll(); // Notify waiting threads
        return order;
    }
}

