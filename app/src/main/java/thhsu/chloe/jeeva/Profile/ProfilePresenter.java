package thhsu.chloe.jeeva.Profile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import thhsu.chloe.jeeva.Utils.Constants;
import thhsu.chloe.jeeva.activities.JeevaActivity;
import thhsu.chloe.jeeva.api.model.User;

/**
 * Created by Chloe on 4/30/2018.
 */

public class ProfilePresenter implements ProfileContract.Presenter{

    private Uri mImageUri;
    Context mContext;
    private User mUser = new User();
    private String mCurrentPhotoPath;
    private ProfileContract.View mProfileView;
    private JeevaActivity mActivity;

    public ProfilePresenter(ProfileContract.View profileView, JeevaActivity jeevaActivity){
        mProfileView = profileView;
        if(profileView != null){
            mProfileView.setPresenter(this);
        }
        mActivity= jeevaActivity;
    }

    @Override
    public void start() {
    }

    @Override
    public void result(int requestCode, int resultCode) {
    }



    @Override
    public void takePhoto() {
        Intent takePicIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePicIntent.resolveActivity(mActivity.getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            if (photoFile != null) {
                mImageUri = FileProvider.getUriForFile(mActivity, "thhsu.chloe.jeeva.fileprovider", photoFile); //mImageCameraTempUri
                takePicIntent.putExtra(MediaStore.EXTRA_OUTPUT, mImageUri);
                mActivity.startActivityForResult(takePicIntent, Constants.CAPTURE_IMAGE_FRAGMENT_REQUEST);
            }
        }

    }

    @Override
    public void pickImage() {
        Intent intentPhotoPicker = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intentPhotoPicker.setType("image/*");
        mActivity.startActivityForResult(intentPhotoPicker, Constants.PICK_IMAGE_REQUEST);
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
        mCurrentPhotoPath = image.getAbsolutePath();
        Log.i("Wayne", "mCurrentPhotoPath: " + mCurrentPhotoPath);
        return image;
    }
}
