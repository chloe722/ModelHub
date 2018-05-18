package thhsu.chloe.jeeva.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.icu.text.LocaleDisplayNames;
import android.util.Log;

import java.util.ArrayList;

import thhsu.chloe.jeeva.api.model.Jobs;

/**
 * Created by Chloe on 5/14/2018.
 */

public class JeevaSQLHelper extends SQLiteOpenHelper{

    private final static String DATABASE_NAME = "jeeva_data.db";
    private final static int DATABASE_VERSION = 1;
    private final static String JOBS_TABLE = "jobs_table";

    private final static String JOB_ID = "job_id";
    private final static String JOB_TITLE = "job_title";
    private final static String JOB_URGENT = "job_urgent";
    private final static String JOB_RECOMMENDED = "job_recommended";
    private final static String JOB_POSTED_DATE = "job_posted_date";
    private final static String JOB_EXPIRED_DATE = "job_expired_date";
    private final static String JOB_COMPANY = "job_company";
    private final static String JOB_LOGO = "job_logo";
    private final static String JOB_IMAGE = "job_image";
    private final static String JOB_LOCATION = "job_location";
    private final static String JOB_TYPE = "job_type";
    private final static String JOB_SAVED = "job_saved";

    private Cursor mCursor;
    private boolean isSavedJobsChanged = false;

    private final static String CREATE_TABLE = "CREATE TABLE " + JOBS_TABLE + " ("
            + " _id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + JOB_ID + " TEXT NOT NULL UNIQUE, "
            + JOB_TITLE + " TEXT NOT NULL, "
            + JOB_URGENT + " INTEGER NOT NULL, "
            + JOB_RECOMMENDED + " INTEGER NOT NULL, "
            + JOB_POSTED_DATE + " TEXT NOT NULL, "
            + JOB_EXPIRED_DATE + " TEXT NOT NULL, "
            + JOB_COMPANY + " TEXT NOT NULL, "
            + JOB_LOGO + " TEXT NOT NULL, "
            + JOB_IMAGE + " TEXT NOT NULL, "
            + JOB_LOCATION + " TEXT NOT NULL, "
            + JOB_TYPE + " TEXT NOT NULL, "
            + JOB_SAVED + " INTEGER NOT NULL) ";



    public JeevaSQLHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + JOBS_TABLE);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + JOBS_TABLE);
    }

    private void insertJob(Jobs job, boolean isSaved){
        ContentValues contentValues = new ContentValues();

        Log.d("Chloe", "insert Job: " + job.getId());

        contentValues.put(JOB_ID, job.getId());
        contentValues.put(JOB_TITLE, job.getTitle());
        contentValues.put(JOB_URGENT, job.getUrgent());
        contentValues.put(JOB_RECOMMENDED, job.getRecommended());
        contentValues.put(JOB_POSTED_DATE, job.getDatePosted());
        contentValues.put(JOB_EXPIRED_DATE, job.getDateExpires());
        contentValues.put(JOB_COMPANY, job.getCompany());
        contentValues.put(JOB_LOGO, job.getLogo());
        contentValues.put(JOB_IMAGE, job.getImage());
        contentValues.put(JOB_LOCATION, job.getLocation());
        contentValues.put(JOB_TYPE, job.getType());
        contentValues.put(JOB_SAVED, (isSaved)? 1: 0);

        getWritableDatabase().insert(JOBS_TABLE, null, contentValues);
    }

    public void updateSavedJobs(Jobs job, boolean isSaved){
        ContentValues contentValues = new ContentValues();
        contentValues.put(JOB_SAVED, (isSaved)? 1:0);

        if(isJobDataExist(job.getId())){
            Log.d("Chloe", "Job: " + job.getId() + " exist, update is_saved is " + isSaved + ".");
            getWritableDatabase().update(JOBS_TABLE, contentValues,
                    JOB_ID + "='" + job.getId() + "'", null);
        }else{
            insertJob(job, isSaved);
        }
        setSavedJobsChanged(true);
    }

    public boolean getSavedJob(String jobId){

        if(isJobDataExist(jobId)){
            Log.d("Chloe", "if job data exist: true");
            mCursor = getWritableDatabase().query(JOBS_TABLE,
                    new String[]{JOB_SAVED}, JOB_ID + "='" + jobId + "'",
                    null,null,null,null);
            mCursor.moveToFirst();
            return (mCursor.getInt(mCursor.getColumnIndex(JOB_SAVED)) == 1)? true : false;
        }else{
            Log.d("Chloe", "if job data exist: false");
            return false;
        }
    }


    public boolean isJobDataExist(String jobId){
        Log.d("Chloe", "isJobData exist ID:" + jobId);
        mCursor = getWritableDatabase().query(JOBS_TABLE,
                new String[]{JOB_SAVED}, JOB_ID + "='" + jobId + "'",
                null, null,null,null);
        return (mCursor.getCount() > 0) ? true : false;
    }

    public boolean isSavedJobsChanged(){return isSavedJobsChanged;}

    public void setSavedJobsChanged(boolean savedJobsChanged){
        isSavedJobsChanged = savedJobsChanged;
    }

    public ArrayList<Jobs> getSavedJobs(){

        mCursor = getWritableDatabase().query(JOBS_TABLE,
                new String[]{JOB_ID, JOB_TITLE, JOB_URGENT, JOB_RECOMMENDED,
                JOB_POSTED_DATE, JOB_EXPIRED_DATE, JOB_COMPANY, JOB_LOGO,
                JOB_IMAGE, JOB_LOCATION, JOB_TYPE, JOB_SAVED}, JOB_SAVED + "=1",
                null, null, null, null);
        ArrayList<Jobs> jobs = new ArrayList<>();
        while (mCursor.moveToNext()){
            Jobs job = new Jobs();
            job.setId(mCursor.getString(0));
            job.setTitle(mCursor.getString(mCursor.getColumnIndex(JOB_TITLE)));
            job.setUrgent((mCursor.getInt(mCursor.getColumnIndex(JOB_URGENT)) == 1)? true:false);
            job.setRecommended((mCursor.getInt(mCursor.getColumnIndex(JOB_RECOMMENDED)) == 1)? true: false);
            job.setDatePosted(mCursor.getString(mCursor.getColumnIndex(JOB_POSTED_DATE)));
            job.setCompany(mCursor.getString(mCursor.getColumnIndex(JOB_COMPANY)));
            job.setLogo(mCursor.getString(mCursor.getColumnIndex(JOB_LOGO)));
            job.setLocation(mCursor.getString(mCursor.getColumnIndex(JOB_LOCATION)));
            job.setType(mCursor.getString(mCursor.getColumnIndex(JOB_TYPE)));
            job.setSaved((mCursor.getInt(mCursor.getColumnIndex(JOB_SAVED)) == 1)? true: false);
            jobs.add(job);

        }
        return jobs;
    }
}
