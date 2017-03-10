package com.ruslan_hlushen.navigation.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ruslan_hlushen.androidappnavigation.navigation_manager.INavigationManager;
import com.ruslan_hlushen.navigation.NavigationApplication;
import com.ruslan_hlushen.navigation.R;

import javax.inject.Inject;

/**
 * Created by Ruslan on 27.02.2017.
 */

public class FragmentD extends Fragment {

    public static final String TAG = "FragmentD";

    TextView textView;

    @Inject
    INavigationManager iNavigationManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        NavigationApplication.getAppComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        initViews(view);
        setOnClickListeners();
    }


    private void initViews(View view) {

        textView = (TextView) view.findViewById(R.id.text);
        textView.setText(TAG);
    }


    private void setOnClickListeners() {

        textView.setOnClickListener(v -> iNavigationManager.startFragment(FragmentF.TAG, null, true));
    }
}