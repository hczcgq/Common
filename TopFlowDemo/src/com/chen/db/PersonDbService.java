package com.chen.db;

import java.util.List;

import com.chen.BaseApplication;
import com.chen.dao.DaoSession;
import com.chen.dao.Person;
import com.chen.dao.PersonDao;
import com.chen.dao.PersonDao.Properties;

import android.content.Context;
import de.greenrobot.dao.query.QueryBuilder;

public class PersonDbService implements DbServiceImp<Person>{

    private static PersonDbService instance;

    private static Context appContext;

    private DaoSession mDaoSession;

    private PersonDao personDao;

    private PersonDbService() {
    }

    public static PersonDbService getInstance(Context context) {
        if (instance == null) {
            instance = new PersonDbService();
            if (appContext == null) {
                appContext = context.getApplicationContext();
            }
            instance.mDaoSession = BaseApplication.getDaoSession(context);
            instance.personDao = instance.mDaoSession.getPersonDao();
        }
        return instance;
    }
    
    @Override
    public long insertOrReplace(Person entity) {
        return personDao.insertOrReplace(entity);
    }

    @Override
    public void insertOrReplace(final List<Person> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        personDao.getSession().runInTx(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < list.size(); i++) {
                    Person person = list.get(i);
                    personDao.insertOrReplace(person);
                }
            }
        });
    }

    @Override
    public void deleteAll() {
        personDao.deleteAll();
    }

    @Override
    public void deleteBykey(long id) {
        personDao.deleteByKey(id);
    }

    @Override
    public void deleteByEntity(Person entity) {
       personDao.delete(entity);
    }

    @Override
    public Person loadByKey(long id) {
        return personDao.load(id);
    }

    @Override
    public List<Person> loadAll() {
        return personDao.loadAll();
    }

    @Override
    public List<Person> queryWithParem(String where, String... params) {
        return personDao.queryRaw(where, params);
    }

    @Override
    public boolean isExistWithId(long id) {
        QueryBuilder<Person> qb = personDao.queryBuilder();
        qb.where(Properties.Id.eq(id));
        qb.buildCount().count();
        return qb.buildCount().count() > 0 ? true : false;
    }

}
