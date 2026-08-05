public class RateLimitPolicy {
    private int maxRequests;
    private int windowSeconds;
    private int currentCount;

    public RateLimitPolicy( int maxRequests, int windowSeconds) {
        this.currentCount = 0;
        this.maxRequests = maxRequests;
        this.windowSeconds = windowSeconds;
    }
    public boolean allowRequest(){
        if(currentCount < maxRequests){
            currentCount++;
            return  true;
        }
        return false;
    }
    public void resetWindow(){
        this.currentCount = 0;
    }

    public int getCurrentCount() {
        return currentCount;
    }

    public int getMaxRequests() {
        return maxRequests;
    }
}
