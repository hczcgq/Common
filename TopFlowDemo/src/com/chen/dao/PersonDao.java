package com.chen.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table PERSON.
*/
public class PersonDao extends AbstractDao<Person, Long> {

    public static final String TABLENAME = "PERSON";

    /**
     * Properties of entity Person.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Name = new Property(1, String.class, "name", false, "NAME");
        public final static Property Mobile = new Property(2, String.class, "mobile", false, "MOBILE");
        public final static Property Age = new Property(3, String.class, "age", false, "AGE");
        public final static Property Sex = new Property(4, String.class, "sex", false, "SEX");
        public final static Property Email = new Property(5, String.class, "email", false, "EMAIL");
        public final static Property Hometown = new Property(6, String.class, "hometown", false, "HOMETOWN");
        public final static Property Job = new Property(7, String.class, "job", false, "JOB");
        public final static Property Describe = new Property(8, String.class, "describe", false, "DESCRIBE");
    };


    public PersonDao(DaoConfig config) {
        super(config);
    }
    
    public PersonDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'PERSON' (" + //
                "'_id' INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "'NAME' TEXT NOT NULL ," + // 1: name
                "'MOBILE' TEXT NOT NULL ," + // 2: mobile
                "'AGE' TEXT," + // 3: age
                "'SEX' TEXT," + // 4: sex
                "'EMAIL' TEXT," + // 5: email
                "'HOMETOWN' TEXT," + // 6: hometown
                "'JOB' TEXT," + // 7: job
                "'DESCRIBE' TEXT);"); // 8: describe
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'PERSON'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Person entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getName());
        stmt.bindString(3, entity.getMobile());
 
        String age = entity.getAge();
        if (age != null) {
            stmt.bindString(4, age);
        }
 
        String sex = entity.getSex();
        if (sex != null) {
            stmt.bindString(5, sex);
        }
 
        String email = entity.getEmail();
        if (email != null) {
            stmt.bindString(6, email);
        }
 
        String hometown = entity.getHometown();
        if (hometown != null) {
            stmt.bindString(7, hometown);
        }
 
        String job = entity.getJob();
        if (job != null) {
            stmt.bindString(8, job);
        }
 
        String describe = entity.getDescribe();
        if (describe != null) {
            stmt.bindString(9, describe);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public Person readEntity(Cursor cursor, int offset) {
        Person entity = new Person( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getString(offset + 1), // name
            cursor.getString(offset + 2), // mobile
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // age
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // sex
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // email
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // hometown
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // job
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8) // describe
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Person entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setName(cursor.getString(offset + 1));
        entity.setMobile(cursor.getString(offset + 2));
        entity.setAge(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setSex(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setEmail(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setHometown(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setJob(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setDescribe(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(Person entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(Person entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
