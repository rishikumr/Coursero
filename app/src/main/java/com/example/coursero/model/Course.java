package com.example.coursero.model;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.coursero.util.Constants;

import java.util.ArrayList;

@Entity(tableName = Constants.COURSE_TABLE_NAME)
public class Course {

    @NonNull()
    @PrimaryKey(autoGenerate = true)
    private int courseId;
    private String title = "title";
    private String url = "Url";
    private boolean isCompleted = false;
    private String duration = "duration";
    private float completedUpTo = 0.0f;
    private ArrayList<String> savedNotes = new ArrayList<>();

    private int quiz_status = 0;

    public int getQuiz_status() {
        return quiz_status;
    }

    public void setQuiz_status(int quiz_status) {
        this.quiz_status = quiz_status;
    }

    public Course(String title, String url, String duration) {
        this.title = title;
        this.url = url;
        this.duration = duration;
    }

    @Ignore
    public Course() {
    }

    public ArrayList<String> getSavedNotes() {
        return savedNotes;
    }

    public void setSavedNotes(ArrayList<String> savedNotes) {
        this.savedNotes = savedNotes;
    }


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

    public boolean getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public float getCompletedUpTo() {
        return completedUpTo;
    }

    public void setCompletedUpTo(float completedUpTo) {
        this.completedUpTo = completedUpTo;
    }

    public void saveNoteToCurrentCourse(float currentTimeStamp, String note) {
        savedNotes.add(currentTimeStamp + Constants.DIFFERENTIATORS_SIGN + note);
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", isCompleted=" + isCompleted +
                ", duration='" + duration + '\'' +
                ", completedUpTo=" + completedUpTo +
                ", savedNotes=" + savedNotes +
                '}';
    }
}
