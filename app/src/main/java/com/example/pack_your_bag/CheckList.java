package com.example.pack_your_bag;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.pack_your_bag.Adapter.CheckListAdapter;
import com.example.pack_your_bag.Constants.myConstants;
import com.example.pack_your_bag.Data.AppData;
import com.example.pack_your_bag.Database.RoomDb;
import com.example.pack_your_bag.Models.Items;

import java.util.ArrayList;
import java.util.List;

public class CheckList extends AppCompatActivity {

    RecyclerView recyclerView;
    CheckListAdapter checkListAdapter;
    RoomDb database;
    List<Items> itemsList = new ArrayList<>();
    String header,show;

    EditText txtAdd;
    Button btnAdd;
    LinearLayout linearLayout;


    @Override
    public boolean onCreatePanelMenu(int featureId, @NonNull Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_one,menu);

        if(myConstants.MY_SELECTIONS.equals(header)){
            menu.getItem(0).setVisible(false);
            menu.getItem(2).setVisible(false);
            menu.getItem(3).setVisible(false);
        } else if (myConstants.MY_LIST_CAMEL_CASE.equals(header)) {
            menu.getItem(1).setVisible(false);
        }

        MenuItem menuItem = menu.findItem(R.id.btnSearch);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                List<Items> mFinalList = new ArrayList<>();
                for (Items items:itemsList) {
                    if(items.getItemname().toLowerCase().startsWith(newText.toLowerCase())){
                        mFinalList.add(items);
                    }

                }
                updateRecycler(mFinalList);
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            // Handle back arrow button click
            if (item.getItemId() == android.R.id.home) {
                finish(); // Close the current activity
                return true;
            }

            // Handle other menu items


        Intent intent = new Intent(this,CheckList.class);
        AppData appData = new AppData(database,this);

        int itemId = item.getItemId();
        if (itemId == R.id.btnMySelection) {
            intent.putExtra(myConstants.HEADER_SMALL,myConstants.MY_SELECTIONS);
            intent.putExtra(myConstants.SHOW_SMALL,myConstants.FALSE_STRING);
            startActivityForResult(intent,101);
            return true;
        } else if (itemId == R.id.btnCustomList) {
            intent.putExtra(myConstants.HEADER_SMALL,myConstants.MY_LIST_CAMEL_CASE);
            intent.putExtra(myConstants.SHOW_SMALL,myConstants.TRUE_STRING);
            startActivity(intent);
            return true;
        } else if(itemId==R.id.btnDefaultDelete){
            new AlertDialog.Builder(this)
                    .setTitle("Delete default data")
                    .setMessage("Are you sure?\n\n As this will delete the data provided by (Pack Your Bag) while installing.")
                    .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            appData.persistDataByCategory(header,true);
                            itemsList = database.mainDao().getAll(header);
                            updateRecycler(itemsList);
                        }
                    }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).setIcon(R.drawable.baseline_add_alert_24).show();

            return true;


        } else if (itemId==R.id.btnReset) {
            new AlertDialog.Builder(this).setTitle("Reset to default")
                    .setMessage("Are you sure?\n\nThis will load the default data provided by 'Pack Your Bag' and will delete the custom data you have added in (" + header + ")."
                    ).setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            appData.persistDataByCategory(header,false);
                            itemsList = database.mainDao().getAll(header);
                            updateRecycler(itemsList);
                        }
                    }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).setIcon(R.drawable.baseline_add_alert_24).show();
            return true;

        }
        else if (itemId==R.id.btnLogout){
            performLogout();
            return true;
        }
        else {
            return super.onOptionsItemSelected(item);
        }

    }
    private void performLogout(){
        Toast toast = Toast.makeText(this, "Logout successful", Toast.LENGTH_SHORT);
        Intent intent = new Intent(this, logActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK); // Clear back stack
        startActivity(intent);
        finish();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_list);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        header = intent.getStringExtra(myConstants.HEADER_SMALL);
        show = intent.getStringExtra(myConstants.SHOW_SMALL);

        getSupportActionBar().setTitle(header);

        txtAdd = findViewById(R.id.txtAdd);
        btnAdd = findViewById(R.id.btnAdd);
        recyclerView=findViewById(R.id.recycler_view);
        linearLayout=findViewById(R.id.linearLayout);

        database = RoomDb.getInstance(this);

        if(myConstants.FALSE_STRING.equals(show)){
            linearLayout.setVisibility(View.GONE);
            itemsList = database.mainDao().getAllSelected(true);
        }else{
            itemsList = database.mainDao().getAll(header);

        }
        updateRecycler(itemsList);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemName = txtAdd.getText().toString();
                if(itemName!=null && !itemName.isEmpty()){
                    addNewItem(itemName);
                    Toast.makeText(CheckList.this, "Item added", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(CheckList.this, "Empty can't be added", Toast.LENGTH_SHORT).show();
                }
            }
        });




    }
    private void addNewItem(String itemName){
        Items item = new Items();
        item.setChecked(false);
        item.setCategory(header);
        item.setItemname(itemName);
        item.setAddedBy(myConstants.USER_SMALL);
        database.mainDao().saveItems(item);
        itemsList=database.mainDao().getAll(header);
        updateRecycler(itemsList);
        recyclerView.scrollToPosition(checkListAdapter.getItemCount());
        txtAdd.setText("");

    }
    private void updateRecycler(List<Items> itemsList){
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL));
        checkListAdapter=new CheckListAdapter(CheckList.this,itemsList,database,show);
        recyclerView.setAdapter(checkListAdapter);
    }

}
