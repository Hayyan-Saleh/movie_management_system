package Domain;

import java.util.Map;

public class TimeMap {
    private Map<Integer,Integer> timeMap;

    public TimeMap(Map<Integer,Integer>timeMap){
        this.timeMap=timeMap;
    }

    public Map<Integer, Integer> getTimeMap() {
        return timeMap;
    }

    public void setTimeMap(Map<Integer, Integer> timeMap) {
        this.timeMap = timeMap;
    }
}
