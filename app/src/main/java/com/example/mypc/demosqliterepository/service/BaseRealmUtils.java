package com.example.mypc.demosqliterepository.service;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by MyPC on 5/30/2017.
 */

public class BaseRealmUtils {
    private static final int REALM_VERSION = 4;
    public static RealmConfiguration getRealmConfig(Context context){
        Realm.init(context);
        RealmConfiguration config2 = new RealmConfiguration.Builder()
                .schemaVersion(REALM_VERSION)
                .deleteRealmIfMigrationNeeded()
                .build();
        return config2;
    }
}
