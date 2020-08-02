package ejercicio2;

public class Lock {
    private Integer keyValue;
    private boolean locked;

    public Lock(Integer keyValue) {
        this.keyValue = keyValue;
        locked = true;
    }

    public boolean unlock (Integer keyValue) {
        if (this.keyValue == keyValue) {
            locked = false;
        } else {
            locked = true;
        }
        return !locked;
    }

    public void lock() {
        locked = true;
    }

    public boolean isLocked() {
        return locked;
    }
}
