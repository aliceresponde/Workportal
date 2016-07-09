package com.bizagi.app.workportal.inbox.di;

import android.content.Context;

import com.bizagi.app.workportal.db.entities.Vacation;
import com.bizagi.app.workportal.inbox.InboxInteractor;
import com.bizagi.app.workportal.inbox.InboxInteractorImp;
import com.bizagi.app.workportal.inbox.InboxPresenter;
import com.bizagi.app.workportal.inbox.InboxPresenterImp;
import com.bizagi.app.workportal.inbox.InboxRepository;
import com.bizagi.app.workportal.inbox.InboxRepositoryImp;
import com.bizagi.app.workportal.inbox.ui.InboxActivity;
import com.bizagi.app.workportal.inbox.ui.InboxView;
import com.bizagi.app.workportal.inbox.ui.adapters.InboxAdapter;
import com.bizagi.app.workportal.inbox.ui.adapters.OnItemClickListener;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by alice on 7/9/16.
 *
 * InboxAdapter getAdapter();
   InboxPresenter getPresenter();
 */
@Module
public class InboxModules {
    InboxView view;
    InboxActivity activity;
    OnItemClickListener clickListener;

    public InboxModules(InboxView view, InboxActivity activity, OnItemClickListener clickListener) {
        this.view = view;
        this.activity = activity;
        this.clickListener = clickListener;
    }

    //    =========================================PRESENTER=============================================
    @Provides @Singleton
    InboxView providesInboxView(){
        return  this.view;
    }

    @Provides @Singleton
    InboxPresenter providesInboxPresenter(InboxView view, EventBus eventBus, InboxInteractor interactor){
        return  new InboxPresenterImp(view, eventBus , interactor);
    }

    @Provides @Singleton
    InboxInteractor providesInboxInteractor(InboxRepository repository){
        return  new InboxInteractorImp(repository);
    }

    @Provides @Singleton
    InboxRepository providesInboxRepository(EventBus eventBus){
        return new InboxRepositoryImp(eventBus);
    }

//    ======================== ADAPTER ==============================================================
    @Provides @Singleton
    InboxActivity providesInboxActivity(){
        return  this.activity;
    }

    @Provides @Singleton
    OnItemClickListener  providesOnItemClickListener(){
        return  this.clickListener;
    }

    @Provides @Singleton
    InboxAdapter providesInboxAdapter(Context context, List<Vacation> vacationList, OnItemClickListener onItemClickListener){
        return  new InboxAdapter(context, vacationList, onItemClickListener);
    }

    @Provides @Singleton
    Context providesContext(){
        return  activity.getBaseContext();
    }

    @Provides @Singleton
    List<Vacation> providesEmptyList(){
        return  new ArrayList<Vacation>();
    }





}
