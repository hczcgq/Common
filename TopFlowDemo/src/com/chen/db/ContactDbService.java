package com.chen.db;

import java.util.List;

import com.chen.BaseApplication;
import com.chen.dao.DaoSession;
import com.chen.dao.Person;
import com.chen.dao.PersonDao;
import com.chen.dao.PersonDao.Properties;

import de.greenrobot.dao.query.QueryBuilder;
import android.content.Context;
import android.util.Log;

public class ContactDbService {
    private static final String TAG = ContactDbService.class.getSimpleName();

    private static ContactDbService instance;

    private static Context appContext;

    private DaoSession mDaoSession;

    private PersonDao personDao;

    private ContactDbService() {
    }

    public static ContactDbService getInstance(Context context) {
        if (instance == null) {
            instance = new ContactDbService();
            if (appContext == null) {
                appContext = context.getApplicationContext();
            }
            instance.mDaoSession = BaseApplication.getDaoSession(context);
            instance.personDao = instance.mDaoSession.getPersonDao();
        }
        return instance;
    }

    public Person loadPerson(long id) {
        return personDao.load(id);
    }

    public List<Person> loadAllPerson() {
        return personDao.loadAll();
    }

    /**
     * query list with where clause ex: begin_date_time >= ? AND end_date_time
     * <= ?
     * 
     * @param where
     *            where clause, include 'where' word
     * @param params
     *            query parameters
     * @return
     */

    public List<Person> queryPerson(String where, String... params) {
        return personDao.queryRaw(where, params);
    }

    public boolean isSaved(int id) {
        QueryBuilder<Person> qb = personDao.queryBuilder();
        qb.where(Properties.Id.eq(id));
        qb.buildCount().count();
        return qb.buildCount().count() > 0 ? true : false;

    }

    /**
     * insert or update note
     * 
     * @param note
     * @return insert or update note id
     */
    public long savePerson(Person person) {
        return personDao.insertOrReplace(person);
    }

    /**
     * insert or update noteList use transaction
     * 
     * @param list
     */
    public void savePersonLists(final List<Person> list) {
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

    /**
     * delete all note
     */
    public void deleteAllPerson() {
        personDao.deleteAll();
    }

    /**
     * delete note by id
     * 
     * @param id
     */
    public void deletePerson(long id) {
        personDao.deleteByKey(id);
    }

    public void deletePerson(Person person) {
        personDao.delete(person);
    }
}
