package com.technoecorp.gorilladealer.adapter;

/**
 * Created by amrut on 25/5/17.
 */


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.technoecorp.gorilladealer.fragment.ContactInfoFragment;
import com.technoecorp.gorilladealer.fragment.PersonalInfoFragment;


public class RegistrationPagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    PersonalInfoFragment personalInfoFragment;
    ContactInfoFragment contactInfoFragment;


    public RegistrationPagerAdapter(FragmentManager fm, int NumOfTab) {
        super(fm);
        this.mNumOfTabs = NumOfTab;

    }


    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                if (personalInfoFragment == null) {
                    personalInfoFragment = new PersonalInfoFragment();
                    return personalInfoFragment;
                } else {
                    return personalInfoFragment;
                }
            case 1:
                if (contactInfoFragment == null) {
                    contactInfoFragment = new ContactInfoFragment();
                    return contactInfoFragment;
                } else {
                    return contactInfoFragment;
                }

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
