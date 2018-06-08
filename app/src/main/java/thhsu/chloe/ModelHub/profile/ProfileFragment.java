package thhsu.chloe.ModelHub.profile;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;
import thhsu.chloe.ModelHub.R;
import thhsu.chloe.ModelHub.profileInfo.ProfileInfoFragment;
import thhsu.chloe.ModelHub.profileInfo.ProfileInfoPresenter;
import thhsu.chloe.ModelHub.profileWorkbook.ProfileWorkbookFragment;
import thhsu.chloe.ModelHub.profileWorkbook.ProfileWorkbookPresenter;
import thhsu.chloe.ModelHub.utils.CircleTransform;
import thhsu.chloe.ModelHub.adapters.ViewPagerAdapter;
import thhsu.chloe.ModelHub.api.model.User;

/**
 * Created by Chloe on 4/30/2018.
 */

@RuntimePermissions
public class ProfileFragment extends Fragment implements ProfileContract.View, View.OnClickListener {
    private ImageView mImageViewUserPhoto;
    private ProfileContract.Presenter mPresenter;
    private BottomSheetDialog mBottomSheetDialog;
    private TextView mTextViewUserName, mTextViewUserHeight, mTextViewUserLocation, mTextViewUserWeight, mTextViewUserNationality;

    public static ProfileFragment newInstance() {return new ProfileFragment();}

    @Override
    public void setPresenter(ProfileContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        Button cameraBtn = (Button) root.findViewById(R.id.btn_profile_camera);
        mBottomSheetDialog = new BottomSheetDialog(getActivity());

        mTextViewUserName = (TextView) root.findViewById(R.id.textview_profile_name);
        mImageViewUserPhoto = (ImageView) root.findViewById(R.id.imageview_profile_photo);
        mTextViewUserLocation = (TextView) root.findViewById(R.id.textview_profile_location);
        mTextViewUserWeight = (TextView) root.findViewById(R.id.textview_profile_weight_text);
        mTextViewUserHeight = (TextView) root.findViewById(R.id.textview_profile_height_text);
        mTextViewUserNationality = (TextView) root.findViewById(R.id.textview_profile_nationality_text);
        ImageView imageViewUserCover = (ImageView) root.findViewById(R.id.imageview_profile_cover_image);

        TabLayout tabLayout = (TabLayout) root.findViewById(R.id.profile_fragment_tablayout);
        ViewPager viewPager = (ViewPager) root.findViewById(R.id.viewpager_profile);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
        ProfileInfoFragment profileInfoFragment = new ProfileInfoFragment();
        ProfileWorkbookFragment profileWorkbookFragment = new ProfileWorkbookFragment();
        ProfileInfoPresenter profileInfoPresenter = new ProfileInfoPresenter(profileInfoFragment);
        ProfileWorkbookPresenter profileWorkbookPresenter = new ProfileWorkbookPresenter(profileWorkbookFragment);
        viewPagerAdapter.addFragment(profileInfoFragment);
        viewPagerAdapter.addFragment(profileWorkbookFragment);
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        View sheetView = getActivity().
                getLayoutInflater().inflate(R.layout.fragment_profile_bottomsheet, null);
        mBottomSheetDialog.setContentView(sheetView);
        LinearLayout camera = (LinearLayout) sheetView.findViewById(R.id.fragment_profile_camera);
        LinearLayout gallery = (LinearLayout) sheetView.findViewById(R.id.fragment_profile_gallery);

        camera.setOnClickListener(this);
        gallery.setOnClickListener(this);
        cameraBtn.setOnClickListener(this);

        Picasso.get().load("http://www.cianellistudios.com/blog/wp-content/uploads/2010/12/" +
                "abstract-art-painting-canvas-art-mother-earth.jpg").fit().into(imageViewUserCover);

        mPresenter.showUserInfo();

        return root;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        ProfileFragmentPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.showUserInfo();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_profile_camera:
                ProfileFragmentPermissionsDispatcher.
                        requestPermissionsWithPermissionCheck(this);
                break;

            case R.id.fragment_profile_camera:
                mBottomSheetDialog.hide();
                mPresenter.takePhoto();
                break;

            case R.id.fragment_profile_gallery:
                mBottomSheetDialog.hide();
                mPresenter.pickImage();
                break;
        }
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
        Toast.makeText(getContext(), "You need to allow ModelHub access to use camera." +
                " Please go to SETTING to allow the access. ", Toast.LENGTH_SHORT).show();
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

    @Override
    public void showUserPhoto(Uri uri) {
        Picasso.get().load(uri).fit().centerCrop().transform(new CircleTransform()).into(mImageViewUserPhoto);
    }

    @Override
    public void showUserInfoUi(User user) {
        String userName = user.getName();
        String userHeight = user.getHeight();
        String userWeight = user.getWeight();
        String userLocationCity = user.getCity();
        String userNationality = user.getNationality();
        String userLocationCountry = user.getCountry();
        String userLocation = (userLocationCity != null ? userLocationCity + " , " : "") +
                (userLocationCountry != null ? userLocationCountry : "");

        mTextViewUserName.setText(userName);
        mTextViewUserLocation.setText(userLocation);

        if (!mTextViewUserHeight.equals("")) {
            mTextViewUserHeight.setText(userHeight);
        }

        if (!mTextViewUserWeight.equals("")) {
            mTextViewUserWeight.setText(userWeight);

        }
        mTextViewUserNationality.setText(userNationality);

        if (!(mTextViewUserLocation == null || mTextViewUserLocation.equals(""))) {
            mTextViewUserLocation.setVisibility(View.VISIBLE);
        }
    }
}

// Upload file (testing)
//                getContext().getContentResolver().openInputStream(mImageUri)
//                File f = new File(mImageUri.getPath());
//                ApiJobManager.getInstance().upLoadImage(f, new UploadImageCallBack() {
//                    @Override
//                    public void onComplete(String url) {
//                        Log.d("Chloe", "image-url: "+url);
//                        userImageUrl = "https://moelhub.tw" + url;
//                        mUser.setProfilePic(userImageUrl);
//                        UpdateUserRequest r = new UpdateUserRequest(mUserToken, mUser);
//                        ApiJobManager.getInstance().getPostUserInfoResult(r, new PostUserInfoCallBack() {
//                            @Override
//                            public void onComplete() {
////                                Toast.makeText(this, "Photo updated", Toast.LENGTH_SHORT).show();
//                                Picasso.get().load(userImageUrl).fit().centerCrop().transform(new CircleTransform()).into(mImageViewUserPhoto);
//
//                            }
//
//                            @Override
//                            public void onError(String errorMessage) {
//                                Log.d("Chloe","error: " + errorMessage);
//                            }
//                        });
//                    }
//
//                    @Override
//                    public void onError(String errorMessage) {
//
//                    }
//                });
