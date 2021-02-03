package com.zhangqie.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.zhangqie.db.entity.SearchHistoryEntity;

import java.util.List;

@Dao
public interface SearchHistoryDao {


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insertPerson(SearchHistoryEntity entity);


    @Query("select * from SearchHistoryEntity")
    public List<SearchHistoryEntity> selectHis();


    @Query("delete from SearchHistoryEntity where id= :id")
    void deleteById(long id);

    @Query("delete from SearchHistoryEntity")
    void deleteAll();
}
