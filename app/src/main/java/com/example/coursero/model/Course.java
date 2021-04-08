package com.example.coursero.model;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.coursero.util.Constants;

@Entity(tableName = Constants.COURSE_TABLE_NAME)
public class Course {

    @NonNull()
    @PrimaryKey()
    private int courseId;
    private String title;
    private String url;
    private String isCompleted;
    private String duration;
    private String completedUpTo;

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(String isCompleted) {
        this.isCompleted = isCompleted;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getCompletedUpTo() {
        return completedUpTo;
    }

    public void setCompletedUpTo(String completedUpTo) {
        this.completedUpTo = completedUpTo;
    }


}
