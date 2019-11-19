package entidade;
// Generated 18/11/2019 20:56:18 by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * Checkin generated by hbm2java
 */
public class Checkin  implements java.io.Serializable {


     private int id;
     private ActivityEvent activityEvent;
     private Date date;
     private String initHour;
     private Character sync;

    public Checkin() {
    }

	
    public Checkin(int id, ActivityEvent activityEvent) {
        this.id = id;
        this.activityEvent = activityEvent;
    }
    public Checkin(int id, ActivityEvent activityEvent, Date date, String initHour, Character sync) {
       this.id = id;
       this.activityEvent = activityEvent;
       this.date = date;
       this.initHour = initHour;
       this.sync = sync;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public ActivityEvent getActivityEvent() {
        return this.activityEvent;
    }
    
    public void setActivityEvent(ActivityEvent activityEvent) {
        this.activityEvent = activityEvent;
    }
    public Date getDate() {
        return this.date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
    public String getInitHour() {
        return this.initHour;
    }
    
    public void setInitHour(String initHour) {
        this.initHour = initHour;
    }
    public Character getSync() {
        return this.sync;
    }
    
    public void setSync(Character sync) {
        this.sync = sync;
    }




}


