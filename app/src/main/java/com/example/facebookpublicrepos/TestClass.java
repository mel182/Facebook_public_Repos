package com.example.facebookpublicrepos;

import com.example.facebookpublicrepos.interfaces.RepositoryListApiInterface;
import com.example.facebookpublicrepos.models.Repository;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Path;

public class TestClass {

    private RepositoryListApiInterface repositoryListApiInterface;

    @SerializedName("id")
    private  int ID;

    @SerializedName("image_path")
    private String ImagePath;

    public static String TEST = "";

    private int test1, test2 = 0;

//    private void test() {
//        repositoryListApiInterface = RepositoryListApiInterface.
//    }


}
