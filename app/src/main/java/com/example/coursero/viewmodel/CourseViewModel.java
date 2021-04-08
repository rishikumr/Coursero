package com.example.coursero.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import com.example.coursero.database.CourseRepository;

public class CourseViewModel extends AndroidViewModel {
    CourseRepository mRepository;
    //Fixed Data
    String[] courseTitleList;
    String[] coursesDurationList;


    public CourseViewModel(@NonNull Application application) {
        super(application);
        mRepository = new CourseRepository(application);
        courseTitleList = mRepository.getCoursesTitleList();
        coursesDurationList = mRepository.getCoursesDurationList();


    }

    public String[] getCoursesTitleList() {
        return courseTitleList;
    }

    public String[] getCoursesDurationList() {
        return coursesDurationList;
    }

}