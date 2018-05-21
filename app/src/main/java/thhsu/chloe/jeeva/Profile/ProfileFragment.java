package thhsu.chloe.jeeva.Profile;

import android.Manifest;
import android.app.Activity;
import android.app.Fragment;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
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

import com.makeramen.roundedimageview.RoundedImageView;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import thhsu.chloe.jeeva.Jeeva;
import thhsu.chloe.jeeva.R;
import thhsu.chloe.jeeva.Utils.Constants;
import thhsu.chloe.jeeva.activities.AboutMeActivity;
import thhsu.chloe.jeeva.api.ApiJobManager;
import thhsu.chloe.jeeva.api.GetUserInfoCallBack;
import thhsu.chloe.jeeva.api.model.User;

import static android.support.v4.content.FileProvider.getUriForFile;

/**
 * Created by Chloe on 4/30/2018.
 */

public class ProfileFragment extends Fragment implements ProfileContract.View, View.OnClickListener {

    ProfileContract.Presenter mPresenter;
    Button mEditInfoBtn, mCameraBtn;
    TextView mUserName, mUserEmail, mUserNumber, mUserJobTitle, mUserLocation;
    RoundedImageView mUserPhotoView;
    Uri tempFileUri, imageUri;
    Context mContext;
    String userToken, userName, userEmail, userJobTitle, userLocationCountry, userLocationCity, userLocation, userFacebookUsername, userGithubUsername, userLinkedinUsername;
    Bitmap bitmap;
    Bundle extras;
    BottomSheetDialog mBottomSheetDialog;
    InputStream imageStream;
    SharedPreferences sharedPreferences;
    private User mUser = new User();
    File imagePath = new File(getContext().getFilesDir(), "images");
    File newFile = new File(imagePath, "default_image.jpg");
    Uri contentUri = getUriForFile(getContext(), "thhsu.chloe.jeeva.fileprovider", newFile);



    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        mContext = getActivity();
//        mEditInfoBtn = (Button) root.findViewById(R.id.profile_edited_btn);
        sharedPreferences = Jeeva.getAppContext().getSharedPreferences(Constants.USER_DATA, Context.MODE_PRIVATE);
        userToken = sharedPreferences.getString(Constants.USER_TOKEN, "");
        mCameraBtn = (Button) root.findViewById(R.id.profile_camera_btn);
        mUserPhotoView = (RoundedImageView) root.findViewById(R.id.profile_user_photo);
        mUserName = (TextView) root.findViewById(R.id.profile_user_name);
        mUserEmail = (TextView) root.findViewById(R.id.profile_user_email);
        mUserJobTitle = (TextView) root.findViewById(R.id.profile_user_job_title);
        mUserLocation = (TextView) root.findViewById(R.id.profile_user_location);
        mBottomSheetDialog = new BottomSheetDialog(getActivity());
        View sheetView = getActivity().getLayoutInflater().inflate(R.layout.fragment_profile_bottomsheet, null);
        mBottomSheetDialog.setContentView(sheetView);
//        CropImage.activity().setGuidelines(CropImageView.Guidelines.ON).start(getActivity());
//        CropImage.activity(imageUri).start(getActivity());
//        CropImage.activity().start(getContext(), this);

        LinearLayout camera = (LinearLayout) sheetView.findViewById(R.id.fragment_profile_camera);
        LinearLayout gallery = (LinearLayout) sheetView.findViewById(R.id.fragment_profile_gallery);
        camera.setOnClickListener(this);
        gallery.setOnClickListener(this);
//        mEditInfoBtn.setOnClickListener(this);
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
                    userLocationCity = mUser.getCity();
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

//        if ((ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) ||
//                (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
//
//            mCameraBtn.setEnabled(false);
//            ActivityCompat.requestPermissions(getActivity(), new String[] { Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
//        }

        return root;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 100) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                mCameraBtn.setEnabled(true);
            }
        }
    }


//    @Override
//    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//
//    }

    @Override
    public void setPresenter(ProfileContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.profile_edited_btn:
//                Intent intent = new Intent(getActivity(), AboutMeActivity.class);
//                startActivityForResult(intent, Constants.USER_INFO_REQUEST);
//                break;

            case R.id.profile_camera_btn:
                mBottomSheetDialog.show();

                break;

            case R.id.fragment_profile_camera:
                mBottomSheetDialog.hide();
                takePhoto();
                break;

            case R.id.fragment_profile_gallery:
                mBottomSheetDialog.hide();
//                pickImage();
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
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("Chloe", "requestCode" + requestCode + "resultCode" + resultCode);
        if (requestCode == Constants.CAPTURE_IMAGE_FRAGMENT_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                extras = data.getExtras();
                bitmap = (Bitmap) extras.get("data");
                tempFileUri = getImageUri(getContext(), bitmap);
                Toast.makeText(getActivity(),"Here "+ getRealPathFromURI(tempFileUri), Toast.LENGTH_LONG).show();
                performCrop(tempFileUri);
            }
        }else if(requestCode == Constants.PICK_IMAGE_REQUEST){
            if(resultCode == Activity.RESULT_OK){
                imageUri = data.getData();

//                tempFileUri = getRealPathFromURI(imageUri);
                performCrop(imageUri);

            }
        } else if (requestCode == Constants.CROP_IMAGE) {
            try {
                if(tempFileUri != null){
                    Bitmap croppedImage = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), tempFileUri);
                    Log.d("Chloe", "croppedImage: " + croppedImage);
                    Toast.makeText(getActivity(),"cropppedimage "+ croppedImage, Toast.LENGTH_LONG).show();
                    if(croppedImage != null){
                        Log.d("Chloe", "croppedImage: " + croppedImage);
                        mUserPhotoView.setImageBitmap(croppedImage);
                    }
                }else if(imageUri != null){
//                    imageStream = getActivity().getContentResolver().openInputStream(imageUri);
//                    Bitmap selectedBitmap = BitmapFactory.decodeStream(imageStream);
                    Bitmap lalaimage =  MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), imageUri);

                    if(lalaimage != null) {
                        mUserPhotoView.setImageBitmap(lalaimage);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (requestCode == Constants.USER_INFO_REQUEST) {
            if (resultCode == Constants.RESULT_SUCCESS) {
            }
        }
    }

//    private Bitmap decodeUriAsBitmap(Uri uri){
//        Bitmap bitmap = null;
//        try {
//            bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//            return null;
//        }
//        return bitmap;
//    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Testing", null);
        return Uri.parse(path);
    }

    public String getRealPathFromURI(Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            cursor = getActivity().getContentResolver().query(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public void takePhoto() {
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(i, Constants.CAPTURE_IMAGE_FRAGMENT_REQUEST);
    }

    private void performCrop(Uri uri) {
        try {
            // call the standard crop action intent (the user device may not
            // support it)
            Intent cropIntent = new Intent("com.android.camera.action.CROP");
            // indicate image type and Uri
            cropIntent.setDataAndType(uri, "image/*");
            // set crop properties
            cropIntent.putExtra("crop", "true");
            // indicate aspect of desired crop
            cropIntent.putExtra("aspectX", 1);
            cropIntent.putExtra("aspectY", 1);
            // indicate output X and Y
            cropIntent.putExtra("outputX", 256);
            cropIntent.putExtra("outputY", 256);
            // retrieve data on return
            cropIntent.putExtra("return-data", false); //
            cropIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri); // Displayed cropped image
            // start the activity - we handle returning in onActivityResult
            startActivityForResult(cropIntent, Constants.CROP_IMAGE);
        }
        // respond to users whose devices do not support the crop action
        catch (ActivityNotFoundException anfe) {
            Toast toast = Toast
                    .makeText(getActivity(), "This device doesn't support the crop action!", Toast.LENGTH_SHORT);
            toast.show();
        }

    }

//    private void performCropGallery(String picUri) {
//        try {
//            Intent cropIntent = new Intent("com.android.camera.action.CROP");
//            // indicate image type and Uri
//            File f = new File(picUri);
//            Uri contentUri = Uri.fromFile(f);
//
//            cropIntent.setDataAndType(contentUri, "image/*");
//            // set crop properties
//            cropIntent.putExtra("crop", "true");
//            // indicate aspect of desired crop
//            cropIntent.putExtra("aspectX", 1);
//            cropIntent.putExtra("aspectY", 1);
//            // indicate output X and Y
//            cropIntent.putExtra("outputX", 280);
//            cropIntent.putExtra("outputY", 280);
//
//            // retrieve data on return
//            cropIntent.putExtra("return-data", true);
//            // start the activity - we handle returning in onActivityResult
//            startActivityForResult(cropIntent, Constants.CROP_IMAGE);
//        }
//        // respond to users whose devices do not support the crop action
//        catch (ActivityNotFoundException anfe) {
//            // display an error message
//            String errorMessage = "your device doesn't support the crop action!";
//            Toast toast = Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_SHORT);
//            toast.show();
//        }
//    }

//    public void pickImage(){
//        Intent intentPhotoPicker = new Intent(Intent.ACTION_GET_CONTENT);
//        intentPhotoPicker.addCategory(Intent.CATEGORY_OPENABLE);
//        intentPhotoPicker.setType("image/*");
////        Uri uriForFile = FileProvider.getUriForFile(this, "thhsu.chloe.jeeva.fileprovider", mGalleryFile );
////        intentPhotoPicker.putExtra(MediaStore.EXTRA_OUTPUT, uriForFile);
//        intentPhotoPicker.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
//        intentPhotoPicker.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//        startActivityForResult(intentPhotoPicker, Constants.PICK_IMAGE_REQUEST);
//    }


}
