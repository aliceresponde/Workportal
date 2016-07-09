package com.bizagi.app.workportal.inbox;

import com.bizagi.app.workportal.db.entities.Vacation;
import com.bizagi.app.workportal.inbox.events.InboxEvent;
import com.raizlabs.android.dbflow.list.FlowCursorList;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by alice on 7/9/16.
 */
public class InboxRepositoryImp implements InboxRepository {

    private EventBus eventBus;

    public InboxRepositoryImp(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void getVacationsRequest() {
        FlowCursorList<Vacation> storedVacationRequest = new FlowCursorList<Vacation>(false, Vacation.class);
        post(InboxEvent.READ_EVENT, storedVacationRequest.getAll());
        storedVacationRequest.close();
    }

    private void post(int eventType, List<Vacation> requestedVacationList) {
        InboxEvent event= new InboxEvent();
        event.setType(eventType);
        event.setRequestedVacationList(requestedVacationList);
        eventBus.post(event);
    }
}
