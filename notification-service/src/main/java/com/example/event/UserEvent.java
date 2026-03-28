

package com.example.event;

public class UserEvent {

    private String email;
    private EventType eventType;

    public UserEvent() {
    }

    public UserEvent(String email, EventType eventType) {
        this.email = email;
        this.eventType = eventType;
    }

    public String getEmail() {
        return email;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }
}
