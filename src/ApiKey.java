public class ApiKey {
    private String keyValue;
    private boolean isActive;

    public ApiKey(String keyValue) {
        this.isActive = true;
        this.keyValue = keyValue;
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
}
