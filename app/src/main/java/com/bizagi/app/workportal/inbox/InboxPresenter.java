package com.bizagi.app.workportal.inbox;

import com.bizagi.app.workportal.inbox.events.InboxEvent;
import com.bizagi.app.workportal.inbox.ui.InboxView;

/**
 * Created by alice on 7/9/16.
 */
public interface InboxPresenter {
    void  onCreate();
    void  onDestroy();
    void  onEventMainThread(InboxEvent event);


    void  getVactionsRequest();//vacations saved into sqlLite
    //=================== VIEW ====================
    InboxView getView();


}
