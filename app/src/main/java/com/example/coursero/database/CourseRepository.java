package com.example.coursero.database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.coursero.model.Course;

import java.util.concurrent.ExecutionException;

public class CourseRepository {

    private final CourseDao mCourseDao;
    private Course[] getAllCourseList;

    public CourseRepository(Application application) {

        CourseDataBase mCourseDatabase = CourseDataBase.getInstance(application);
        mCourseDao = mCourseDatabase.CourseDao();

    }

    public Course[] getAllCourse() {
        try {
            return new getAllCourseInDBAsyncTask(mCourseDao).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String[] getCoursesTitleList() {
        return CourseStaticDetails.URL_COURSE_Name;
    }

    public String[] getCoursesDurationList() {
        return CourseStaticDetails.URL_COURSE_Duration;
    }

    public void insertCourse(Course course) {
        new insertCourseInDBAsyncTask(mCourseDao).execute(course);
    }

    public void updateCourse(Course currentCourse) {
        new updateCourseInDBAsyncTask(mCourseDao).execute(currentCourse);
    }

    public LiveData<String> getLiveSavedNotes(int courseId) {
        try {
            return new getSavedNotesInDBAsyncTask(mCourseDao).execute(courseId).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new LiveData<String>() {
        };
    }


    // Async Tasks
    private class insertCourseInDBAsyncTask extends AsyncTask<Course, Void, Void> {
        CourseDao mDao;

        public insertCourseInDBAsyncTask(CourseDao mEventDao) {
            this.mDao = mEventDao;
        }

        @Override
        protected Void doInBackground(Course... events) {
            mDao.insert(events[0]);
            return null;
        }
    }

    private class getSavedNotesInDBAsyncTask extends AsyncTask<Integer, Void, LiveData<String>> {
        CourseDao mDao;

        public getSavedNotesInDBAsyncTask(CourseDao mEventDao) {
            this.mDao = mEventDao;
        }

        @Override
        protected LiveData<String> doInBackground(Integer... courseId) {
            return mDao.getNotes(courseId[0]);
        }
    }

    private class updateCourseInDBAsyncTask extends AsyncTask<Course, Void, Void> {
        CourseDao mDao;

        public updateCourseInDBAsyncTask(CourseDao mEventDao) {
            this.mDao = mEventDao;
        }

        @Override
        protected Void doInBackground(Course... events) {
            //Constants.logD(String.valueOf(events[0].getCompletedUpTo()));
            mDao.update(events[0]);
            return null;
        }
    }


    private class getAllCourseInDBAsyncTask extends AsyncTask<Void, Void, Course[]> {
        CourseDao mDao;

        public getAllCourseInDBAsyncTask(CourseDao mEventDao) {
            this.mDao = mEventDao;
        }

        @Override
        protected Course[] doInBackground(Void... voids) {
            return mDao.getAllCourse();
        }
    }

}
