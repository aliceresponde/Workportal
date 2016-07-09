package com.bizagi.app.workportal.libs.di;




import android.app.Activity;

import com.bizagi.app.workportal.libs.EventBus;
import com.bizagi.app.workportal.libs.GreenRobotsEventBus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Provee la dependencia de las librerias
 */

@Module
public class LibsModule {
    private Activity activity;


    public LibsModule(Activity activity) {
        this.activity = activity;
    }

    /**
     * Crea un new GreenRobotsEventBus a partir del mio
     * @param eventBus
     * @return
     */
    @Provides
    @Singleton
    EventBus providesEventBus(org.greenrobot.eventbus.EventBus eventBus ){
        return new GreenRobotsEventBus(eventBus);
    }

    /**
     * Retorna unstancia del GreenRobotEventBus
     * @return
     */
    @Provides
    @Singleton
    org.greenrobot.eventbus.EventBus providesLibraryEventBus(){
        return org.greenrobot.eventbus.EventBus.getDefault();
    }

    @Provides
    @Singleton
    Activity providesActivity(){
        return  this.activity;
    }




}
