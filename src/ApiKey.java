public class ApiKey {
    private String keyValue;
    private boolean isActive;
    private RateLimitPolicy policy;

    public ApiKey(String keyValue,RateLimitPolicy policy) {
        this.isActive = true;
        this.keyValue = keyValue;
        this.policy = policy;
    }

    public String getKeyValue() {
        return keyValue;
    }
    public boolean isActive() {
        return isActive;
    }
    public void deactivate(){
        this.isActive = false;
    }
    public boolean canMakeRequest(){
        return isActive && policy.allowRequest();
    }
}
