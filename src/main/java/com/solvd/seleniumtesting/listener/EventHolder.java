package com.solvd.seleniumtesting.listener;

import com.solvd.seleniumtesting.service.ServiceClass;

import java.util.*;

public class EventHolder {

    private static final Map<EventType, List<ServiceClass>> listeners = new HashMap<>();

    public EventHolder() {
        Arrays.stream(EventType.values())
                .forEach(eventType -> listeners.put(eventType, new ArrayList<>()));
    }

    public void addListener(EventType type, ServiceClass service) {
        List<ServiceClass> services = listeners.get(type);
        services.add(service);
    }

    public void removeListener(EventType type, ServiceClass service) {
        List<ServiceClass> services = listeners.get(type);
        services.remove(service);
    }

    public void notify(EventType type){
        List<ServiceClass> services = listeners.get(type);
        services.forEach(Event::onEvent);
    }
}
