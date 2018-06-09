//package thhsu.chloe.ModelHub.activities;
//
//import android.Manifest;
//import android.app.Activity;
//import android.app.AlertDialog;
//import android.content.ActivityNotFoundException;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.content.pm.PackageManager;
//import android.content.pm.ResolveInfo;
//import android.database.Cursor;
//import android.graphics.Bitmap;
//import android.icu.text.SimpleDateFormat;
//import android.net.Uri;
//import android.os.Bundle;
//import android.os.Environment;
//import android.provider.DocumentsContract;
//import android.provider.MediaStore;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.support.design.widget.BottomNavigationView;
//import android.support.design.widget.BottomSheetDialog;
//import android.support.design.widget.TabLayout;
//import android.support.v4.content.FileProvider;
//import android.support.v4.view.ViewPager;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.RecyclerView;
//import android.support.v7.widget.Toolbar;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.squareup.picasso.Picasso;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.Date;
//import java.util.List;
//
//import permissions.dispatcher.NeedsPermission;
//import permissions.dispatcher.OnNeverAskAgain;
//import permissions.dispatcher.OnPermissionDenied;
//import permissions.dispatcher.OnShowRationale;
//import permissions.dispatcher.PermissionRequest;
//import thhsu.chloe.ModelHub.Profile.ProfileContract;
////import thhsu.chloe.ModelHub.Profile.ProfileFragmentPermissionsDispatcher;
//import thhsu.chloe.ModelHub.R;
//import thhsu.chloe.ModelHub.Utils.CircleTransform;
//import thhsu.chloe.ModelHub.Utils.Constants;
//import thhsu.chloe.ModelHub.adapters.ProfileViewPagerAdapter;
//import thhsu.chloe.ModelHub.api.model.User;
//
///**
// * Created by Chloe on 5/31/2018.
// */
//
//public class ProfileActivity extends BaseActivity {
//    private ViewPager mViewPager;
//    private TabLayout mTablayout;
//    private ProfileViewPagerAdapter mViewPagerAdapter;
//    private BottomNavigationView bottomNavigationView;
//    ProfileContract.Presenter mPresenter;
//    Button mEditInfoBtn, mCameraBtn;
//    TextView mUserName, mUserEmail, mUserNumber, mUserHeight, mUserLocation, mUserWeight, mUserNationality, mUserBio, mUserLanguage, mUserExperience;
//    ImageView mUserPhotoView, mUserCoverImage;
//    private Uri mImageUri;
//    Context mContext;
//    String userToken, userName, userEmail, userHeight, userLocationCountry,
//            userLocationCity, userLocation, userWeight, userNationality, userBio, userLanguage, userExperience;
//    BottomSheetDialog mBottomSheetDialog;
//    SharedPreferences sharedPreferences;
//    private User mUser = new User();
//    private String mCurrentPhotoPath;
//    boolean isIconActivate = false;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.profile_testing);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_cor);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);
//
//         mViewPager = (ViewPager) findViewById(R.id.viewpager_cor);
//         mViewPagerAdapter = new ProfileViewPagerAdapter(getSupportFragmentManager());
//         mViewPager.setAdapter(mViewPagerAdapter);
//         mTablayout = (TabLayout) findViewById(R.id.profile_tablayout);
//         mTablayout.setupWithViewPager(mViewPager);
//        mCameraBtn = (Button) findViewById(R.id.profileactivity_camera_btn);
//        mUserPhotoView = (ImageView) findViewById(R.id.profile_user_photo);
//        mUserName = (TextView) findViewById(R.id.profile_user_name);
//        mUserHeight = (TextView) findViewById(R.id.profile_user_height_text);
//        mUserLocation = (TextView) findViewById(R.id.profile_user_location);
//        mUserWeight = (TextView) findViewById(R.id.profile_user_weight_text);
//        mUserNationality = (TextView) findViewById(R.id.profile_user_nationality_text);
//        mUserBio = (TextView) findViewById(R.id.profile_user_bio_text);
//        mUserExperience = (TextView) findViewById(R.id.profile_user_experience_text);
//        mUserLanguage = (TextView) findViewById(R.id.profile_user_language_text);
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
////        ProfileFragmentPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
//
//    }
//
//
//
//
////    @Override
////    public void setPresenter(ProfileContract.Presenter presenter) {
////        mPresenter = presenter;
////    }
//
//
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.profileactivity_camera_btn:
////                ProfileFragmentPermissionsDispatcher.requestPermissionsWithPermissionCheck(this);
//                takePhoto();
//                break;
//
//            case R.id.fragment_profile_camera:
//                mBottomSheetDialog.hide();
//                takePhoto();
//                break;
//
//            case R.id.fragment_profile_gallery:
//                mBottomSheetDialog.hide();
//                pickImage();
//                break;
//
//        }
//    }
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        Log.d("Chloe", "requestCode" + requestCode + "resultCode" + resultCode);
//        if (requestCode == Constants.CAPTURE_IMAGE_FRAGMENT_REQUEST) {
//            if (resultCode == Activity.RESULT_OK) {
//                performCrop(mImageUri);
//            }
//        }
//        else if(requestCode == Constants.PICK_IMAGE_REQUEST){
//            if(resultCode == Activity.RESULT_OK){
//                Log.d("Chloe", "image uri: " + mImageUri);
//                Log.d("Chloe", "data.getdata:" + data.getData());
//                String path = getRealPathFromURI(data.getData());
//                Log.d("Chloe", "path: " + path);
//                File imageFile = new File(path);
//                Log.d("Chloe", "imageFile: " + imageFile);
//                Log.d("Chloe", "imageFile: " + imageFile.getAbsolutePath());
//
//                Log.d("Chloe", "other image uri: " + mImageUri);
//                performCrop(mImageUri, FileProvider.getUriForFile(this,"thhsu.chloe.ModelHub", imageFile));
//            }
//        }
//        else if (requestCode == Constants.CROP_IMAGE) {
//            if(!mImageUri.equals("")){
//                Picasso.get().load(mImageUri).fit().centerCrop().transform(new CircleTransform()).into(mUserPhotoView);
//            }
//        }
//    }
//
//    public Uri getImageUri(Context inContext, Bitmap inImage) {
//        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Image", null);
//        return Uri.parse(path);
//    }
//
//    public String getRealPathFromURI(Uri uri){
//        final String docId = DocumentsContract.getDocumentId(uri);
//        final String[] split = docId.split(":");
//        final String type = split[0];
//
//        Uri contentUri = null;
//        if ("image".equals(type)) {
//            contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
//        } else if ("video".equals(type)) {
//            contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
//        } else if ("audio".equals(type)) {
//            contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
//        }
//
//        final String selection = "_id=?";
//        final String[] selectionArgs = new String[]{
//                split[1]
//        };
//
//        return getDataColumn(this, contentUri, selection, selectionArgs);
//    }
//
//    public static String getDataColumn(Context context, Uri uri, String selection,
//                                       String[] selectionArgs) {
//        Cursor cursor = null;
//        final String column = "_data";
//        final String[] projection = {
//                column
//        };
//
//        try {
//            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
//                    null);
//            if (cursor != null && cursor.moveToFirst()) {
//                final int index = cursor.getColumnIndexOrThrow(column);
//                return cursor.getString(index);
//            }
//        } finally {
//            if (cursor != null)
//                cursor.close();
//        }
//        return null;
//    }
//
//
//
//    public void takePhoto() {
//        Intent takePicIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        if(takePicIntent.resolveActivity(this.getPackageManager()) != null){
//            File photoFile = null; // Create an file
//            try {
//                photoFile = createImageFile();
//            }catch (IOException ex){
//                ex.printStackTrace();
//            }
//            if(photoFile != null){
//                mImageUri = FileProvider.getUriForFile(this,"thhsu.chloe.ModelHub", photoFile); //mImageCameraTempUri
//                takePicIntent.putExtra(MediaStore.EXTRA_OUTPUT, mImageUri);
//                startActivityForResult(takePicIntent, Constants.CAPTURE_IMAGE_FRAGMENT_REQUEST);
//            }
//        }
//    }
//
//    public void pickImage(){
//        Intent intentPhotoPicker = new Intent(Intent.ACTION_OPEN_DOCUMENT);
//        intentPhotoPicker.setType("image/*");
//        if(intentPhotoPicker.resolveActivity(this.getPackageManager())!= null){
//            File imageFile = null;
//            try {
//                imageFile = createImageFile();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            if(imageFile != null){
//                mImageUri = FileProvider.getUriForFile(this,"thhsu.chloe.ModelHub", imageFile); //mImageCameraTempUri
//                startActivityForResult(intentPhotoPicker, Constants.PICK_IMAGE_REQUEST);
//            }
//        }
//    }
//
//    private void performCrop(Uri uri) {
//        try {
//            Intent cropIntent = new Intent("com.android.camera.action.CROP");
//            cropIntent.setDataAndType(uri, "image/*");
//            cropIntent.putExtra("crop", "true");
//            cropIntent.putExtra("aspectX", 1);
//            cropIntent.putExtra("aspectY", 1);
//            cropIntent.putExtra("outputX", 256);
//            cropIntent.putExtra("outputY", 256);
//            cropIntent.putExtra("return-data", false);
//            cropIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
//            cropIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION|Intent.FLAG_GRANT_WRITE_URI_PERMISSION); //For android 8.0 and after
//            startActivityForResult(cropIntent, Constants.CROP_IMAGE);
//        }
//        catch (ActivityNotFoundException anfe) {
//            Toast toast = Toast
//                    .makeText(this, "This device doesn't support the crop action!", Toast.LENGTH_SHORT);
//            toast.show();
//        }
//    }
//
//    private void performCrop(Uri uri, Uri galleryUri) {
//        try {
//            Intent cropIntent = new Intent("com.android.camera.action.CROP");
//            cropIntent.setDataAndType(galleryUri, "image/*");
//            cropIntent.putExtra("crop", "true");
//            cropIntent.putExtra("aspectX", 1);
//            cropIntent.putExtra("aspectY", 1);
//            cropIntent.putExtra("outputX", 256);
//            cropIntent.putExtra("outputY", 256);
//            cropIntent.putExtra("return-data", false);
//            cropIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
//            List<ResolveInfo> resInfoList= this.getPackageManager().queryIntentActivities(cropIntent, PackageManager.MATCH_DEFAULT_ONLY);
//            for (ResolveInfo resolveInfo : resInfoList) {
//                String packageName = resolveInfo.activityInfo.packageName;
//                this.grantUriPermission(packageName, uri,
//                        Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
//            }
////            getContext().grantUriPermission("thhsu.chloe.ModelHub", uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
//            cropIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION|Intent.FLAG_GRANT_WRITE_URI_PERMISSION); //For android 8.0 and after
//            startActivityForResult(cropIntent, Constants.CROP_IMAGE);
//        }
//        catch (ActivityNotFoundException anfe) {
//            Toast toast = Toast
//                    .makeText(this, "This device doesn't support the crop action!", Toast.LENGTH_SHORT);
//            toast.show();
//        }
//    }
//
//
//    private File createImageFile() throws IOException {
//        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
//        String imageFileName = "JPEG_" + timeStamp + "_";
//        File storageDir = this.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
//        File image = File.createTempFile(
//                imageFileName,  /* prefix */
//                ".jpg",         /* suffix */
//                storageDir      /* directory */ );
//        // Save a file: path for use with ACTION_VIEW intents
//        mCurrentPhotoPath = image.getAbsolutePath();
//        Log.i("Wayne", "mCurrentPhotoPath: " + mCurrentPhotoPath);
//        return image;
//    }
//
//
//
//    @NeedsPermission({Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
//    void requestPermissions() {
//        mBottomSheetDialog.show();
//    }
//
//    @OnPermissionDenied({Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
//    void onPermissionDenied() {
//        Toast.makeText(this, "You've denied access request", Toast.LENGTH_SHORT).show();
//    }
//
//    @OnNeverAskAgain({Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
//    void onNeverAskAgain() {
//        Toast.makeText(this, "You need to allow ModelHub access to use camera. Please go to SETTING to allow the access. ", Toast.LENGTH_SHORT).show();
//    }
//
//    @OnShowRationale({Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
//    void showRationalForCamera(final PermissionRequest request) {
//        new AlertDialog.Builder(this)
//                .setMessage("You need to allow access to Camera and Gallery. ")
//                .setPositiveButton(R.string.button_allow, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        request.proceed();
//                    }
//                })
//                .setNegativeButton(R.string.button_deny, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        request.cancel();
//                    }
//                })
//                .show();
//    }
//
//
//}
