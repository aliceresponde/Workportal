package com.bizagi.app.workportal.libs;


/**
 * Created by alice on 6/23/16.
 * Implementa de mi  EventBus
 */

public class GreenRobotsEventBus implements EventBus {

    private  org.greenrobot.eventbus.EventBus eventBus;

    public GreenRobotsEventBus(org.greenrobot.eventbus.EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void register(Object subscriber) {
        eventBus.register(subscriber);
    }

    @Override
    public void unregister(Object subscriber) {
        eventBus.unregister(subscriber);
    }

    @Override
    public void post(Object event) {
        eventBus.post(event);
    }
}
