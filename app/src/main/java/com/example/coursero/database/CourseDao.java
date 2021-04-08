package com.example.coursero.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.coursero.model.Course;
import com.example.coursero.util.Constants;

@Dao
public interface CourseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Course ev);

    @Update()
    void update(Course co);

    @Delete
    void delete(Course ev);

    @Query("DELETE FROM " + Constants.COURSE_TABLE_NAME)
    void deleteAllEvents();

    @Query("SELECT * FROM " + Constants.COURSE_TABLE_NAME + " WHERE " + Constants.PRIMARY_KEY + "=:server_Id")
    LiveData<Course> getEvent(String server_Id);
}
