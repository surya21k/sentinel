public class RateLimitPolicy {
    private int maxRequests;
    private int windowSeconds;
    private int currentCount;

    public RateLimitPolicy(int maxRequests, int windowSeconds) {
        if (maxRequests <= 0) {
            throw new InvalidPolicyException("maxRequests must be greater than 0, got: " + maxRequests);
        }
        if (windowSeconds <= 0) {
            throw new InvalidPolicyException("windowSeconds must be greater than 0, got: " + windowSeconds);
        }
        this.maxRequests = maxRequests;
        this.windowSeconds = windowSeconds;
        this.currentCount = 0;
    }

    public boolean allowRequest() {
        if (currentCount < maxRequests) {
            currentCount++;
            return true;
        }
        return false;
    }

    public void resetWindow() { this.currentCount = 0; }
    public int getCurrentCount() { return currentCount; }
    public int getMaxRequests() { return maxRequests; }
}