package com.example.coursero.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.example.coursero.model.Course;
import com.example.coursero.util.Constants;

@Database(entities = {Course.class}, version = 1)
abstract class CourseDataBase extends RoomDatabase {


    private static final String DB_NAME = "event_database";
    public static CourseDataBase instance;

    public static synchronized CourseDataBase getInstance(Context cn) {
        if (instance == null) {
            instance = Room.databaseBuilder(cn.getApplicationContext(),
                    CourseDataBase.class, Constants.COURSE_TABLE_NAME)
                    /*.addMigrations(MIGRATION_1_2  ,MIGRATION_2_3)*/
                    .fallbackToDestructiveMigration()
                    /*.addCallback(roomCallback)*/
                    .build();
        }
        return instance;
    }

    public abstract CourseDao CourseDao();


   /* Callback called when Room DB is created. To create some default items
   private static final RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            //Log.d(Constants.LOG_TAG, "onCreate:  Callback");
            new PopulateDummyEventsAsyncTask(instance).execute();
        }
    };
    public  static class PopulateDummyEventsAsyncTask extends AsyncTask<Void ,Void , Void> {
        private final EventDao mDao;
        public PopulateDummyEventsAsyncTask(EventDataBase db) {
            mDao = db.EventDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            // Log.d(Constants.LOG_TAG, "doInBackground:  Preparing dummy List");
            mDao.insert(new Event("Conference Meeting", true, "Rishi", "10-05-2020", "Delhi", "Please don't forget to attend this high level meting you have to be in.\nPlease mark your availability", Constants.getDummyEventImages1(), new People().getDummyPeopleList()));
            mDao.insert(new Event("Birthday Celebration", false, "Sam", "12-05-2022", "Bangalore", "Hey ! It is my birthday celebration party. \n Come join us and celebrate together", Constants.getDummyEventImages3(), new People().getDummyPeopleList()));
            mDao.insert(new Event("Celebration Meeting", true, "Rishi", "11-05-2022", "Mumbai", "Come together on this joyous occasion ", Constants.getDummyEventImages2(), new People().getDummyPeopleList()));
            mDao.insert(new Event("Family dinner", false, "Pam", "13-05-2020", "Hyderabad", "Please join us to have a dinner with all of your loved ones.", Constants.getDummyEventImages3(), new People().getDummyPeopleList()));
            return null;
        }
    }*/

    /* When Migrating the DataBAse
    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE event_table " + " ADD COLUMN invite_acceptance BOOLEAN");
        }
    };
    static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE Book "
                    + " ADD COLUMN pub_year INTEGER");
        }
    };*/


}
