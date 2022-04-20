package com.technoecorp.gorilladealer.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.technoecorp.gorilladealer.R;
import com.technoecorp.gorilladealer.activity.RegistrationActivity;

import butterknife.BindView;
import butterknife.ButterKnife;


public class PersonalInfoFragment extends Fragment {

    @BindView(R.id.registerButton)
    MaterialButton registerButton;


    public PersonalInfoFragment() {
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
        View view = inflater.inflate(R.layout.fragment_personal_info, container, false);
        ButterKnife.bind(this, view);
        initView(view);
        return view;
    }

    void initView(View view) {
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getActivity() instanceof RegistrationActivity) {
                }
            }
        });
    }


}