package com.chen.db;

import java.util.List;

import com.chen.BaseApplication;
import com.chen.dao.DaoSession;
import com.chen.dao.ThreadInfo;
import com.chen.dao.ThreadInfoDao;

import android.content.Context;
import android.util.Log;

public class ThreadInfoDbService {
    private static final String TAG = ContactDbService.class.getSimpleName();

    private static ThreadInfoDbService instance;

    private static Context appContext;

    private DaoSession mDaoSession;

    private ThreadInfoDao threadInfoDao;

    private ThreadInfoDbService() {
    }

    public static ThreadInfoDbService getInstance(Context context) {
        if (instance == null) {
            instance = new ThreadInfoDbService();
            if (appContext == null) {
                appContext = context.getApplicationContext();
            }
            instance.mDaoSession = BaseApplication.getDaoSession(context);
            instance.threadInfoDao = instance.mDaoSession.getThreadInfoDao();
        }
        return instance;
    }

    public ThreadInfo loadThreadInfo(long id) {
        return threadInfoDao.load(id);
    }

    public List<ThreadInfo> loadAllThreadInfo() {
        return threadInfoDao.loadAll();
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

    public List<ThreadInfo> queryThreadInfo(String where, String... params) {
        return threadInfoDao.queryRaw(where, params);
    }

    /**
     * insert or update note
     * 
     * @param note
     * @return insert or update note id
     */
    public long saveThreadInfo(ThreadInfo ThreadInfo) {
        return threadInfoDao.insertOrReplace(ThreadInfo);
    }

    /**
     * insert or update noteList use transaction
     * 
     * @param list
     */
    public void saveThreadInfoLists(final List<ThreadInfo> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        threadInfoDao.getSession().runInTx(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < list.size(); i++) {
                    ThreadInfo ThreadInfo = list.get(i);
                    threadInfoDao.insertOrReplace(ThreadInfo);
                }
            }
        });

    }

    /**
     * delete all note
     */
    public void deleteAllThreadInfo() {
        threadInfoDao.deleteAll();
    }

    /**
     * delete note by id
     * 
     * @param id
     */
    public void deleteThreadInfo(long id) {
        threadInfoDao.deleteByKey(id);
        Log.i(TAG, "delete");
    }

    public void deleteThreadInfo(ThreadInfo ThreadInfo) {
        threadInfoDao.delete(ThreadInfo);
    }
}
