package com.egecius.demo_gson;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rits.cloning.Cloner;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

@SuppressLint("UseSparseArrays")
public class MainActivity extends AppCompatActivity {

    Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        makeDeepCopyWithGson();
        makeDeepCopyWithCloner();
    }

    private void makeDeepCopyWithGson() {
        Log.v("Eg:MainActivity:25", "doReflection");

        String json = gson.toJson(createHashMap());
        Type type = new TypeToken<Map<Integer, String>>() {}.getType();
        Map<Integer, String> mapDeepCopy = gson.fromJson(json, type);

        showToast(mapDeepCopy);
    }

    private HashMap<Integer, String> createHashMap() {
        HashMap<Integer, String> hashMap = new HashMap<>();
        hashMap.put(0, "nulis");
        return hashMap;
    }

    private void showToast(Map<Integer, String> mapDeepCopy) {
        Toast.makeText(this, mapDeepCopy.toString(), Toast.LENGTH_SHORT).show();
    }

    private void makeDeepCopyWithCloner() {
        Log.v("Eg:MainActivity:53", "makeDeepCopyWithCloner");

        Map<Integer, String> mapDeepCopy = new Cloner().deepClone(createHashMap());

        showToast(mapDeepCopy);
    }
}
