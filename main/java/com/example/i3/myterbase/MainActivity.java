package com.example.i3.myterbase;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    private Button btnCreateDatabase;
    private DBHelper dbHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b = (Button)findViewById(R.id.btnmanagement);
        b.setOnClickListener(new View.OnClickListener() {
                                 @Override
                                 public void onClick(View view) {
                                     Intent intent = new Intent(
                                             getApplicationContext(), // 현재 화면의 제어권자
                                             Management.class); // 다음 넘어갈 클래스 지정
                                     startActivity(intent); // 다음 화면으로 넘어간다
                                 }
                             });

        Button b1 = (Button)findViewById(R.id.btnviewdata);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        getApplicationContext(), // 현재 화면의 제어권자
                        ViewData.class); // 다음 넘어갈 클래스 지정
                startActivity(intent); // 다음 화면으로 넘어간다
            }
        });

        btnCreateDatabase = (Button) findViewById(R.id.btnCreateButton);
        btnCreateDatabase.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                final EditText etDBName = new EditText(MainActivity.this);
                etDBName.setHint("Enter the DBNAME");

                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("Enter the Database name")
                        .setMessage("Enter the Database name")
                        .setView(etDBName)
                        .setPositiveButton("Create", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                if( etDBName.getText().toString().length()>0){
                                    dbHelper = new DBHelper(MainActivity.this,etDBName.getText().toString(),null,1);
                                    dbHelper.testDB();
                                }
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .create()
                        .show();
            }
        });






    }
}

