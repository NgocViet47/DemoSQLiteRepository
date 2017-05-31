package com.example.mypc.demosqliterepository.service;

import io.realm.DynamicRealm;
import io.realm.DynamicRealmObject;
import io.realm.FieldAttribute;
import io.realm.RealmMigration;
import io.realm.RealmObjectSchema;
import io.realm.RealmSchema;

/**
 * Created by MyPC on 5/30/2017.
 */

public class Migration implements RealmMigration {
    private final static String TABLE_STUDENT = "Student";

    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
        RealmSchema schema = realm.getSchema();
        if (oldVersion == 4) {
            RealmObjectSchema personSchema = schema.get(TABLE_STUDENT);
            personSchema
                    .addField("address", String.class, FieldAttribute.REQUIRED)
                    .transform(new RealmObjectSchema.Function() {
                        @Override
                        public void apply(DynamicRealmObject obj) {
                            obj.set("address", "Ha Noi");
                        }
                    });
            oldVersion++;
        }
    }
}
