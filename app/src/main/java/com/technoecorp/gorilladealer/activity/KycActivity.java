package com.technoecorp.gorilladealer.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.technoecorp.gorilladealer.R;
import com.technoecorp.gorilladealer.bean.KYCResponse.KycDetailsBean;
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
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;

public class KycActivity extends AppCompatActivity {
    //UI Elements
    @BindView(R.id.accountName)
    TextInputEditText accountName;
    @BindView(R.id.accountNumber)
    TextInputEditText accountNumber;
    @BindView(R.id.confirmAccountNumber)
    TextInputEditText confirmAccountNumber;
    @BindView(R.id.bankName)
    TextInputEditText bankName;
    @BindView(R.id.branchName)
    TextInputEditText branchName;
    @BindView(R.id.ifscCode)
    TextInputEditText ifscCode;
    @BindView(R.id.accountType)
    AutoCompleteTextView accountType;
    @BindView(R.id.uploadAddressButton)
    MaterialButton uploadAddressButton;
    @BindView(R.id.uploadIdButton)
    MaterialButton uploadIdButton;
    @BindView(R.id.idProofImage)
    ImageView idProofImage;
    @BindView(R.id.addProofImage)
    ImageView addProofImage;
    @BindView(R.id.registerButton)
    MaterialButton registerButton;
    AlertDialog dialog;

    //Variables
    private AmazonS3Client s3;
    private BasicAWSCredentials credentials;
    private TransferUtility transferUtility;
    final int PERMISSION_DISK_WRITE_CODE_ID_PROOF = 102;
    final int PERMISSION_DISK_WRITE_CODE_ADD_PROOF = 103;
    final int PHOTO_ID_CROP_REQUEST = 551;
    final int ADDRESS_ID_CROP_REQUEST = 552;
    Dealer dealer;
    String idProofPath = "";
    String addProofPath = "";
    String accountTypeText = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kyc);
        ButterKnife.bind(this);
        Gson gson = new Gson();
        String dealerBean = SharedPrefrenceUtils.loadSavedPreferences(this, SharedPrefrenceUtils.dealerBean, "");
        dealer = gson.fromJson(dealerBean, Dealer.class);
        if (dealer == null) {
            finish();
        }

        try {
            String secret = ResourceUtils.getString(KycActivity.this, R.string.aws_secret);
            String key = ResourceUtils.getString(KycActivity.this, R.string.aws_key);
            credentials = new BasicAWSCredentials(key, secret);
            s3 = new AmazonS3Client(credentials);
            s3.setRegion(Region.getRegion(Regions.AP_SOUTH_1));
            transferUtility = new TransferUtility(s3, getApplicationContext());
        } catch (Exception e) {
            Log.e("Error",e.getMessage());
        }
        initView();
        if (!isValidationProfile()) {
            showMaterialDialog();
        }
        if (dealer.getKycBean() != null) {
            updateKycOnUI();
        }
    }

    void updateKycOnUI() {
        accountName.setText(dealer.getKycBean().getAccountName());
        accountNumber.setText(dealer.getKycBean().getAccountNumber());
        confirmAccountNumber.setText(dealer.getKycBean().getAccountNumber());
        bankName.setText(dealer.getKycBean().getBankName());
        branchName.setText(dealer.getKycBean().getBranchName());
        ifscCode.setText(dealer.getKycBean().getIfscCode());
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(getApplicationContext()));
        if (dealer.getKycBean().getUserVerificationId() != null) {
            if (!dealer.getKycBean().getUserVerificationId().equalsIgnoreCase("")) {
                imageLoader.displayImage(dealer.getKycBean().getUserVerificationId(), idProofImage);
            }
        }
        if (dealer.getKycBean().getAddressProofId() != null) {
            if (!dealer.getKycBean().getAddressProofId().equalsIgnoreCase("")) {
                imageLoader.displayImage(dealer.getKycBean().getAddressProofId(), addProofImage);
            }
        }
    }

    @Override
    protected void onResume() {
        Log.e("on resume ", "resume called");
        Gson gson = new Gson();
        String dealerBean = SharedPrefrenceUtils.loadSavedPreferences(this, SharedPrefrenceUtils.dealerBean, "");
        dealer = gson.fromJson(dealerBean, Dealer.class);
        if (dealer == null) {
            finish();
        }
        if (!isValidationProfile()) {
            showMaterialDialog();
        }
        super.onResume();
    }

    boolean isValidationProfile() {
        if (dealer.getEmail().equalsIgnoreCase("")) {
            return false;
        } else if (dealer.getAlternetMobileNo1().equalsIgnoreCase("")) {
            return false;
        } else if (dealer.getCountry().getId() == -1) {
            return false;
        } else if (dealer.getState().getStateId() == -1) {
            return false;
        } else if (dealer.getCity().getCityId() == -1) {
            return false;
        } else if (dealer.getUserAddress().equalsIgnoreCase("")) {
            return false;
        } else if (dealer.getDistrictName().equalsIgnoreCase("")) {
            return false;
        } else if (dealer.getPincode().equalsIgnoreCase("")) {
            return false;
        } else if (dealer.getFatherName().equalsIgnoreCase("")) {
            return false;
        } else if (dealer.getDob().equalsIgnoreCase("")) {
            return false;
        }
        return true;
    }

    void showMaterialDialog() {
        dialog = new MaterialAlertDialogBuilder(KycActivity.this)
                .setTitle("Profile Update")
                .setMessage("Your profile details are not updated. You won't be able to receive any payment without updating it.")
                .setPositiveButton("Edit Profile Now", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent edit = new Intent(KycActivity.this, EditProfileActivity.class);
                        startActivity(edit);
                        dialogInterface.dismiss();
                    }
                }).setCancelable(false).show();

    }


    void initView() {
        uploadIdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startImagePicker(PHOTO_ID_CROP_REQUEST);
            }
        });

        uploadAddressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startImagePicker(ADDRESS_ID_CROP_REQUEST);
            }
        });
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String accountNameText = accountName.getText().toString();
                String accountNumberText = accountNumber.getText().toString();
                String confirmAccountNumberText = confirmAccountNumber.getText().toString();
                String bankNameText = bankName.getText().toString();
                String branchNameText = branchName.getText().toString();
                String ifscCodeText = ifscCode.getText().toString();
                if (isValidation(accountNameText, accountNumberText, confirmAccountNumberText, bankNameText, branchNameText, ifscCodeText)) {
                    uploadKycOnServer(accountNameText, accountNumberText, bankNameText, branchNameText, ifscCodeText);
                }
            }
        });

        String[] type = new String[]{"Saving", "Credit", "Current"};
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(
                        KycActivity.this,
                        R.layout.state_selected_item, R.id.stateName,
                        type);

        accountType.setAdapter(adapter);
        accountType.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //add call to method here
                if (s.toString().toLowerCase().equalsIgnoreCase("saving") || s.toString().toLowerCase().equalsIgnoreCase("credit")
                        || s.toString().toLowerCase().equalsIgnoreCase("current")) {
                    accountTypeText = s.toString().toLowerCase();
                } else {
                    accountTypeText = "";
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int
                    after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        updateKycButton();
    }

    void updateKycButton() {
        if (dealer.getKycBean() == null) {
            registerButton.setClickable(true);
        } else if (dealer.getKycBean().getIsKycVerify() == 0) {
            registerButton.setClickable(false);
            ToastUtil.showLongToast(KycActivity.this, "Your Kyc is under verification");
        } else if (dealer.getKycBean().getIsKycRejected() == 1) {
            registerButton.setClickable(true);
            ToastUtil.showLongToast(KycActivity.this, "Your Kyc is rejected. Please submit your details again");
        }
    }

    boolean isValidation(String accountName, String accountNumber, String ConfirmAccountNumber, String bankName, String branchName, String ifscCode) {
        if (accountName.equalsIgnoreCase("")) {
            ToastUtil.showToast(KycActivity.this, "Account Holder Name is required");
            return false;
        } else if (accountNumber.equalsIgnoreCase("")) {
            ToastUtil.showToast(KycActivity.this, "Account Number is required");
            return false;
        } else if (ConfirmAccountNumber.equalsIgnoreCase("")) {
            ToastUtil.showToast(KycActivity.this, "Confirm Account Number is required");
            return false;
        } else if (!ConfirmAccountNumber.equalsIgnoreCase(accountNumber)) {
            ToastUtil.showToast(KycActivity.this, "Account Number is not matching with Confirm Account Number");
            return false;
        } else if (bankName.equalsIgnoreCase("")) {
            ToastUtil.showToast(KycActivity.this, "Bank Name is required");
            return false;
        } else if (branchName.equalsIgnoreCase("")) {
            ToastUtil.showToast(KycActivity.this, "Branch Name is required");
            return false;
        } else if (ifscCode.equalsIgnoreCase("")) {
            ToastUtil.showToast(KycActivity.this, "IFSC Code is required");
            return false;
        } else if (accountTypeText.equalsIgnoreCase("")) {
            ToastUtil.showToast(KycActivity.this, "Account Type is required");
            return false;
        } else if (idProofPath.equalsIgnoreCase("")) {
            ToastUtil.showToast(KycActivity.this, "Please Upload An Id Proof");
            return false;
        } else if (addProofPath.equalsIgnoreCase("")) {
            ToastUtil.showToast(KycActivity.this, "Please Upload An Address Proof");
            return false;
        }
        return true;
    }

    void uploadKycOnServer(String accountName, String accountNumber, String bankName, String branchName, String ifscCode) {
        try {
            if (NetworkUtil.isNetworkConnected(KycActivity.this)) {
                final CustomDialogClass customDialogClass = new CustomDialogClass(KycActivity.this);
                customDialogClass.show();
                HttpCall call = new HttpCall();
                call.setOkHttpCustomResponse(new OkHttpCustomResponse() {
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String responseData = response.body().string();
                        Log.e("already response is", responseData);
                        Gson gson = new Gson();
                        final KycDetailsBean registerBean = gson.fromJson(responseData, KycDetailsBean.class);
                        if (registerBean.getStatus()) {
                            //     SharedPrefrenceUtils.saveObjectPreferences(KycActivity.this, SharedPrefrenceUtils.dealerBean, registerBean.getData().getData().getDealer());
                            updateKycButton();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
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
                                customDialogClass.dismiss();
                            }
                        });
                    }
                });

                Map<String, Object> postData = new HashMap<>();
                postData.put("accountName", accountName);
                postData.put("accountNumber", accountNumber);
                postData.put("bankName", bankName);
                postData.put("branchName", branchName);
                postData.put("ifscCode", ifscCode);
                postData.put("addressProofId", addProofPath);
                postData.put("userVerificationId", idProofPath);
                postData.put("dealerId", dealer.getDealerId());
                call.callNewHTTP(postData, APICallConstants.kycDealerApiRequest, KycActivity.this);
            } else {
                ToastUtil.showToast(KycActivity.this, ResourceUtils.getString(KycActivity.this, R.string.internet_connection));
            }
        } catch (Exception e) {
            Log.e("Error",e.getMessage());
        }
    }

    void startImagePicker(int requestCode) {
        if (PermissionUtils.checkPhoneAccess(KycActivity.this)) {
            ImagePicker.with(KycActivity.this)
                    .galleryOnly()
                    .crop()                    //Crop image(Optional), Check Customization for more option
                    .compress(1024)            //Final image size will be less than 1 MB(Optional)
                    .maxResultSize(1920, 1080)    //Final image resolution will be less than 1080 x 1080(Optional)
                    .start(requestCode);
        } else {
            if (requestCode == ADDRESS_ID_CROP_REQUEST) {
                requestPermission(PERMISSION_DISK_WRITE_CODE_ADD_PROOF);
            } else if (requestCode == PHOTO_ID_CROP_REQUEST) {
                requestPermission(PERMISSION_DISK_WRITE_CODE_ID_PROOF);
            }

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_DISK_WRITE_CODE_ID_PROOF:
                startImagePicker(PHOTO_ID_CROP_REQUEST);
                break;
            case PERMISSION_DISK_WRITE_CODE_ADD_PROOF:
                startImagePicker(ADDRESS_ID_CROP_REQUEST);
                break;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    void requestPermission(int requestCode) {
        if (!PermissionUtils.checkPhoneAccess(KycActivity.this)) {
            PermissionUtils.requestStorage(KycActivity.this, requestCode);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PHOTO_ID_CROP_REQUEST || requestCode == ADDRESS_ID_CROP_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                Uri uri = data.getData();
                if (uri != null) {
                    Log.e("RUI is", uri.getPath());
                    try {
                        File file = new File(new URI(uri.toString()));
                        if (requestCode == PHOTO_ID_CROP_REQUEST) {
                            idProofImage.setImageURI(uri);
                            uploadURL(file.getAbsolutePath(), "compress_" + dealer.getMobileNo() + "_" + dealer.getDealerId() + "_id_proof.jpg", PHOTO_ID_CROP_REQUEST);
                        } else if (requestCode == ADDRESS_ID_CROP_REQUEST) {
                            addProofImage.setImageURI(uri);
                            uploadURL(file.getAbsolutePath(), "compress_" + dealer.getMobileNo() + "_" + dealer.getDealerId() + "_add_proof.jpg", ADDRESS_ID_CROP_REQUEST);
                        }
                    } catch (URISyntaxException e) {
                        Log.e("Error",e.getMessage());
                    }
                }
            }
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            ToastUtil.showToast(KycActivity.this, ImagePicker.getError(data));
        } else {
            ToastUtil.showToast(KycActivity.this, "Task Cancelled");
        }
    }

    void uploadURL(String tempUploadPath, String fileName, int requestCode) {
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
                            String imagePath = "https://suraksha-images.s3.amazonaws.com/" + fileName;
                            Log.e("image path", imagePath + " ");
                            if (requestCode == PHOTO_ID_CROP_REQUEST) {
                                idProofPath = imagePath;
                            } else if (requestCode == ADDRESS_ID_CROP_REQUEST) {
                                addProofPath = imagePath;
                            }
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

}