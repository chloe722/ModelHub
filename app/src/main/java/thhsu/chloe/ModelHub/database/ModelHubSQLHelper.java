package thhsu.chloe.ModelHub.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import thhsu.chloe.ModelHub.api.model.Cases;

/**
 * Created by Chloe on 5/14/2018.
 */

public class ModelHubSQLHelper extends SQLiteOpenHelper{

    private final static String DATABASE_NAME = "modelHub_data.db";
    private final static int DATABASE_VERSION = 1;
    private final static String CASE_TABLE = "case_table";

    private final static String USER_TOKEN = "user_token";
    private final static String CASE_ID = "case_id";
    private final static String CASE_TITLE = "case_title";
    private final static String CASE_URGENT = "case_urgent";
    private final static String CASE_RECOMMENDED = "case_recommended";
    private final static String CASE_POSTED_DATE = "case_posted_date";
    private final static String CASE_EXPIRED_DATE = "case_expired_date";
    private final static String CASE_COMPANY = "case_company";
    private final static String CASE_LOGO = "case_logo";
    private final static String CASE_IMAGE = "case_image";
    private final static String CASE_LOCATION = "case_location";
    private final static String CASE_TYPE = "case_type";
    private final static String CASE_DES = "case_des";
    private final static String CASE_REQUIREMENT = "case_requirement";
    private final static String CASE_BENEFIT = "case_benefit";
    private final static String CASE_HIRING_RESOURCE_FROM = "case_hiring_resource_from";
    private final static String CASE_HIRING_RESOURCE_CONTACT_NAME = "case_hiring_resource_contact_name";
    private final static String CASE_HIRING_RESOURCE_CONTACT_EMAIL = "case_hiring_resource_contact_email";
    private final static String CASE_HIRING_RESOURCE_INFO = "case_hiring_resource_info";
    private final static String CASE_SAVED = "case_saved";

    private Cursor mCursor;
    private boolean isInterestChanged = false;

    private final static String CREATE_TABLE = "CREATE TABLE " + CASE_TABLE + " ("
            + " _id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + CASE_ID + " TEXT NOT NULL UNIQUE, "
            + CASE_TITLE + " TEXT NOT NULL, "
            + CASE_URGENT + " INTEGER NOT NULL, "
            + CASE_RECOMMENDED + " INTEGER NOT NULL, "
            + CASE_POSTED_DATE + " TEXT NOT NULL, "
            + CASE_EXPIRED_DATE + " TEXT NOT NULL, "
            + CASE_COMPANY + " TEXT NOT NULL, "
            + CASE_LOGO + " TEXT NOT NULL, "
            + CASE_IMAGE + " TEXT NOT NULL, "
            + CASE_LOCATION + " TEXT NOT NULL, "
            + CASE_TYPE + " TEXT NOT NULL, "
            + CASE_DES + " TEXT NOT NULL, "
            + CASE_REQUIREMENT + " TEXT NOT NULL, "
            + CASE_BENEFIT + " TEXT NOT NULL, "
            + CASE_HIRING_RESOURCE_FROM + " TEXT NOT NULL, "
            + CASE_HIRING_RESOURCE_CONTACT_NAME + " TEXT NOT NULL, "
            + CASE_HIRING_RESOURCE_CONTACT_EMAIL + " TEXT NOT NULL, "
            + CASE_HIRING_RESOURCE_INFO + " TEXT NOT NULL, "
            + CASE_SAVED + " INTEGER NOT NULL) ";



    public ModelHubSQLHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CASE_TABLE);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CASE_TABLE);
    }

    private void insertCase(Cases acase, boolean isSaved){
        ContentValues contentValues = new ContentValues();

        Log.d("Chloe", "insert Job: " + acase.getId());

        contentValues.put(CASE_ID, acase.getId());
        contentValues.put(CASE_TITLE, acase.getTitle());
        contentValues.put(CASE_URGENT, acase.getUrgent());
        contentValues.put(CASE_RECOMMENDED, acase.getRecommended());
        contentValues.put(CASE_POSTED_DATE, acase.getDatePosted());
        contentValues.put(CASE_EXPIRED_DATE, acase.getDateExpires());
        contentValues.put(CASE_COMPANY, acase.getCompany());
        contentValues.put(CASE_LOGO, acase.getLogo());
        contentValues.put(CASE_IMAGE, acase.getImage());
        contentValues.put(CASE_LOCATION, acase.getLocation());
        contentValues.put(CASE_TYPE, acase.getType());
        contentValues.put(CASE_DES, acase.getDescription());
        contentValues.put(CASE_REQUIREMENT, acase.getRequirements());
        contentValues.put(CASE_BENEFIT, String.join(",", acase.getBenefits()));
        contentValues.put(CASE_HIRING_RESOURCE_FROM, acase.getHiring_source_from());
        contentValues.put(CASE_HIRING_RESOURCE_CONTACT_NAME, acase.getHiring_contact_name());
        contentValues.put(CASE_HIRING_RESOURCE_CONTACT_EMAIL, acase.getHiring_contact_email());
        contentValues.put(CASE_HIRING_RESOURCE_INFO, acase.getHiring_other_info());
        contentValues.put(CASE_SAVED, (isSaved)? 1: 0);

        getWritableDatabase().insert(CASE_TABLE, null, contentValues);
    }

    public void deleteCase(Cases acase){
       Log.d("Chloe", "delete acase: "+acase.getId()+
               getWritableDatabase().delete(CASE_TABLE, CASE_ID + "=?", new String[] {acase.getId()}));
    }


    public void updateCases(Cases acase, boolean isSaved){
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(CASE_SAVED, (isSaved)? 1:0);

        if(isSaved && !isCaseDataExist(acase.getId())){
            Log.d("Chloe", "insert acase"+ acase.getId());
            insertCase(acase, isSaved);
        } else {
            deleteCase(acase);
        }
            setInterestChanged(true);
//                Log.d("Chloe", "Job: " + acase.getId() + " exist, update is_saved is " + isSaved + ".");
//                getWritableDatabase().update(CASE_TABLE, contentValues,
//                        CASE_ID + "='" + acase.getId() + "'", null);

    }

    public boolean getInterest(String caseId){
        return  isCaseDataExist(caseId);
//        if(isCaseDataExist(caseId)){
//            Log.d("Chloe", "if job data exist: true");
//            mCursor = getWritableDatabase().query(CASE_TABLE,
//                    new String[]{CASE_SAVED}, CASE_ID + "='" + caseId + "'",
//                    null,null,null,null);
//            mCursor.moveToFirst();
//            return mCursor.getInt(mCursor.getColumnIndex(CASE_SAVED)) == 1;
//        }else{
//            Log.d("Chloe", "if job data exist: false");
//            return false;
//        }
    }


    public boolean isCaseDataExist(String jobId){
        Log.d("Chloe", "isJobData exist ID: " + jobId);
        mCursor = getWritableDatabase().query(CASE_TABLE,
                new String[]{CASE_SAVED},
                CASE_ID +"=?",
                new String[]{jobId}
                ,null,null,null);
        Log.d("Chloe", "cursor count: " + mCursor.getCount());
        return (mCursor.getCount() > 0) ? true : false;
    }

    public boolean isInterestChanged(){return isInterestChanged;}

    public void setInterestChanged(boolean interestChanged){
        isInterestChanged = interestChanged;
    }

    public ArrayList<Cases> getInterest(){

        mCursor = getWritableDatabase().query(CASE_TABLE,
                new String[]{CASE_ID, CASE_TITLE, CASE_URGENT, CASE_RECOMMENDED,
                        CASE_POSTED_DATE, CASE_EXPIRED_DATE, CASE_COMPANY, CASE_LOGO,
                        CASE_IMAGE, CASE_LOCATION, CASE_TYPE, CASE_DES, CASE_REQUIREMENT, CASE_BENEFIT,
                        CASE_HIRING_RESOURCE_FROM, CASE_HIRING_RESOURCE_CONTACT_NAME, CASE_HIRING_RESOURCE_CONTACT_EMAIL,
                        CASE_HIRING_RESOURCE_INFO, CASE_SAVED}, CASE_SAVED + "=1",
                null, null, null, null);
        ArrayList<Cases> cases = new ArrayList<>();
        while (mCursor.moveToNext()){
            Cases acase = new Cases();
            String[] test;
            acase.setId(mCursor.getString(0));
            acase.setTitle(mCursor.getString(mCursor.getColumnIndex(CASE_TITLE)));
            acase.setUrgent((mCursor.getInt(mCursor.getColumnIndex(CASE_URGENT)) == 1)? true:false);
            acase.setRecommended((mCursor.getInt(mCursor.getColumnIndex(CASE_RECOMMENDED)) == 1)? true: false);
            acase.setDatePosted(mCursor.getString(mCursor.getColumnIndex(CASE_POSTED_DATE)));
            acase.setCompany(mCursor.getString(mCursor.getColumnIndex(CASE_COMPANY)));
            acase.setLogo(mCursor.getString(mCursor.getColumnIndex(CASE_LOGO)));
            acase.setLocation(mCursor.getString(mCursor.getColumnIndex(CASE_LOCATION)));
            acase.setType(mCursor.getString(mCursor.getColumnIndex(CASE_TYPE)));
            acase.setDescription(mCursor.getString(mCursor.getColumnIndex(CASE_DES)));
            acase.setRequirements(mCursor.getString(mCursor.getColumnIndex(CASE_REQUIREMENT)));
            acase.setBenefits(mCursor.getString(mCursor.getColumnIndex(CASE_BENEFIT)).split( "\\,"));
            acase.setHiring_source_from(mCursor.getString(mCursor.getColumnIndex(CASE_HIRING_RESOURCE_FROM)));
            acase.setHiring_contact_name(mCursor.getString(mCursor.getColumnIndex(CASE_HIRING_RESOURCE_CONTACT_NAME)));
            acase.setHiring_contact_email(mCursor.getString(mCursor.getColumnIndex(CASE_HIRING_RESOURCE_CONTACT_EMAIL)));
            acase.setHiring_other_info(mCursor.getString(mCursor.getColumnIndex(CASE_HIRING_RESOURCE_INFO)));
            acase.setSaved((mCursor.getInt(mCursor.getColumnIndex(CASE_SAVED)) == 1)? true: false);
            cases.add(acase);

        }
        return cases;
    }
}
