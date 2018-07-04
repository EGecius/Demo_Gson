package com.egecius.demo_gson;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        doReflection();
    }

    private void doReflection() {
        Log.v("Eg:MainActivity:25", "doReflection");

        Gson gson = new Gson();
        HashMap<Integer, String> hashMap = new HashMap<>();

        hashMap.put(0, "zero");

        String json = gson.toJson(hashMap);
        Type type = new TypeToken<Map<Integer, String>>(){}.getType();
        Map<String, String> mapDeepCopy = gson.fromJson(json, type);


        Toast.makeText(this, mapDeepCopy.toString(), Toast.LENGTH_SHORT).show();

    }
}
