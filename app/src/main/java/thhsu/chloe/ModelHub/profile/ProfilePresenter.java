package thhsu.chloe.ModelHub.profile;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import thhsu.chloe.ModelHub.ModelHub;
import thhsu.chloe.ModelHub.activities.ModelHubActivity;
import thhsu.chloe.ModelHub.api.ApiJobManager;
import thhsu.chloe.ModelHub.api.GetUserInfoCallBack;
import thhsu.chloe.ModelHub.api.PostUserInfoCallBack;
import thhsu.chloe.ModelHub.api.UploadImageCallBack;
import thhsu.chloe.ModelHub.api.model.UpdateUserRequest;
import thhsu.chloe.ModelHub.api.model.User;
import thhsu.chloe.ModelHub.utils.Constants;

/**
 * Created by Chloe on 4/30/2018.
 */

public class ProfilePresenter implements ProfileContract.Presenter{
    private Uri mImageUri;
    private ProfileContract.View mProfileView;
    private ModelHubActivity mActivity;
    private File mCameraPhotoFile, mGalleryPhotoFile;
    private String userImageUrl, mUserToken;
    private User mUser = new User();

    public ProfilePresenter(ProfileContract.View profileView, ModelHubActivity modelHubActivity, String token){
        mProfileView = profileView;
        if(profileView != null){
            mProfileView.setPresenter(this);
        }
        mActivity= modelHubActivity;
        mUserToken = token;
    }



    @Override
    public void start() {}

    @Override
    public void result(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.CAPTURE_IMAGE_FRAGMENT_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                performCropImageFromCamera(mImageUri);
            }

        } else if (requestCode == Constants.PICK_IMAGE_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                String path = getRealPathFromURI(data.getData());
                mGalleryPhotoFile = new File(path);
                performCropImageFromGallery(mImageUri, FileProvider.getUriForFile(mActivity, "thhsu.chloe.ModelHub", mGalleryPhotoFile));
            }

        } else if (requestCode == Constants.CROP_IMAGE) {
            if (!mImageUri.equals("")) {

                if(mCameraPhotoFile != null){
                    uploadImage(mCameraPhotoFile);
                }else{
                    uploadImage(mGalleryPhotoFile);
                }
//                mProfileView.showUserPhoto(mImageUri);
            }
        }
    }

    @Override
    public void showUserInfo() {
        SharedPreferences sharedPreferences = ModelHub.getAppContext().getSharedPreferences(Constants.USER_DATA, Context.MODE_PRIVATE);
        String userToken = sharedPreferences.getString(Constants.USER_TOKEN, "");

        if(!userToken.equals("")){
            ApiJobManager.getInstance().getUserData(userToken, new GetUserInfoCallBack() {
                @Override
                public void onCompleted(User user) {
                    mProfileView.showUserInfoUi(user);
                }
                @Override
                public void onError(String errorMessage) {
                }
            });
        }
    }

    @Override
    public void takePhoto() {
        Intent takePicIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(takePicIntent.resolveActivity(mActivity.getPackageManager()) != null){
            mCameraPhotoFile = null; // Create an file
            try {
                mCameraPhotoFile = createImageFile();
            }catch (IOException ex){
                ex.printStackTrace();
            }
            if(mCameraPhotoFile != null){
                mImageUri = FileProvider.getUriForFile(mActivity,"thhsu.chloe.ModelHub", mCameraPhotoFile); //mImageCameraTempUri
                takePicIntent.putExtra(MediaStore.EXTRA_OUTPUT, mImageUri);
                takePicIntent.putExtra("ImageUri", mImageUri);
                mActivity.startActivityForResult(takePicIntent, Constants.CAPTURE_IMAGE_FRAGMENT_REQUEST);
            }
        }

    }

    @Override
    public void pickImage() {
        Intent intentPhotoPicker = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intentPhotoPicker.setType("image/*");
        if(intentPhotoPicker.resolveActivity(mActivity.getPackageManager())!= null){
            File mGalleryPhotoFile = null;
            try {
                mGalleryPhotoFile = createImageFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(mGalleryPhotoFile != null){
                mImageUri = FileProvider.getUriForFile(mActivity,"thhsu.chloe.ModelHub", mGalleryPhotoFile); //mImageCameraTempUri
                mActivity.startActivityForResult(intentPhotoPicker, Constants.PICK_IMAGE_REQUEST);
            }
        }
    }

    private void uploadImage(File file){
        ApiJobManager.getInstance().upLoadImage(file,new UploadImageCallBack(){
            @Override
            public void onComplete(String url){
                userImageUrl="https://modelhub.tw"+url;
                updateUserPhoto(userImageUrl);
            }

            @Override
            public void onError(String errorMessage){}
        });
    }

    private void updateUserPhoto(final String photoUrl){
        UpdateUserRequest r = new UpdateUserRequest();
        r.token = mUserToken;
        r.user.setProfilePic(photoUrl);
        Log.d("Chloe", "token: " + mUserToken);
        ApiJobManager.getInstance().getPostUserInfoResult( r ,new PostUserInfoCallBack(){
            @Override
            public void onComplete(){
                mProfileView.showUserPhoto(photoUrl);

            }

            @Override
            public void onError(String errorMessage){
                    Log.d("Chloe","error: "+errorMessage);
            }});
    }


    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = mActivity.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */);
        // Save a file: path for use with ACTION_VIEW intents
        String currentPhotoPath = image.getAbsolutePath();
        Log.i("Chloe", "mCurrentPhotoPath: " + currentPhotoPath);
        return image;
    }

    private void performCropImageFromCamera(Uri uri) {
        try {
            Intent cropIntent = new Intent("com.android.camera.action.CROP");
            cropIntent.setDataAndType(uri, "image/*");
            cropIntent.putExtra("crop", "true");
            cropIntent.putExtra("aspectX", 1);
            cropIntent.putExtra("aspectY", 1);
            cropIntent.putExtra("outputX", 256);
            cropIntent.putExtra("outputY", 256);
            cropIntent.putExtra("return-data", false);
            cropIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            cropIntent.putExtra("imageUri", uri);
            cropIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION|Intent.FLAG_GRANT_WRITE_URI_PERMISSION); //For android 8.0 and after
            mActivity.startActivityForResult(cropIntent, Constants.CROP_IMAGE);
        }
        catch (ActivityNotFoundException anfe) {
            Toast toast = Toast
                    .makeText(mActivity, "This device doesn't support the crop action!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    private void performCropImageFromGallery(Uri uri, Uri galleryUri) {
        try {
            Intent cropIntent = new Intent("com.android.camera.action.CROP");
            cropIntent.setDataAndType(galleryUri, "image/*");
            cropIntent.putExtra("crop", "true");
            cropIntent.putExtra("aspectX", 1);
            cropIntent.putExtra("aspectY", 1);
            cropIntent.putExtra("outputX", 256);
            cropIntent.putExtra("outputY", 256);
            cropIntent.putExtra("return-data", false);
            cropIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            List<ResolveInfo> resInfoList= mActivity.getPackageManager().queryIntentActivities(cropIntent, PackageManager.MATCH_DEFAULT_ONLY);
            for (ResolveInfo resolveInfo : resInfoList) {
                String packageName = resolveInfo.activityInfo.packageName;
                mActivity.grantUriPermission(packageName, uri,
                        Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            }
            cropIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION|Intent.FLAG_GRANT_WRITE_URI_PERMISSION); //For android 8.0 and after
            mActivity.startActivityForResult(cropIntent, Constants.CROP_IMAGE);
        }
        catch (ActivityNotFoundException anfe) {
            Toast toast = Toast
                    .makeText(mActivity, "This device doesn't support the crop action!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }


    private String getRealPathFromURI(Uri uri){
        final String docId = DocumentsContract.getDocumentId(uri);
        final String[] split = docId.split(":");
        final String type = split[0];

        Uri contentUri = null;
        if ("image".equals(type)) {
            contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        } else if ("video".equals(type)) {
            contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        } else if ("audio".equals(type)) {
            contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        }

        final String selection = "_id=?";
        final String[] selectionArgs = new String[]{
                split[1]
        };

        return getDataColumn(mActivity, contentUri, selection, selectionArgs);
    }


    private static String getDataColumn(Context context, Uri uri, String selection,
                                        String[] selectionArgs) {
        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }
}




