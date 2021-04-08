package com.example.coursero.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.coursero.model.Course;
import com.example.coursero.util.Constants;
import com.example.coursero.util.StringListConverter;

@Database(entities = {Course.class}, version = 1)
@TypeConverters({StringListConverter.class})
public abstract class CourseDataBase extends RoomDatabase {

    public static CourseDataBase instance;
    //Callback called when Room DB is created. To create some default items
    private static final RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            //Constants.logD("onOpen ");
        }

        @Override
        public void onDestructiveMigration(@NonNull SupportSQLiteDatabase db) {
            super.onDestructiveMigration(db);
            //Constants.logD("onDestructiveMigration  ");
        }

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            new PopulateDummyEventsAsyncTask(instance).execute();
        }
    };

    public static synchronized CourseDataBase getInstance(Context cn) {
        if (instance == null) {
            //Constants.logD("instance null ");
            instance = Room.databaseBuilder(cn.getApplicationContext(),
                    CourseDataBase.class, Constants.COURSE_DB_NAME)
                    /*.addMigrations(MIGRATION_1_2  ,MIGRATION_2_3)*/
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }

        return instance;
    }

    public abstract CourseDao CourseDao();

    public static class PopulateDummyEventsAsyncTask extends AsyncTask<Void, Void, Void> {
        private final CourseDao mDao;

        public PopulateDummyEventsAsyncTask(CourseDataBase db) {
            mDao = db.CourseDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            for (int i = 0; i < Constants.COURSE_COUNT; i++) {
                Constants.logD("adding " + CourseStaticDetails.URL_COURSE_Name[i]);
                mDao.insert(new Course(CourseStaticDetails.URL_COURSE_Name[i]
                        , CourseStaticDetails.URL_COURSE_URLS[i]
                        , CourseStaticDetails.URL_COURSE_Duration[i])
                );
            }
            return null;
        }
    }


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
