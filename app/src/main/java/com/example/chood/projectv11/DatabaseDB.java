package com.example.chood.projectv11;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.net.ContentHandler;

public class DatabaseDB
{

    public final String DATABASE_NAME = "DatabaseDB";

    //course database table
    public final String DB_COURSE = "CourseDB"; //course information database name

    public static final String COURSE_ID = "course_id";
    public static final String COURSE_NAME = "course_name";
    public static final String COURSE_LEVEL = "course_level";
    public static final String PRE_REQUISITE = "course_prerequisite";

    //timetable database

    public final String DB_TIMETABLE = "TimetableDB"; //timetable database name

    public static final String CLASS_ID = "class_id";
    public static final String CLASS_DAY =  "class_day";
    public static final String CLASS_FROM = "class_from";
    public static final String CLASS_TO = "class_to";

    private final int DATABASE_VERSION = 1;


    private DBHelper ourHelper;
    private final Context ourContext;
    private SQLiteDatabase ourDatabase;

    public DatabaseDB(Context context)
    {
        ourContext = context;
    }



    private class DBHelper extends SQLiteOpenHelper {
        public DBHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {

            String sqlCourse = "CREATE TABLE " + DB_COURSE + " ("
                    + COURSE_ID + " INTEGER PRIMARY KEY, "
                    + COURSE_NAME + " TEXT NOT NULL, "
                    + COURSE_LEVEL + " TEXT NOT NULL, "
                    + PRE_REQUISITE + "TEXT NOT NULL);";

            String sqlClass = "CREATE TABLE "
                    + DB_TIMETABLE + " ("
                    + CLASS_DAY + " TEXT NOT NULL, "
                    + CLASS_FROM + " TEXT NOT NULL,"
                    + CLASS_TO + "TEXT NOT NULL,"
                    + CLASS_ID + " INTEGER , "
                    + " FOREIGN KEY (" + CLASS_ID + ")"
                    + " REFERENCES " + DB_COURSE +
                    "(" + COURSE_ID + "));";


            sqLiteDatabase.execSQL(sqlCourse);
            sqLiteDatabase.execSQL(sqlClass);


        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DB_COURSE);
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DB_TIMETABLE);
            onCreate(sqLiteDatabase);
        }
    }

        public DatabaseDB open() throws SQLException
        {
            ourHelper = new DBHelper(ourContext);
            ourDatabase = ourHelper.getWritableDatabase();
            return this;
        }

        public void close()
        {
            ourHelper.close();
        }
        //inserting course data to database
        public long createEntryCourse(String courseID, String courseName, int courseLevel,
                                      String prerequisite)
        {
            ContentValues cv = new ContentValues();
            cv.put(COURSE_ID, courseID);
            cv.put(COURSE_NAME, courseName);
            cv.put(COURSE_LEVEL, courseLevel);
            cv.put(PRE_REQUISITE, prerequisite);
            return ourDatabase.insert(DB_COURSE, null, cv);
        }

       //inserting data to timetable database
        public long createEntryTimetable(String classID, String classDay,
                                         String classFrom, String classTo)
        {
            ContentValues cv = new ContentValues();
            cv.put(CLASS_ID, classID);
            cv.put(CLASS_DAY, classDay);
            cv.put(CLASS_FROM, classFrom);
            cv.put(CLASS_TO, classTo);
            return ourDatabase.insert(DB_TIMETABLE, null, cv);
        }
    }


