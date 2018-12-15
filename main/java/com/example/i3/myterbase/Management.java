package com.example.i3.myterbase;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class Management extends AppCompatActivity {

    private DBHelper dbHelper;
    private Button btnInsertDatabase;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.database_management);


        btnInsertDatabase = (Button) findViewById(R.id.btnInsertButton);
        btnInsertDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LinearLayout layout = new LinearLayout(Management.this);
                layout.setOrientation(LinearLayout.VERTICAL);

                final EditText etName = new EditText(Management.this);
                etName.setHint("이름을 입력하세요.");

                final EditText etAge = new EditText(Management.this);
                etAge.setHint("나이를 입력하세요.");

                final EditText etPhone = new EditText(Management.this);
                etPhone.setHint("전화번호를 입력하세요.");

                layout.addView(etName);
                layout.addView(etAge);
                layout.addView(etPhone);
                AlertDialog.Builder dialog = new AlertDialog.Builder(Management.this);
                dialog.setTitle("정보를 입력하세요")
                        .setView(layout)
                        .setPositiveButton("등록", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String name = etName.getText().toString();
                                String age = etAge.getText().toString();
                                String phone = etPhone.getText().toString();
                                if (dbHelper == null) {
                                    dbHelper = new DBHelper(Management.this, "TEST", null, 1);
                                }

                                Person person = new Person();
                                person.setName(name);
                                person.setAge(age);
                                person.setPhone(phone);

                                dbHelper.addPerson(person);
                            }
                        })
                        .setNeutralButton("취소", new DialogInterface.OnClickListener() {
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
