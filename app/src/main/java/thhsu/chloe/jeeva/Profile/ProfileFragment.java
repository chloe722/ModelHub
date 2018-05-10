package thhsu.chloe.jeeva.Profile;

import android.Manifest;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Date;
import java.util.Locale;

import thhsu.chloe.jeeva.R;
import thhsu.chloe.jeeva.Utils.Constants;
import thhsu.chloe.jeeva.activities.AboutMeActivity;
import thhsu.chloe.jeeva.activities.JeevaActivity;

/**
 * Created by Chloe on 4/30/2018.
 */

public class ProfileFragment extends Fragment implements ProfileContract.View, View.OnClickListener {

    ProfileContract.Presenter mPresenter;
    Button mEditInfoBtn, mCameraBtn;
    ImageView mUserPhotoView;
    Uri fileUri;
    Context mContext;

    public static ProfileFragment newInstance(){
        return new ProfileFragment();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        mEditInfoBtn = (Button) root.findViewById(R.id.profile_edited_btn);
        mCameraBtn = (Button) root.findViewById(R.id.profile_camera_btn);
        mUserPhotoView = (ImageView) root.findViewById(R.id.profile_user_photo);
        mContext = getActivity();

        mEditInfoBtn.setOnClickListener(this);
        mCameraBtn.setOnClickListener(this);

//        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
//            mCameraBtn.setEnabled(false);
//            ActivityCompat.requestPermissions(getActivity(), new String[] { Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE }, 0);
//        }

        return root;
    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        if (requestCode == 0) {
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
//                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
//                mCameraBtn.setEnabled(true);
//            }
//        }
//    }

    @Override
    public void setPresenter(ProfileContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.profile_edited_btn:
                Intent intent = new Intent(getActivity(), AboutMeActivity.class);
                startActivityForResult(intent, Constants.USER_INFO_REQUEST);
                break;
            case R.id.profile_camera_btn:
                takePhoto();
                break;
        }
    }

//    private File getOutputPhotoFile() {
//        File directory = new File(Environment.getExternalStoragePublicDirectory(
//                Environment.DIRECTORY_PICTURES), "CameraTesting");
//        if (!directory.exists()) {
//            if (!directory.mkdirs()) {
//                Log.e("Chloe", "Failed to create storage directory.");
//                return null;
//            }
//        }
//        String timeStamp = new SimpleDateFormat("yyyMMdd_HHmmss", Locale.UK).format(new Date());
//        return new File(directory.getPath() + File.separator + "IMG_"
//                + timeStamp + ".jpg");
//    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.CAPTURE_IMAGE_FRAGMENT_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                Bitmap bmp = (Bitmap) data.getExtras().get("data");


                Uri selectedImage = getImageUri(getActivity(), bmp);
                String path = getRealPathFromURI(selectedImage);
                selectedImage = Uri.parse(path);
                Log.d("Chloe", "selected image: " + selectedImage);
                mUserPhotoView.setImageBitmap(bmp);

            }
        }
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Testing", null);
        return Uri.parse(path);
    }

    public String getRealPathFromURI(Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = { MediaStore.Images.Media.DATA };
            cursor = getActivity().getContentResolver().query(contentUri,  proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public void takePhoto(){
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        fileUri = Uri.fromFile(getOutputPhotoFile());
//        i.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        startActivityForResult(i, Constants.CAPTURE_IMAGE_FRAGMENT_REQUEST);
    }

}
