package com.example.coursero.database;

import android.app.Application;

import com.example.coursero.util.Constants;

public class CourseRepository {

    private final CourseDao mCourseDao;

    public CourseRepository(Application application) {

        CourseDataBase mCourseDatabase = CourseDataBase.getInstance(application);
        mCourseDao = mCourseDatabase.CourseDao();

       /* mAllMyEvents = mEventDao.getAllMyEvents();
        mAllInvitations = mEventDao.getAllInvitation();*/

    }


    public String[] getCoursesTitleList() {
        String[] strArray = CourseStaticDetails.URL_COURSE_Name;
        Constants.logD("Selected Course" + strArray[0]);
        return CourseStaticDetails.URL_COURSE_Name;
    }

    public String[] getCoursesDurationList() {
        return CourseStaticDetails.URL_COURSE_Duration;
    }
}
