package com.example.testing;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.testing.Sql.DBHandler;
import com.example.testing.Sql.ItemModel;

import java.util.ArrayList;

public class RUI_Activity extends AppCompatActivity {

    private EditText editTextID,editTextCode,editTextDes,editTextQua;
    private Button btnRIU;
    private Intent i;
    private String s;
    private ArrayList<ItemModel> list;
    private DBHandler db = new DBHandler(RUI_Activity.this);
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rui);
        editTextID = findViewById(R.id.editIDRIU);
        editTextCode = findViewById(R.id.editCodeRIU);
        editTextDes = findViewById(R.id.editDesRIU);
        editTextQua = findViewById(R.id.editQuaRIU);
        btnRIU = findViewById(R.id.btnRIU);
        i = getIntent();
        s=i.getStringExtra("Button");
        if (s.equals("ReadMain")){
            String id = null, code = null, des = null, quan = null;
            id =i.getStringExtra("GetItem");
            list = new ArrayList<>(db.readData());
            for (ItemModel d : list) {
                String temp = d.getId();
                if (temp.equals(id)) {
                    id = d.getId();
                    code = d.getItemCode();
                    des = d.getItemDes();
                    quan = d.getItemQuan();
                    editTextID.setText(id);
                    editTextCode.setText(code);
                    editTextDes.setText(des);
                    editTextQua.setText(quan);
                    btnRIU.setText("OK");
                    btnRIU.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i = new Intent(RUI_Activity.this,Dashboard_new.class);
                            startActivity(i);
                            finish();
                        }
                    });
                }
            }
            editTextID.setEnabled(false);
            editTextCode.setEnabled(false);
            editTextDes.setEnabled(false);
            editTextQua.setEnabled(false);
        }else if (s.equals("CreateMain")){
            editTextID.setText("");
            editTextCode.setText("");
            editTextDes.setText("");
            editTextQua.setText("");
            btnRIU.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String id,code ,des,quan;
                    id = editTextID.getText().toString();
                    code = editTextCode.getText().toString();
                    des = editTextDes.getText().toString();
                    quan = editTextQua.getText().toString();
                    if (db.insertData(id,code ,des,quan)){
                        Toast.makeText(RUI_Activity.this,"Data Inserted",Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(RUI_Activity.this,Dashboard_new.class);
                        startActivity(i);
                        finish();
                    }
                }
            });
        }
        else if (s.equals("UpdateMain")){
            editTextID.setEnabled(false);
            String id = null, code = null, des = null, quan = null;
            id =i.getStringExtra("GetItem");
            list = new ArrayList<>(db.readData());
            for (ItemModel d : list) {
                String temp = d.getId();
                if (temp.equals(id)) {
                    id = d.getId();
                    code = d.getItemCode();
                    des = d.getItemDes();
                    quan = d.getItemQuan();
                    editTextID.setText(id);
                    editTextCode.setText(code);
                    editTextDes.setText(des);
                    editTextQua.setText(quan);
                    btnRIU.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String id1 = editTextID.getText().toString();
                            String code1 = editTextCode.getText().toString();
                            String des1 = editTextDes.getText().toString();
                            String quan1 =editTextQua.getText().toString();
                            if (db.updateData(id1,code1,des1,quan1)){
                                Toast.makeText(RUI_Activity.this,"Data Updated",Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(RUI_Activity.this,Dashboard_new.class);
                                startActivity(i);
                                finish();
                            }else {
                                Toast.makeText(RUI_Activity.this,"Data Update Failed",Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }
                    });
                }
            }
        }else if (s.equals("DeleteMain")){
            String id = i.getStringExtra("GetItem");
            if (db.deleteData(id)){
                Toast.makeText(RUI_Activity.this,"Data Droped",Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(RUI_Activity.this,"Failed to remove data",Toast.LENGTH_SHORT).show();
            }
            Intent i = new Intent(RUI_Activity.this,Dashboard_new.class);
            startActivity(i);
            finish();
        }
    }
}