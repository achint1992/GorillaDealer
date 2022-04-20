package com.technoecorp.gorilladealer.activity;

import static com.technoecorp.gorilladealer.utils.APICallConstants.certificate;
import static com.technoecorp.gorilladealer.utils.APICallConstants.dealerCard;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferListener;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferObserver;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferState;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.github.florent37.viewanimator.AnimationListener;
import com.github.florent37.viewanimator.ViewAnimator;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.dynamiclinks.DynamicLink;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.google.firebase.dynamiclinks.ShortDynamicLink;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.technoecorp.gorilladealer.R;
import com.technoecorp.gorilladealer.bean.DealerDashboardBean.DashboardResponse;
import com.technoecorp.gorilladealer.bean.DealerDashboardBean.Data;
import com.technoecorp.gorilladealer.bean.OTPBean.Dealer;
import com.technoecorp.gorilladealer.dialog.CustomDialogClass;
import com.technoecorp.gorilladealer.interfaces.OkHttpCustomResponse;
import com.technoecorp.gorilladealer.utils.APICallConstants;
import com.technoecorp.gorilladealer.utils.HttpCall;
import com.technoecorp.gorilladealer.utils.NetworkUtil;
import com.technoecorp.gorilladealer.utils.PermissionUtils;
import com.technoecorp.gorilladealer.utils.ResourceUtils;
import com.technoecorp.gorilladealer.utils.SharedPrefrenceUtils;
import com.technoecorp.gorilladealer.utils.ToastUtil;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;

public class DashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.navigationView)
    NavigationView navView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.userName)
    TextView userName;
    @BindView(R.id.userMobile)
    TextView userMobile;
    @BindView(R.id.userRefCode)
    TextView userRefCode;
    @BindView(R.id.totalBusinessValue)
    TextView totalBusinessValue;
    @BindView(R.id.totalIncomeValue)
    TextView totalIncomeValue;
    @BindView(R.id.totalOneDayIncomeValue)
    TextView totalOneDayIncomeValue;
    @BindView(R.id.totalOneWeekIncomeValue)
    TextView totalOneWeekIncomeValue;
    @BindView(R.id.totalOneMonthIncomeValue)
    TextView totalOneMonthIncomeValue;
    @BindView(R.id.activeDealerValue)
    TextView activeDealerValue;
    @BindView(R.id.deactiveDealerValue)
    TextView deactiveDealerValue;
    @BindView(R.id.withdrawlIncomeValue)
    TextView withdrawlIncomeValue;
    @BindView(R.id.totalDealerValue)
    TextView totalDealerValue;
    @BindView(R.id.totalUniDealerValue)
    TextView totalUniDealerValue;

    @BindView(R.id.shareIcon)
    ImageView shareIcon;
    @BindView(R.id.cardSix)
    MaterialCardView cardSix;
    @BindView(R.id.cardSeven)
    MaterialCardView cardSeven;
    @BindView(R.id.cardNine)
    MaterialCardView cardNine;

    @BindView(R.id.cardThree)
    MaterialCardView cardThree;
    @BindView(R.id.cardFour)
    MaterialCardView cardFour;
    @BindView(R.id.cardFive)
    MaterialCardView cardFive;

    @BindView(R.id.profileImage)
    ImageView profileImage;
    @BindView(R.id.profileImageContainer)
    RelativeLayout profileImageContainer;
    @BindView(R.id.textAnimation)
    LinearLayout textAnimation;
    @BindView(R.id.userText)
    TextView userText;
    @BindView(R.id.facebookButton)
    ImageView facebookButton;
    @BindView(R.id.instaButton)
    ImageView instaButton;
    @BindView(R.id.youtubeButton)
    ImageView youtubeButton;
    @BindView(R.id.telegramButton)
    ImageView telegramButton;
    @BindView(R.id.withdrawlButton)
    MaterialButton withdrawlButton;

    int index = 0;
    final int IMAGE_CROP_REQUEST = 551;
    private AmazonS3Client s3;
    private BasicAWSCredentials credentials;
    private TransferUtility transferUtility;
    TextView userNameMenu;
    Dealer dealer;
    static final int permissionDiskWriteCode = 102;
    ArrayList<String> recentList = new ArrayList<>();
    AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.colorPrimaryDark));
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navView.setNavigationItemSelectedListener(this);
        navView.setItemIconTintList(null);
        View navHeaderView = navView.getHeaderView(0);
        userNameMenu = navHeaderView.findViewById(R.id.userNameMenu);
        try {
            String secret = ResourceUtils.getString(DashboardActivity.this, R.string.aws_secret);
            String key = ResourceUtils.getString(DashboardActivity.this, R.string.aws_key);
            credentials = new BasicAWSCredentials(key, secret);
            s3 = new AmazonS3Client(credentials);
            s3.setRegion(Region.getRegion(Regions.AP_SOUTH_1));
            transferUtility = new TransferUtility(s3, getApplicationContext());
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
        }

        initView();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.menu_home:
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            case R.id.menu_Kyc:
                Intent kyc = new Intent(DashboardActivity.this, KycActivity.class);
                startActivity(kyc);
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            case R.id.menu_idCard:
                if (dealer.getPayment().isEmpty()) {
                    intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(dealerCard + dealer.getDealerId()));
                    startActivity(intent);
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            case R.id.menu_certificate:
                if (dealer.getPayment().isEmpty()) {
                    intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(certificate + dealer.getDealerId()));
                    startActivity(intent);
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;

            case R.id.menu_edit:
                drawerLayout.closeDrawer(GravityCompat.START);
                Intent edit = new Intent(DashboardActivity.this, EditProfileActivity.class);
                startActivity(edit);
                return true;
            case R.id.menu_video:
                intent = new Intent(DashboardActivity.this, GalleryActivity.class);
                intent.putExtra("type", "video");
                startActivity(intent);
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            case R.id.menu_photo:
                intent = new Intent(DashboardActivity.this, GalleryActivity.class);
                intent.putExtra("type", "photo");
                startActivity(intent);
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            case R.id.menu_pdf:
                intent = new Intent(DashboardActivity.this, GalleryActivity.class);
                intent.putExtra("type", "pdf");
                startActivity(intent);
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;

            case R.id.menu_policy:
                Intent policy = new Intent(DashboardActivity.this, PolicyActivity.class);
                startActivity(policy);
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            case R.id.menu_terms:
                Intent terms = new Intent(DashboardActivity.this, TermOfUseActivity.class);
                startActivity(terms);
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            case R.id.menu_payment:
                Intent payment = new Intent(DashboardActivity.this, ProductListActivity.class);
                startActivity(payment);
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            case R.id.menu_logout:
                SharedPrefrenceUtils.clearPreferences(DashboardActivity.this);
                ToastUtil.showToast(DashboardActivity.this, "User Logout Successfully");
                Intent user = new Intent(DashboardActivity.this, DealerLoginActivity.class);
                startActivity(user);
                finish();

                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
        }
        return false;
    }

    void initView() {
        totalBusinessValue.setText("0");
        totalIncomeValue.setText("0");
        totalOneDayIncomeValue.setText("0");
        totalOneWeekIncomeValue.setText("0");
        totalOneMonthIncomeValue.setText("0");
        activeDealerValue.setText("0");
        withdrawlIncomeValue.setText("0");
        Gson gson = new Gson();
        String dealerBean = SharedPrefrenceUtils.loadSavedPreferences(this, SharedPrefrenceUtils.dealerBean, "");
        dealer = gson.fromJson(dealerBean, Dealer.class);
        if (dealer != null) {
            userName.setText(dealer.getName());
            userNameMenu.setText(dealer.getName());
            userMobile.setText(dealer.getMobileNo());
            userRefCode.setText(dealer.getReferCode());
            ImageLoader imageLoader = ImageLoader.getInstance();
            imageLoader.init(ImageLoaderConfiguration.createDefault(getApplicationContext()));
            if (dealer.getProfilePic() != null) {
                if (!dealer.getProfilePic().equalsIgnoreCase("")) {
                    imageLoader.displayImage(dealer.getProfilePic(), profileImage);
                }
            }
            shareIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    shareIntent(dealer.getReferCode());
                }
            });
        } else {
            finish();
        }

        userText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                animateText();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        profileImageContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startImagePicker();
            }
        });

        facebookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/Technoecorp-Service-Pvt-Ltd-112394931093289/"));
                startActivity(intent);
            }
        });


        instaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/invites/contact/?i=11epqbcn446nk&utm_content=m92tp8r"));
                startActivity(intent);

            }
        });


        youtubeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/channel/UC9PbG4n7hkRpekdNm0tKfkQ"));
                startActivity(intent);

            }
        });


        telegramButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/technoecorp"));
                startActivity(intent);

            }
        });

        cardThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dashboardData = SharedPrefrenceUtils.loadSavedPreferences(DashboardActivity.this, SharedPrefrenceUtils.lastDashBoardObject, "");
                if (!dashboardData.equalsIgnoreCase("")) {
                    Data dashboard = gson.fromJson(dashboardData, Data.class);
                    if (dashboard != null) {
                        if (dashboard.getTodayList().size() > 0) {
                            Intent intent = new Intent(DashboardActivity.this, IncomeListActivity.class);
                            intent.putParcelableArrayListExtra("dealers", dashboard.getTodayList());
                            intent.putExtra("title", "One Day Income");
                            intent.putExtra("isExport", false);
                            startActivity(intent);
                        } else {
                            ToastUtil.showToast(DashboardActivity.this, "No Income Found");
                        }
                    }
                }
            }
        });

        cardFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dashboardData = SharedPrefrenceUtils.loadSavedPreferences(DashboardActivity.this, SharedPrefrenceUtils.lastDashBoardObject, "");
                if (!dashboardData.equalsIgnoreCase("")) {
                    Data dashboard = gson.fromJson(dashboardData, Data.class);
                    if (dashboard != null) {
                        if (dashboard.getLastWeekList().size() > 0) {
                            Intent intent = new Intent(DashboardActivity.this, IncomeListActivity.class);
                            intent.putParcelableArrayListExtra("dealers", dashboard.getLastWeekList());
                            intent.putExtra("title", "One Week Income");
                            intent.putExtra("isExport", false);
                            startActivity(intent);
                        } else {
                            ToastUtil.showToast(DashboardActivity.this, "No Income Found");
                        }
                    }
                }
            }
        });

        cardFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dashboardData = SharedPrefrenceUtils.loadSavedPreferences(DashboardActivity.this, SharedPrefrenceUtils.lastDashBoardObject, "");
                if (!dashboardData.equalsIgnoreCase("")) {
                    Data dashboard = gson.fromJson(dashboardData, Data.class);
                    if (dashboard != null) {
                        if (dashboard.getLastMonthList().size() > 0) {
                            Intent intent = new Intent(DashboardActivity.this, IncomeListActivity.class);
                            intent.putParcelableArrayListExtra("dealers", dashboard.getLastMonthList());
                            intent.putExtra("title", "One Month Income");
                            intent.putExtra("isExport", false);
                            startActivity(intent);
                        } else {
                            ToastUtil.showToast(DashboardActivity.this, "No Income Found");
                        }
                    }
                }
            }
        });

        cardSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, DealerListActivity.class);
                intent.putExtra("type", "active");
                startActivity(intent);
            }
        });
        cardSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, DealerListActivity.class);
                intent.putExtra("type", "deactive");
                startActivity(intent);
            }
        });

        cardNine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, DealerListActivity.class);
                intent.putExtra("type", "all");
                startActivity(intent);
            }
        });
        getUserData(dealer);
        String dashboardData = SharedPrefrenceUtils.loadSavedPreferences(this, SharedPrefrenceUtils.lastDashBoardObject, "");
        if (!dashboardData.equalsIgnoreCase("")) {
            Data dashboard = gson.fromJson(dashboardData, Data.class);
            setDataForDashBoard(dashboard);
        }

        if (dealer.getPayment().size() == 0) {
            showMaterialDialog();
        }

    }

    void showMaterialDialog() {
        dialog = new MaterialAlertDialogBuilder(DashboardActivity.this)
                .setTitle("Subscription")
                .setMessage("Your did not made any payment on Technoecorp. You can purchase it now.")
                .setPositiveButton("Purchase", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent edit = new Intent(DashboardActivity.this, ProductListActivity.class);
                        startActivity(edit);
                        dialogInterface.dismiss();
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).setCancelable(false).show();

    }

    void startImagePicker() {
        if (PermissionUtils.checkPhoneAccess(DashboardActivity.this)) {
            ImagePicker.with(DashboardActivity.this)
                    .galleryOnly()
                    .crop()                    //Crop image(Optional), Check Customization for more option
                    .compress(1024)            //Final image size will be less than 1 MB(Optional)
                    .maxResultSize(1080, 1080)    //Final image resolution will be less than 1080 x 1080(Optional)
                    .cropSquare().start(IMAGE_CROP_REQUEST);
        } else {
            requestPermission();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case permissionDiskWriteCode:
                startImagePicker();
                break;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    void requestPermission() {
        if (!PermissionUtils.checkPhoneAccess(DashboardActivity.this)) {
            PermissionUtils.requestStorage(DashboardActivity.this, permissionDiskWriteCode);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMAGE_CROP_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                Uri uri = data.getData();
                if (uri != null) {
                    profileImage.setImageURI(uri);
                    Log.e("RUI is", uri.getPath());
                    try {
                        File file = new File(new URI(uri.toString()));
                        uploadURL(file.getAbsolutePath(), "compress_" + dealer.getMobileNo() + "_" + dealer.getDealerId() + ".jpg");
                    } catch (URISyntaxException e) {
                        Log.e("Error", e.getMessage());
                    }
                }
            }
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            ToastUtil.showToast(DashboardActivity.this, ImagePicker.getError(data));
        } else {
            ToastUtil.showToast(DashboardActivity.this, "Task Cancelled");
        }
    }

    void uploadURL(String tempUploadPath, String fileName) {
        File file = new File(tempUploadPath);
        TransferObserver observer = transferUtility.upload(
                "suraksha-images",
                fileName,
                file
        );
        Log.e("fileName is", tempUploadPath);

        observer.setTransferListener(new TransferListener() {
            @Override
            public void onStateChanged(int id, TransferState state) {
                Log.e("state is", "" + observer.getState());
                if (state.COMPLETED.equals(observer.getState())) {
                    Thread thread = new Thread(new Runnable() {
                        public void run() {
                            Log.e("completed", "got completed");
                            s3.setObjectAcl("suraksha-images", fileName, CannedAccessControlList.PublicRead);
                            String imagePath = "http://suraksha-images.s3.amazonaws.com/" + fileName;
                            Log.e("image path", imagePath + "");
                            uploadFileToServer(fileName);
                        }
                    });
                    thread.start();
                }
            }

            @Override
            public void onProgressChanged(int id, long bytesCurrent, long bytesTotal) {
                Log.e("getting progress ", "progress");
            }

            @Override
            public void onError(int id, Exception ex) {
                Log.e("Error",ex.getMessage());
                Log.e("error is ", ex.toString());
            }
        });
    }


    void uploadFileToServer(String fileName) {

        if (NetworkUtil.isNetworkConnected(this)) {
            HttpCall call = new HttpCall();
            call.setOkHttpCustomResponse(new OkHttpCustomResponse() {
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String responseData = response.body().string();
                    Log.e("photo response is", responseData);
                    dealer.setProfilePic("http://suraksha-images.s3.amazonaws.com/" + fileName);
                    SharedPrefrenceUtils.saveObjectPreferences(DashboardActivity.this, SharedPrefrenceUtils.dealerBean, dealer);

                }

                @Override
                public void onFailure(Call call, IOException e) {

                }
            });
            Map<String, Object> postData = new HashMap<>();
            postData.put("dealerId", dealer.getDealerId());
            postData.put("profilePic", "http://suraksha-images.s3.amazonaws.com/" + fileName);
            call.callNewHTTP(postData, APICallConstants.editDealerApiRequest, DashboardActivity.this);

        }

    }

    void shareIntent(String referCodeText) {
        // firebase gorilladealer.page.link deep link

        DynamicLink dynamicLink = FirebaseDynamicLinks.getInstance().createDynamicLink()
                .setLink(Uri.parse("https://www.technoesoft.com/?referId=" + referCodeText))
                .setDomainUriPrefix("https://gorilladealer.page.link")
                // Open links with this app on Android
                .setAndroidParameters(new DynamicLink.AndroidParameters.Builder().build())
                // Open links with com.example.ios on iOS
                .buildDynamicLink();
        Uri dynamicLinkUri = dynamicLink.getUri();
        Log.e("Long link is", "link========" + dynamicLinkUri.toString());

        Task<ShortDynamicLink> shortLinkTask = FirebaseDynamicLinks.getInstance().createDynamicLink()
                .setLongLink(dynamicLinkUri)
                .buildShortDynamicLink()
                .addOnCompleteListener(this, new OnCompleteListener<ShortDynamicLink>() {
                    @Override
                    public void onComplete(@NonNull Task<ShortDynamicLink> task) {
                        if (task.isSuccessful()) {
                            // Short link created
                            Uri shortLink = task.getResult().getShortLink();
                            Uri flowchartLink = task.getResult().getPreviewLink();
                            Log.e("SHort link is", "Short ===" + shortLink.toString());


                            Intent intent = new Intent(android.content.Intent.ACTION_SEND);
                            /*This will be the actual content you wish you share.*/
                            /*The type of the content is text, obviously.*/
                            intent.setType("text/plain");
                            /*Applying information Subject and Body.*/
                            intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Share Refer Code");
                            intent.putExtra(android.content.Intent.EXTRA_TEXT, shortLink.toString());
                            /*Fire!*/
                            startActivity(Intent.createChooser(intent, "Share Your Refer Code"));

                        } else {
                            // Error
                            // ...
                        }
                    }
                });


    }

    void getUserData(Dealer dealer) {
        try {
            if (NetworkUtil.isNetworkConnected(this)) {
                final CustomDialogClass customDialogClass = new CustomDialogClass(this);
                customDialogClass.show();
                HttpCall call = new HttpCall();
                call.setOkHttpCustomResponse(new OkHttpCustomResponse() {
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String responseData = response.body().string();
                        Log.e("already response is", responseData);
                        Gson gson = new Gson();

                        final DashboardResponse registerBean = gson.fromJson(responseData, DashboardResponse.class);
                        if (registerBean.getStatus()) {
                            SharedPrefrenceUtils.saveObjectPreferences(DashboardActivity.this, SharedPrefrenceUtils.lastDashBoardObject, registerBean.getData());
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    setDataForDashBoard(registerBean.getData());
                                    customDialogClass.dismiss();
                                }
                            });
                        } else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    customDialogClass.dismiss();
                                    ToastUtil.showToast(DashboardActivity.this, registerBean.getMessage() + "");
                                }
                            });
                        }
                    }

                    @Override
                    public void onFailure(Call call, IOException e) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ToastUtil.showToast(DashboardActivity.this, "Invalid Refer Code");
                                customDialogClass.dismiss();
                            }
                        });
                    }
                });

                Map<String, Object> postData = new HashMap<>();
                postData.put("dealerId", dealer.getDealerId());
                postData.put("referCode", dealer.getReferCode());

                call.callNewHTTP(postData, APICallConstants.dealerDashboard, this);
            } else {
                ToastUtil.showToast(this, ResourceUtils.getString(this, R.string.internet_connection));
            }
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
        }

    }

    void setDataForDashBoard(Data data) {
        int totalDealer = (data.getActiveDealer() + data.getDeactiveDealer());
        deactiveDealerValue.setText("" + data.getDeactiveDealer());
        totalDealerValue.setText("" + totalDealer);
        activeDealerValue.setText("" + data.getActiveDealer());
        totalOneWeekIncomeValue.setText("" + data.getLastWeekBusiness());
        totalBusinessValue.setText("" + (totalDealer * 7500));
        totalOneDayIncomeValue.setText("" + data.getTodayBusiness());
        totalOneMonthIncomeValue.setText("" + data.getLastMonthBusiness());
        totalIncomeValue.setText("" + data.getTotalBusiness());
        totalUniDealerValue.setText("" + data.getAllDealerCount());
        recentList.addAll(data.getRecentDealers());
        index = 0;
        if (data.getRecentDealers().size() > 0) {
            textAnimation.setVisibility(View.VISIBLE);
            userText.setText("Welcome! " + recentList.get(index));
        }
        if (data.getRecentDealers().size() > 0) {
            textAnimation.setVisibility(View.VISIBLE);
            userText.setText("Welcome! " + recentList.get(index));
        }
        if (data.getWithdrawlBean() != null) {
            withdrawlIncomeValue.setText(data.getWithdrawlBean().getWithdrawalAmount() + "");
            if (data.getWithdrawlBean().getCount() > 1) {
                withdrawlButton.setVisibility(View.VISIBLE);
                withdrawlButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        requestWithDrawal(dealer);
                    }
                });
            } else {
                withdrawlButton.setVisibility(View.GONE);
            }

        } else {
            withdrawlIncomeValue.setText("0");
            withdrawlButton.setVisibility(View.GONE);
        }

    }

    void animateText() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ViewAnimator
                        .animate(textAnimation)
                        .tada().onStop(new AnimationListener.Stop() {
                    @Override
                    public void onStop() {
                        index++;
                        if (index < recentList.size()) {
                            userText.setText("Welcome! " + recentList.get(index));
                        } else {
                            index = 0;
                            userText.setText("Welcome! " + recentList.get(index));
                        }
                    }
                }).start();
            }
        }, 4000);

    }

    void requestWithDrawal(Dealer dealer) {
        try {
            if (NetworkUtil.isNetworkConnected(this)) {
                final CustomDialogClass customDialogClass = new CustomDialogClass(this);
                customDialogClass.show();
                HttpCall call = new HttpCall();
                call.setOkHttpCustomResponse(new OkHttpCustomResponse() {
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String responseData = response.body().string();
                        Log.e("already response is", responseData);
                        Gson gson = new Gson();

                        final DashboardResponse registerBean = gson.fromJson(responseData, DashboardResponse.class);
                        if (registerBean.getStatus()) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    customDialogClass.dismiss();
                                    ToastUtil.showToast(DashboardActivity.this, "Request Created Successfully..");
                                }
                            });
                        } else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    ToastUtil.showToast(DashboardActivity.this, registerBean.getMessage());
                                    customDialogClass.dismiss();
                                }
                            });
                        }
                    }

                    @Override
                    public void onFailure(Call call, IOException e) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ToastUtil.showToast(DashboardActivity.this, "Something Went Wrong...");
                                customDialogClass.dismiss();
                            }
                        });
                    }
                });

                Map<String, Object> postData = new HashMap<>();
                postData.put("dealerId", dealer.getDealerId());
                call.callNewHTTP(postData, APICallConstants.withdrawalMoney, this);
            } else {
                ToastUtil.showToast(this, ResourceUtils.getString(this, R.string.internet_connection));
            }
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
        }

    }

}