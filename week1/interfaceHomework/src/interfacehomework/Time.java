package interfacehomework;
/**
 *
 * @author cj
 */
public class Time implements Comparison{
    
    private final long currentTime;
    
    Time() {
        currentTime = System.currentTimeMillis();
    }
    
    public long getCurrentTime() {
        return currentTime;
    }
    
    @Override
    public boolean isGreater(Object obj) {
        long timeOfObj = ((Time) obj).getCurrentTime();
        return getCurrentTime() > timeOfObj;
    }
    
    @Override
    public boolean isLess(Object obj) {
        long timeOfObj = ((Time) obj).getCurrentTime();
        return getCurrentTime() < timeOfObj;
    }
    
    @Override
    public boolean isEqual(Object obj) {
        long timeOfObj = ((Time) obj).getCurrentTime();
        return getCurrentTime() == timeOfObj;
    }
}
