package com.technopreneur.moneytransfer.event;

public class Event {
    protected String id;
    protected Object data;
    protected EventType eventType;

    public Event(EventType eventType) {
        this.eventType = eventType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id='" + id + '\'' +
                ", data=" + data +
                ", eventType=" + eventType +
                '}';
    }
}
