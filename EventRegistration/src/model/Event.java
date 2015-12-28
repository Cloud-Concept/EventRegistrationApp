package model;

/**
 * Created by Bibo on 12/27/15.
 */
public class Event {

    private String Id;
    private boolean IsAllDayEvent;
    private String OwnerName;
    private String OwnerId;
    private String DurationInMinutes;
    private String whoName;
    private String whoId;
    private String whatId;
    private String whatName;
    private String StartDateTime;
    private String ActivityDateTime;
    private String Subject;


    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public boolean getIsAllDayEvent() {
        return IsAllDayEvent;
    }

    public void setIsAllDayEvent(boolean isAllDayEvent) {
        IsAllDayEvent = isAllDayEvent;
    }

    public String getOwnerName() {
        return OwnerName;
    }

    public void setOwnerName(String ownerName) {
        OwnerName = ownerName;
    }

    public String getOwnerId() {
        return OwnerId;
    }

    public void setOwnerId(String ownerId) {
        OwnerId = ownerId;
    }

    public String getDurationInMinutes() {
        return DurationInMinutes;
    }

    public void setDurationInMinutes(String durationInMinutes) {
        DurationInMinutes = durationInMinutes;
    }

    public String getWhoName() {
        return whoName;
    }

    public void setWhoName(String whoName) {
        this.whoName = whoName;
    }

    public String getWhoId() {
        return whoId;
    }

    public void setWhoId(String whoId) {
        this.whoId = whoId;
    }

    public String getWhatId() {
        return whatId;
    }

    public void setWhatId(String whatId) {
        this.whatId = whatId;
    }

    public String getWhatName() {
        return whatName;
    }

    public void setWhatName(String whatName) {
        this.whatName = whatName;
    }

    public String getStartDateTime() {
        return StartDateTime;
    }

    public void setStartDateTime(String startDateTime) {
        StartDateTime = startDateTime;
    }

    public String getActivityDateTime() {
        return ActivityDateTime;
    }

    public void setActivityDateTime(String activityDateTime) {
        ActivityDateTime = activityDateTime;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }
}
