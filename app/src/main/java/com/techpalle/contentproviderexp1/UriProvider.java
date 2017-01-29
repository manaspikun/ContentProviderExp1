package com.techpalle.contentproviderexp1;

import android.net.Uri;

/**
 * Created by manasranjan on 1/29/2017.
 */


// This file will be exposed to outside world
//URI - Uniform Resource Identifier
// declare column name and prepare URIs for your table
public class UriProvider {

    public static final String _ID = "_id"; //integer
    public static final String Name = "sname"; // string
    public static final String SUB = "ssub"; // string

    // prepare URI for student information table

    public static final Uri STUDENT_URI =
            Uri.parse("content://com.techpalle.android/studenttable");
}
