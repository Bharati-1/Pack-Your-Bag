package com.example.pack_your_bag;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.example.pack_your_bag.Adapter.Adapter;
import com.example.pack_your_bag.Constants.myConstants;
import com.example.pack_your_bag.Data.AppData;
import com.example.pack_your_bag.Database.RoomDb;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<String > titles;
    List<Integer> images;
    Adapter adapter;
    RoomDb database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        recyclerView=findViewById(R.id.recycler_view);
        getSupportActionBar().hide();


        addAddTitles();
        addAllImages();
        persistAppData();
        database = RoomDb.getInstance(this);
        System.out.println("---------------->" +database.mainDao().getAllSelected(false).get(0).getItemname());

        adapter = new Adapter(this,titles,images,HomeActivity.this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    private static final int TIME_INTERVAL = 2000;
    private long eBackPressed;

    @Override
    public void onBackPressed() {
        if(eBackPressed+TIME_INTERVAL>System.currentTimeMillis()){
            super.onBackPressed();
            finishAffinity();
            return;
        }else{
            Toast.makeText(this, "Tap Back button in order to exit", Toast.LENGTH_SHORT).show();

        }
        eBackPressed=System.currentTimeMillis();


    }


    private void persistAppData(){
        SharedPreferences prefs  = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefs.edit();

        database = RoomDb.getInstance(this);
        AppData appdata = new AppData(database);
        int last = prefs.getInt(AppData.LAST_VERSION,0);
        if(!prefs.getBoolean(myConstants.FIRST_TIME_CAMEL_CASE,false)){
            appdata.persistAllData();
            editor.putBoolean(myConstants.FIRST_TIME_CAMEL_CASE,true);
            editor.commit();
        } else if (last<AppData.NEW_VERSION) {
            database.mainDao().deleteAllSystemItem(myConstants.SYSTEM_SMALL);
            appdata.persistAllData();
            editor.putInt(AppData.LAST_VERSION,AppData.NEW_VERSION);
            editor.commit();
        }
    }




    private void addAddTitles(){
        titles = new ArrayList<>();
        titles.add(myConstants.BASIC_NEEDS_CAMEL_CASE);
        titles.add(myConstants.CLOTHING_CAMEL_CASE);
        titles.add(myConstants.PERSONAL_CARE_CAMEL_CASE);
        titles.add(myConstants.BABY_NEEDS_CAMEL_CASE);
        titles.add(myConstants.HEALTH_CAMEL_CASE);
        titles.add(myConstants.TECHNOLOGY_CAMEL_CASE);
        titles.add(myConstants.FOOD_CAMEL_CASE);
        titles.add(myConstants.BEACH_SUPPLIES_CAMEL_CASE);
        titles.add(myConstants.CAR_SUPPLIES_CAMEL_CASE);
        titles.add(myConstants.NEEDS_CAMEL_CASE);
        titles.add(myConstants.MY_LIST_CAMEL_CASE);
        titles.add(myConstants.MY_SELECTIONS_CAMEL_CASE);
    }

    private void addAllImages(){
        images = new ArrayList<>();
        images.add(R.drawable.p1);
        images.add(R.drawable.p2);
        images.add(R.drawable.p3);
        images.add(R.drawable.p4);
        images.add(R.drawable.p5);
        images.add(R.drawable.p6);
        images.add(R.drawable.p7);
        images.add(R.drawable.p8);
        images.add(R.drawable.p9);
        images.add(R.drawable.p10);
        images.add(R.drawable.p11);
        images.add(R.drawable.p12);

    }
}