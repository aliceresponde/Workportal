package com.bizagi.app.workportal.db;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by alice on 7/8/16.
 */
@Database(name =  WorkPortalDataBase.NAME, version =  WorkPortalDataBase.VERSION)
public class WorkPortalDataBase {
    public static final int VERSION =1;
    public static final String NAME = "Workportal";
}
