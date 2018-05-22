package thhsu.chloe.jeeva.Profile;

import android.Manifest;
import android.app.Activity;
import android.app.Fragment;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
//import com.theartofdev.edmodo.cropper.CropImage;
//import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import thhsu.chloe.jeeva.Jeeva;
import thhsu.chloe.jeeva.R;
import thhsu.chloe.jeeva.Utils.CircleTransform;
import thhsu.chloe.jeeva.Utils.Constants;
import thhsu.chloe.jeeva.api.ApiJobManager;
import thhsu.chloe.jeeva.api.GetUserInfoCallBack;
import thhsu.chloe.jeeva.api.model.User;

/**
 * Created by Chloe on 4/30/2018.
 */

public class ProfileFragment extends Fragment implements ProfileContract.View, View.OnClickListener {

    ProfileContract.Presenter mPresenter;
    Button mEditInfoBtn, mCameraBtn;
    TextView mUserName, mUserEmail, mUserNumber, mUserJobTitle, mUserLocation;
    ImageView mUserPhotoView;
    private Uri mImageUri;
    Context mContext;
    String userToken, userName, userEmail, userJobTitle, userLocationCountry, userLocationCity, userLocation, userFacebookUsername, userGithubUsername, userLinkedinUsername;
    BottomSheetDialog mBottomSheetDialog;
    SharedPreferences sharedPreferences;
    private User mUser = new User();
    private String mCurrentPhotoPath;

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        mContext = getActivity();
        sharedPreferences = Jeeva.getAppContext().getSharedPreferences(Constants.USER_DATA, Context.MODE_PRIVATE);
        userToken = sharedPreferences.getString(Constants.USER_TOKEN, "");
        mCameraBtn = (Button) root.findViewById(R.id.profile_camera_btn);
        mUserPhotoView = (ImageView) root.findViewById(R.id.profile_user_photo);
        mUserName = (TextView) root.findViewById(R.id.profile_user_name);
        mUserEmail = (TextView) root.findViewById(R.id.profile_user_email);
        mUserJobTitle = (TextView) root.findViewById(R.id.profile_user_job_title);
        mUserLocation = (TextView) root.findViewById(R.id.profile_user_location);
        mBottomSheetDialog = new BottomSheetDialog(getActivity());
        View sheetView = getActivity().getLayoutInflater().inflate(R.layout.fragment_profile_bottomsheet, null);
        mBottomSheetDialog.setContentView(sheetView);
        LinearLayout camera = (LinearLayout) sheetView.findViewById(R.id.fragment_profile_camera);
        LinearLayout gallery = (LinearLayout) sheetView.findViewById(R.id.fragment_profile_gallery);
        camera.setOnClickListener(this);
        gallery.setOnClickListener(this);
        mCameraBtn.setOnClickListener(this);

        if(!sharedPreferences.getString(Constants.USER_EMAIL, "").equals("")){
            userEmail = sharedPreferences.getString(Constants.USER_EMAIL, "");
            mUserEmail.setText(userEmail);
        }

        if(!sharedPreferences.getString(Constants.USER_NAME, "").equals("")){
            userName = sharedPreferences.getString(Constants.USER_NAME, "");
            mUserName.setText(userName);
        }

        if(!userToken.equals("")){
            ApiJobManager.getInstance().getUserData(userToken, new GetUserInfoCallBack() {
                @Override
                public void onCompleted(User user) {
                    mUser = user;
                    userName = mUser.getName();
                    userLocationCity = mUser.getCity();
                    userEmail = mUser.getEmail();
                    mUserName.setText(userName);
                    mUserEmail.setText(userEmail);
                    userLocationCountry = mUser.getCountry();
                    userLocation = userLocationCity + ", " + userLocationCountry;
                    mUserLocation.setText(userLocation);
                    if(mUserJobTitle.equals("") ||  mUserJobTitle.equals(null)){
                        mUserJobTitle.setVisibility(View.GONE);
                    }else if(mUserLocation.equals("") || mUserLocation.equals(null)){
                        mUserLocation.setVisibility(View.GONE);
                    }
                }
                @Override
                public void onError(String errorMessage) {
                    mUserLocation.setText("");
                }
            });
        }
        return root;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 100) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED
                    && grantResults[2] == PackageManager.PERMISSION_GRANTED)  {
                mCameraBtn.setEnabled(true);
            }
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void setPresenter(ProfileContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.profile_camera_btn:
                mBottomSheetDialog.show();
                if ((ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) ||
                        (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) ||
                        (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
                    mCameraBtn.setEnabled(false);
                    ActivityCompat.requestPermissions(getActivity(), new String[] { Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
                }
                break;

            case R.id.fragment_profile_camera:
                mBottomSheetDialog.hide();
                takePhoto();
                break;

            case R.id.fragment_profile_gallery:
                mBottomSheetDialog.hide();
                pickImage();
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("Chloe", "requestCode" + requestCode + "resultCode" + resultCode);
        if (requestCode == Constants.CAPTURE_IMAGE_FRAGMENT_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                performCrop(mImageUri);
            }
        }
        else if(requestCode == Constants.PICK_IMAGE_REQUEST){
            if(resultCode == Activity.RESULT_OK){
                mImageUri = data.getData();
                performCrop(mImageUri);
            }
        }
        else if (requestCode == Constants.CROP_IMAGE) {
            if(!mImageUri.equals("")){
                try {
                    Bitmap croppedImageFromGalleryBitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), mImageUri);
                    Uri croppedImageFromGalleryUri = getImageUri(getContext(), croppedImageFromGalleryBitmap);
                    Picasso.get().load(croppedImageFromGalleryUri).fit().centerCrop().transform(new CircleTransform()).into(mUserPhotoView);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Image", null);
        return Uri.parse(path);
    }


    public void takePhoto() {
        Intent takePicIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(takePicIntent.resolveActivity(getActivity().getPackageManager()) != null){
            File photoFile = null;
            try {
                photoFile = createImageFile();
            }catch (IOException ex){
                ex.printStackTrace();
            }
            if(photoFile != null){
                mImageUri = FileProvider.getUriForFile(getActivity(),"thhsu.chloe.jeeva.fileprovider", photoFile); //mImageCameraTempUri
                takePicIntent.putExtra(MediaStore.EXTRA_OUTPUT, mImageUri);
                startActivityForResult(takePicIntent, Constants.CAPTURE_IMAGE_FRAGMENT_REQUEST);
            }
        }
    }

    public void pickImage(){
        Intent intentPhotoPicker = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intentPhotoPicker.setType("image/*");
        startActivityForResult(intentPhotoPicker, Constants.PICK_IMAGE_REQUEST);
    }

    private void performCrop(Uri uri) {
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
            cropIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION|Intent.FLAG_GRANT_WRITE_URI_PERMISSION); //For android 8.0 and after
            startActivityForResult(cropIntent, Constants.CROP_IMAGE);
        }
        catch (ActivityNotFoundException anfe) {
            Toast toast = Toast
                    .makeText(getActivity(), "This device doesn't support the crop action!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */ );
        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        Log.i("Wayne", "mCurrentPhotoPath: " + mCurrentPhotoPath);
        return image;
    }
}
