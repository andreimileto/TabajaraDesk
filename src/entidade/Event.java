package entidade;
// Generated 18/11/2019 20:56:18 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Event generated by hbm2java
 */
public class Event  implements java.io.Serializable {


     private int id;
     private String name;
     private String initHour;
     private String finalHour;
     private char sync;
     private Set activityEvents = new HashSet(0);

    public Event() {
    }

	
    public Event(int id, String name, String initHour, String finalHour, char sync) {
        this.id = id;
        this.name = name;
        this.initHour = initHour;
        this.finalHour = finalHour;
        this.sync = sync;
    }
    public Event(int id, String name, String initHour, String finalHour, char sync, Set activityEvents) {
       this.id = id;
       this.name = name;
       this.initHour = initHour;
       this.finalHour = finalHour;
       this.sync = sync;
       this.activityEvents = activityEvents;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public String getInitHour() {
        return this.initHour;
    }
    
    public void setInitHour(String initHour) {
        this.initHour = initHour;
    }
    public String getFinalHour() {
        return this.finalHour;
    }
    
    public void setFinalHour(String finalHour) {
        this.finalHour = finalHour;
    }
    public char getSync() {
        return this.sync;
    }
    
    public void setSync(char sync) {
        this.sync = sync;
    }
    public Set getActivityEvents() {
        return this.activityEvents;
    }
    
    public void setActivityEvents(Set activityEvents) {
        this.activityEvents = activityEvents;
    }




}

