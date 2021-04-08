package com.example.coursero.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.coursero.database.CourseRepository;
import com.example.coursero.model.Course;

public class CourseViewModel extends AndroidViewModel {
    CourseRepository mRepository;
    //Fixed Data
    private final String[] courseTitleList;
    private final String[] coursesDurationList;
    private final Course[] getAllCourse;
    private Course mCurrentCourse;


    public CourseViewModel(@NonNull Application application) {
        super(application);

        mRepository = new CourseRepository(application);
        courseTitleList = mRepository.getCoursesTitleList();
        coursesDurationList = mRepository.getCoursesDurationList();
        getAllCourse = mRepository.getAllCourse();


    }

    public Course[] getAllCourse() {
        return getAllCourse;
    }

    public String[] getCoursesTitleList() {
        return courseTitleList;
    }

    public String[] getCoursesDurationList() {
        return coursesDurationList;
    }

    public void insertCourse(Course course) {
        mRepository.insertCourse(course);
    }

    public Course getCurrentSelectedCourse() {
        return mCurrentCourse;
    }

    public void setCurrentSelectedCourse(Course course) {
        mCurrentCourse = course;
    }

    public void updateCourse(Course currentCourse) {
        mRepository.updateCourse(currentCourse);
    }

    public void saveNoteToCurrentCourse(float currentTimeStamp, String notes) {
        mCurrentCourse.saveNoteToCurrentCourse(currentTimeStamp, notes);
    }

    public LiveData<String> getLiveSavedNotes(int courseId) {
        return mRepository.getLiveSavedNotes(courseId);
    }
}