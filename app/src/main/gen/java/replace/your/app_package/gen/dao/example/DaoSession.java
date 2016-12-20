package replace.your.app_package.gen.dao.example;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import replace.your.app_package.gen.dao.example.DbExample;

import replace.your.app_package.gen.dao.example.DbExampleDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig dbExampleDaoConfig;

    private final DbExampleDao dbExampleDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        dbExampleDaoConfig = daoConfigMap.get(DbExampleDao.class).clone();
        dbExampleDaoConfig.initIdentityScope(type);

        dbExampleDao = new DbExampleDao(dbExampleDaoConfig, this);

        registerDao(DbExample.class, dbExampleDao);
    }
    
    public void clear() {
        dbExampleDaoConfig.getIdentityScope().clear();
    }

    public DbExampleDao getDbExampleDao() {
        return dbExampleDao;
    }

}
