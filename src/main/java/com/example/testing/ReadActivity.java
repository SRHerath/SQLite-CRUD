package com.example.testing;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.testing.Sql.DBHandler;
import com.example.testing.Sql.ItemModel;

import java.util.ArrayList;

public class ReadActivity extends AppCompatActivity {

    private ListView listView;
    private static ArrayList<String> list = new ArrayList<>();
    DBHandler db = new DBHandler(ReadActivity.this);
    private static ArrayList<ItemModel> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        listView = findViewById(R.id.list);
        data = new ArrayList<>(db.readData());
        getItem();
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(ReadActivity.this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(stringArrayAdapter);
    }
    public static void getItem() {
        for (int i = 0; i < data.size(); i++) {
            list.add(data.get(i).getId());
        }
    }
    @Override
    public void onBackPressed() {
        list.clear();
        finish();
        super.onBackPressed();
    }
}