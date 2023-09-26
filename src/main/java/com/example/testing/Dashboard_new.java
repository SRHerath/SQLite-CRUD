package com.example.testing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.testing.Sql.DBHandler;
import com.example.testing.Sql.ItemModel;

import java.util.ArrayList;

public class Dashboard_new extends AppCompatActivity {

    private Spinner spinnerMain;
    private Button createMain,readMain,updateMain,deleteMain;
    private String s;
    private String s1;
    DBHandler db = new DBHandler(Dashboard_new.this);
    private static ArrayList<ItemModel> data ;
    private static ArrayList<String> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_new);

        data = new ArrayList<>(db.readData());
        items = new ArrayList<>();
        getItem();
        spinnerMain = findViewById(R.id.spinnerMain);
        createMain = findViewById(R.id.createEditText);
        readMain = findViewById(R.id.readEditText);
        updateMain = findViewById(R.id.updateEditText);
        deleteMain = findViewById(R.id.deleteEditText);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_activated_1,items);
        spinnerMain.setAdapter(adapter);
        spinnerMain.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                s1=adapterView.getItemAtPosition(i).toString();


            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        createMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s = "CreateMain";
                Intent i = new Intent(Dashboard_new.this, RUI_Activity.class);
                i.putExtra("Button",s);
                startActivity(i);
            }
        });
        readMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s = "ReadMain";
                Intent i = new Intent(Dashboard_new.this, RUI_Activity.class);
                i.putExtra("Button",s);
                i.putExtra("GetItem",s1);
                startActivity(i);
            }
        });
        updateMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s = "UpdateMain";
                Intent i = new Intent(Dashboard_new.this, RUI_Activity.class);
                i.putExtra("Button",s);
                i.putExtra("GetItem",s1);
                startActivity(i);
            }
        });
        deleteMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (db.deleteData(s1)){
                    Toast.makeText(Dashboard_new.this,"Data Dropped",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public static void getItem(){
        for (int i =0 ; i<data.size();i++){
            items.add(data.get(i).getId());
        }
    }
}