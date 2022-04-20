package com.technoecorp.gorilladealer.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.technoecorp.gorilladealer.R;
import com.technoecorp.gorilladealer.activity.OtpActivity;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ContactInfoFragment extends Fragment {

    @BindView(R.id.registerButton)
    MaterialButton registerButton;
    String gender = "";


    public ContactInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contact_info, container, false);
        ButterKnife.bind(this, view);
        initView(view);
        return view;
    }

    void initView(View view) {
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent otpIntent = new Intent(getActivity(), OtpActivity.class);
                startActivity(otpIntent);
            }
        });
    }


}