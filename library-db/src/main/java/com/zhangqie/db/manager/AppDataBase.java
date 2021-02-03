package com.zhangqie.db.manager;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.zhangqie.db.converter.DateConverter;
import com.zhangqie.db.dao.SearchHistoryDao;
import com.zhangqie.db.entity.SearchHistoryEntity;

@TypeConverters(value = {DateConverter.class})
@Database(entities = {SearchHistoryEntity.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {
    public abstract SearchHistoryDao searchHistoryDao();
}

