package thhsu.chloe.ModelHub.Profile;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;
import thhsu.chloe.ModelHub.ModelHub;
import thhsu.chloe.ModelHub.R;
import thhsu.chloe.ModelHub.Utils.CircleTransform;
import thhsu.chloe.ModelHub.Utils.Constants;
import thhsu.chloe.ModelHub.api.ApiJobManager;
import thhsu.chloe.ModelHub.api.GetUserInfoCallBack;
import thhsu.chloe.ModelHub.api.model.User;

/**
 * Created by Chloe on 4/30/2018.
 */

@RuntimePermissions
public class ProfileFragment extends Fragment implements ProfileContract.View, View.OnClickListener {

    ProfileContract.Presenter mPresenter;
    Button mEditInfoBtn, mCameraBtn;
    private ImageButton mUserFacebook, mUserGithub, mUserLinkedin;
    TextView mUserName, mUserEmail, mUserNumber, mUserJobTitle, mUserLocation;
    ImageView mUserPhotoView;
    private Uri mImageUri;
    Context mContext;
    String userToken, userName, userEmail, userJobTitle, userLocationCountry, userLocationCity, userLocation, userFacebookUsername, userGithubUsername, userLinkedinUsername;
    BottomSheetDialog mBottomSheetDialog;
    SharedPreferences sharedPreferences;
    private User mUser = new User();
    private String mCurrentPhotoPath;
    boolean isIconActivate = false;

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        mContext = getActivity();
        sharedPreferences = ModelHub.getAppContext().getSharedPreferences(Constants.USER_DATA, Context.MODE_PRIVATE);
        userToken = sharedPreferences.getString(Constants.USER_TOKEN, "");
        mCameraBtn = (Button) root.findViewById(R.id.profile_camera_btn);
        mUserPhotoView = (ImageView) root.findViewById(R.id.profile_user_photo);
        mUserName = (TextView) root.findViewById(R.id.profile_user_name);
        mUserEmail = (TextView) root.findViewById(R.id.profile_user_email);
        mUserJobTitle = (TextView) root.findViewById(R.id.profile_user_job_title);
        mUserLocation = (TextView) root.findViewById(R.id.profile_user_location);
        mUserFacebook = (ImageButton) root.findViewById(R.id.profile_user_facebook);
        mUserGithub = (ImageButton) root.findViewById(R.id.profile_user_github);
        mUserLinkedin = (ImageButton) root.findViewById(R.id.profile_user_linkedin);
        mBottomSheetDialog = new BottomSheetDialog(getActivity());
        View sheetView = getActivity().getLayoutInflater().inflate(R.layout.fragment_profile_bottomsheet, null);
        mBottomSheetDialog.setContentView(sheetView);
        LinearLayout camera = (LinearLayout) sheetView.findViewById(R.id.fragment_profile_camera);
        LinearLayout gallery = (LinearLayout) sheetView.findViewById(R.id.fragment_profile_gallery);
        camera.setOnClickListener(this);
        gallery.setOnClickListener(this);
        mCameraBtn.setOnClickListener(this);
        mUserFacebook.setOnClickListener(this);
        mUserGithub.setOnClickListener(this);
        mUserLinkedin.setOnClickListener(this);

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
                    userJobTitle = mUser.getJobTitle();
                    userFacebookUsername = mUser.getFacebookAccount();
                    userGithubUsername = mUser.getGithubAccount();
                    userLinkedinUsername = mUser.getLinkedinAccount();
                    mUserName.setText(userName);
                    mUserEmail.setText(userEmail);
                    userLocationCountry = mUser.getCountry();
                    userLocation = (userLocationCity != null ? userLocationCity + ",  " : "") + userLocationCountry != null ?userLocationCity : "";
                    mUserLocation.setText(userLocation);
                    mUserJobTitle.setText(userJobTitle);

                    if(!(mUserJobTitle == null || mUserJobTitle.equals(""))){
                        mUserJobTitle.setVisibility(View.VISIBLE);
                    }

                    if( !(mUserLocation == null || mUserLocation.equals(""))){
                        mUserLocation.setVisibility(View.VISIBLE);}

                    if( userFacebookUsername == null||userFacebookUsername.equals("")){
                        mUserFacebook.setVisibility(View.GONE);
                    }
                    if( userGithubUsername == null || userGithubUsername.equals("") ){
                        mUserGithub.setVisibility(View.GONE);
                    }
                    if( userLinkedinUsername == null || userLinkedinUsername.equals("")){
                        mUserLinkedin.setVisibility(View.GONE);
                    }
                }
                @Override
                public void onError(String errorMessage) {
                }
            });
        }
        return root;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        ProfileFragmentPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);

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
                ProfileFragmentPermissionsDispatcher.requestPermissionsWithPermissionCheck(this);
                break;

            case R.id.fragment_profile_camera:
                mBottomSheetDialog.hide();
                takePhoto();
                break;

            case R.id.fragment_profile_gallery:
                mBottomSheetDialog.hide();
                pickImage();
                break;
            case R.id.profile_user_facebook:
                isIconActivate = true;
                String url = Constants.FACEBOOK_URL + "houhou.xu";
                Intent intentToFacebook = new Intent(Intent.ACTION_VIEW);
                intentToFacebook.setData(Uri.parse(url));
                startActivity(intentToFacebook);
                break;

            case R.id.profile_user_github:
                isIconActivate = true;
                String githubUrl = Constants.GITHUB_URL + "chloe722";
                Intent intentToGithub = new Intent(Intent.ACTION_VIEW);
                intentToGithub.setData(Uri.parse(githubUrl));
                startActivity(intentToGithub);
                break;

            case R.id.profile_user_linkedin:
                isIconActivate = true;
                String linkedinUrl = Constants.LINKEDIN_URL + "skijur/";
                Intent intenToLinkedin = new Intent(Intent.ACTION_VIEW);
                intenToLinkedin.setData(Uri.parse(linkedinUrl));
                startActivity(intenToLinkedin);
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
                Log.d("Chloe", "image uri: " + mImageUri);
                Log.d("Chloe", "data.getdata:" + data.getData());
                String path = getRealPathFromURI(data.getData());
                Log.d("Chloe", "path: " + path);
                File imageFile = new File(path);
                    Log.d("Chloe", "imageFile: " + imageFile);
                    Log.d("Chloe", "imageFile: " + imageFile.getAbsolutePath());

                Log.d("Chloe", "other image uri: " + mImageUri);
                performCrop(mImageUri, FileProvider.getUriForFile(getContext(),"thhsu.chloe.jeeva.fileprovider", imageFile));
            }
        }
        else if (requestCode == Constants.CROP_IMAGE) {
            if(!mImageUri.equals("")){
                Picasso.get().load(mImageUri).fit().centerCrop().transform(new CircleTransform()).into(mUserPhotoView);
            }
        }
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Image", null);
        return Uri.parse(path);
    }

    public String getRealPathFromURI(Uri uri){
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

        return getDataColumn(getContext(), contentUri, selection, selectionArgs);
    }

    public static String getDataColumn(Context context, Uri uri, String selection,
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



    public void takePhoto() {
        Intent takePicIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(takePicIntent.resolveActivity(getActivity().getPackageManager()) != null){
            File photoFile = null; // Create an file
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
        if(intentPhotoPicker.resolveActivity(getActivity().getPackageManager())!= null){
            File imageFile = null;
            try {
                imageFile = createImageFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(imageFile != null){
                mImageUri = FileProvider.getUriForFile(getActivity(),"thhsu.chloe.jeeva.fileprovider", imageFile); //mImageCameraTempUri
                startActivityForResult(intentPhotoPicker, Constants.PICK_IMAGE_REQUEST);
            }
        }
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

    private void performCrop(Uri uri, Uri galleryUri) {
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
            List<ResolveInfo> resInfoList= getActivity().getPackageManager().queryIntentActivities(cropIntent, PackageManager.MATCH_DEFAULT_ONLY);
            for (ResolveInfo resolveInfo : resInfoList) {
                String packageName = resolveInfo.activityInfo.packageName;
                getContext().grantUriPermission(packageName, uri,
                        Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            }
//            getContext().grantUriPermission("thhsu.chloe.jeeva", uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
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



    @NeedsPermission({Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void requestPermissions() {
        mBottomSheetDialog.show();
    }

    @OnPermissionDenied({Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void onPermissionDenied() {
        Toast.makeText(getContext(), "You've denied access request", Toast.LENGTH_SHORT).show();
    }

    @OnNeverAskAgain({Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void onNeverAskAgain() {
        Toast.makeText(getContext(), "You need to allow ModelHub access to use camera. Please go to SETTING to allow the access. ", Toast.LENGTH_SHORT).show();
    }

    @OnShowRationale({Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void showRationalForCamera(final PermissionRequest request) {
        new AlertDialog.Builder(getContext())
                .setMessage("You need to allow access to Camera and Gallery. ")
                .setPositiveButton(R.string.button_allow, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        request.proceed();
                    }
                })
                .setNegativeButton(R.string.button_deny, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        request.cancel();
                    }
                })
                .show();
    }


}
