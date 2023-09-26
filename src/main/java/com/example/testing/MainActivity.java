package com.example.testing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.testing.Sql.DBHelper;

public class MainActivity extends AppCompatActivity {

    Button login,Reg;
    DBHelper dbHelper;

    @Override
    public void onBackPressed() {
        MainActivity.this.finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DBHelper(this);
        login =(Button) findViewById(R.id.btnLogin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Login.class);
                startActivity(intent);
            }
        });
        Reg = findViewById(R.id.btnSignUp);
        Reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SignUp.class);
                startActivity(intent);
            }
        });
    }

}