package Lab7;

//Main class to execute the Restaurant Management System
public class RestaurantManagementSystem {

	public static void main(String[] args) {
		final int QUEUE_CAPACITY = 5; // Maximum number of orders in the queue
        final int SYSTEM_RUNTIME = 10000; // System runtime in milliseconds (10 seconds)

        OrderQueue sharedOrderQueue = new OrderQueue(QUEUE_CAPACITY);

        OrderTaker orderTaker = new OrderTaker(sharedOrderQueue);
        Chef chef = new Chef(sharedOrderQueue);

        Thread orderTakerThread = new Thread(orderTaker, "OrderTakerThread");
        Thread chefThread = new Thread(chef, "ChefThread");

        // Start threads
        orderTakerThread.start();
        chefThread.start();

        // Run the system for the specified runtime
        try {
            Thread.sleep(SYSTEM_RUNTIME);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Stop threads gracefully
        System.out.println("Stopping the system...");
        orderTaker.stop();
        chef.stop();

        // Wait for threads to finish
        try {
            orderTakerThread.join();
            chefThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Restaurant Management System has stopped.");

	}

}
