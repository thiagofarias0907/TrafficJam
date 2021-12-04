package instance.world.cells.State;


/*
    when the car will attempt to enter, it will first check if the road is available

 */
public class MyMutexBlocker extends DangerZoneHandler {


    @Override
    public boolean exitedDangerZone() {
      super.setAvailability(true);
      return true;
    }

    @Override
    public boolean enterTheDangerZone() {
        if(super.isAvailable()){
//            super.setAvailability(false);
            return true;
        }
        return false;
    }
}
