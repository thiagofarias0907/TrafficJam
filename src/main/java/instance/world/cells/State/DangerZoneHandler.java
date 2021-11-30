package instance.world.cells.State;



public abstract class DangerZoneHandler {

    private boolean availability;

    public DangerZoneHandler() {
        this.availability = false;
    }

    public boolean isAvailable() {
        return availability;
    }

    public abstract boolean exitedDangerZone();

    public abstract boolean enterTheDangerZone();

    protected void setAvailability(boolean availability) {
        this.availability = availability;
    }
}
