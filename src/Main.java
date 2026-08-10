public class Main {
    public static void main(String[] args) throws InterruptedException {
        RateLimitPolicy policy = new RateLimitPolicy(100, 60);

        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                policy.allowRequest();
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("Expected max allowed: 100");
        System.out.println("Actual currentCount: " + policy.getCurrentCount());
    }
}