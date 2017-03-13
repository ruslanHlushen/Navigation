package com.ruslan_hlushen.androidappnavigation;

/**
 * Created by Ruslan on 13.03.2017.
 */

public class DataModel {

    private String fragmentNameForBackStack;
    private Object data;


    public DataModel(String fragmentNameForBackStack, Object data) {

        this.fragmentNameForBackStack = fragmentNameForBackStack;
        this.data = data;
    }


    public String getFragmentNameForBackStack() { return fragmentNameForBackStack; }


    public Object getData() { return data; }
}