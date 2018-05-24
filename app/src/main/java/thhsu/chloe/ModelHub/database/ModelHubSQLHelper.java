package thhsu.chloe.ModelHub.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import thhsu.chloe.ModelHub.api.model.Jobs;

/**
 * Created by Chloe on 5/14/2018.
 */

public class ModelHubSQLHelper extends SQLiteOpenHelper{

    private final static String DATABASE_NAME = "jeeva_data.db";
    private final static int DATABASE_VERSION = 1;
    private final static String JOBS_TABLE = "jobs_table";

    private final static String USER_TOKEN = "user_token";
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
    private final static String JOB_DES = "job_des";
    private final static String JOB_REQUIREMENT = "job_requirement";
    private final static String JOB_BENEFIT = "job_benefit";
    private final static String JOB_HIRING_RESOURCE_FROM = "job_hiring_resource_from";
    private final static String JOB_HIRING_RESOURCE_CONTACT_NAME = "job_hiring_resource_contact_name";
    private final static String JOB_HIRING_RESOURCE_CONTACT_EMAIL = "job_hiring_resource_contact_email";
    private final static String JOB_HIRING_RESOURCE_INFO = "job_hiring_resource_info";
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
            + JOB_DES + " TEXT NOT NULL, "
            + JOB_REQUIREMENT + " TEXT NOT NULL, "
            + JOB_BENEFIT + " TEXT NOT NULL, "
            + JOB_HIRING_RESOURCE_FROM+ " TEXT NOT NULL, "
            + JOB_HIRING_RESOURCE_CONTACT_NAME + " TEXT NOT NULL, "
            + JOB_HIRING_RESOURCE_CONTACT_EMAIL + " TEXT NOT NULL, "
            + JOB_HIRING_RESOURCE_INFO + " TEXT NOT NULL, "
            + JOB_SAVED + " INTEGER NOT NULL) ";



    public ModelHubSQLHelper(Context context) {
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
        contentValues.put(JOB_DES, job.getDescription());
        contentValues.put(JOB_REQUIREMENT, job.getRequirements());
        contentValues.put(JOB_BENEFIT, String.join(",", job.getBenefits()));
        contentValues.put(JOB_HIRING_RESOURCE_FROM, job.getHiring_source_from());
        contentValues.put(JOB_HIRING_RESOURCE_CONTACT_NAME, job.getHiring_contact_name());
        contentValues.put(JOB_HIRING_RESOURCE_CONTACT_EMAIL, job.getHiring_contact_email());
        contentValues.put(JOB_HIRING_RESOURCE_INFO, job.getHiring_other_info());
        contentValues.put(JOB_SAVED, (isSaved)? 1: 0);

        getWritableDatabase().insert(JOBS_TABLE, null, contentValues);
    }

    public void deleteJob(Jobs job){
       Log.d("Chloe", "delete job: "+job.getId()+
               getWritableDatabase().delete(JOBS_TABLE, JOB_ID + "=?", new String[] {job.getId()}));
    }


    public void updateSavedJobs(Jobs job, boolean isSaved){
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(JOB_SAVED, (isSaved)? 1:0);

        if(isSaved && !isJobDataExist(job.getId())){
            Log.d("Chloe", "insert job"+job.getId());
            insertJob(job, isSaved);
        } else {
            deleteJob(job);
        }
            setSavedJobsChanged(true);
//                Log.d("Chloe", "Job: " + job.getId() + " exist, update is_saved is " + isSaved + ".");
//                getWritableDatabase().update(JOBS_TABLE, contentValues,
//                        JOB_ID + "='" + job.getId() + "'", null);

    }

    public boolean getSavedJob(String jobId){
        return  isJobDataExist(jobId);
//        if(isJobDataExist(jobId)){
//            Log.d("Chloe", "if job data exist: true");
//            mCursor = getWritableDatabase().query(JOBS_TABLE,
//                    new String[]{JOB_SAVED}, JOB_ID + "='" + jobId + "'",
//                    null,null,null,null);
//            mCursor.moveToFirst();
//            return mCursor.getInt(mCursor.getColumnIndex(JOB_SAVED)) == 1;
//        }else{
//            Log.d("Chloe", "if job data exist: false");
//            return false;
//        }
    }


    public boolean isJobDataExist(String jobId){
        Log.d("Chloe", "isJobData exist ID: " + jobId);
        mCursor = getWritableDatabase().query(JOBS_TABLE,
                new String[]{JOB_SAVED},
                JOB_ID+"=?",
                new String[]{jobId}
                ,null,null,null);
        Log.d("Chloe", "cursor count: " + mCursor.getCount());
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
                JOB_IMAGE, JOB_LOCATION, JOB_TYPE, JOB_DES, JOB_REQUIREMENT, JOB_BENEFIT,
                        JOB_HIRING_RESOURCE_FROM, JOB_HIRING_RESOURCE_CONTACT_NAME, JOB_HIRING_RESOURCE_CONTACT_EMAIL,
                        JOB_HIRING_RESOURCE_INFO, JOB_SAVED}, JOB_SAVED + "=1",
                null, null, null, null);
        ArrayList<Jobs> jobs = new ArrayList<>();
        while (mCursor.moveToNext()){
            Jobs job = new Jobs();
            String[] test;
            job.setId(mCursor.getString(0));
            job.setTitle(mCursor.getString(mCursor.getColumnIndex(JOB_TITLE)));
            job.setUrgent((mCursor.getInt(mCursor.getColumnIndex(JOB_URGENT)) == 1)? true:false);
            job.setRecommended((mCursor.getInt(mCursor.getColumnIndex(JOB_RECOMMENDED)) == 1)? true: false);
            job.setDatePosted(mCursor.getString(mCursor.getColumnIndex(JOB_POSTED_DATE)));
            job.setCompany(mCursor.getString(mCursor.getColumnIndex(JOB_COMPANY)));
            job.setLogo(mCursor.getString(mCursor.getColumnIndex(JOB_LOGO)));
            job.setLocation(mCursor.getString(mCursor.getColumnIndex(JOB_LOCATION)));
            job.setType(mCursor.getString(mCursor.getColumnIndex(JOB_TYPE)));
            job.setDescription(mCursor.getString(mCursor.getColumnIndex(JOB_DES)));
            job.setRequirements(mCursor.getString(mCursor.getColumnIndex(JOB_REQUIREMENT)));
            job.setBenefits(mCursor.getString(mCursor.getColumnIndex(JOB_BENEFIT)).split( "\\,"));
            job.setHiring_source_from(mCursor.getString(mCursor.getColumnIndex(JOB_HIRING_RESOURCE_FROM)));
            job.setHiring_contact_name(mCursor.getString(mCursor.getColumnIndex(JOB_HIRING_RESOURCE_CONTACT_NAME)));
            job.setHiring_contact_email(mCursor.getString(mCursor.getColumnIndex(JOB_HIRING_RESOURCE_CONTACT_EMAIL)));
            job.setHiring_other_info(mCursor.getString(mCursor.getColumnIndex(JOB_HIRING_RESOURCE_INFO)));
            job.setSaved((mCursor.getInt(mCursor.getColumnIndex(JOB_SAVED)) == 1)? true: false);
            jobs.add(job);

        }
        return jobs;
    }
}
