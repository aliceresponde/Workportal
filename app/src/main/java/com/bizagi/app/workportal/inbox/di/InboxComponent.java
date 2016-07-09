package com.bizagi.app.workportal.inbox.di;

import com.bizagi.app.workportal.inbox.InboxPresenter;
import com.bizagi.app.workportal.inbox.ui.adapters.InboxAdapter;
import com.bizagi.app.workportal.libs.di.LibsModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by alice on 7/9/16.
 */
@Singleton
@Component(modules = {InboxModules.class, LibsModule.class})
public interface InboxComponent {
    InboxAdapter   getAdapter();
    InboxPresenter getPresenter();
}
