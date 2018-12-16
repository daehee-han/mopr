package com.example.i3.myterbase;


import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class ViewData extends AppCompatActivity{
    private DBHelper dbHelper;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewing_database);


        final ListView lvPeople = (ListView) findViewById(R.id.lvPeople);




                // ListView를 보여준다.

                lvPeople.setVisibility(View.VISIBLE);

                // DB Helper가 Null이면 초기화 시켜준다.
                if (dbHelper == null) {
                    dbHelper = new DBHelper(ViewData.this, "TEST", null, 1);
                }

                // 1. Person 데이터를 모두 가져온다.
                List people = dbHelper.getAllPersonData();

                // 2. ListView에 Person 데이터를 모두 보여준다.
                lvPeople.setAdapter(new PersonListAdapter(people, ViewData.this));

        };
    }

    class PersonListAdapter extends BaseAdapter {
        private List people;
        private Context context;

        public PersonListAdapter(List people, Context context){
        this.people = people;
        this.context= context;
        }

        @Override
        public int getCount() {
            return this.people.size();
        }

        @Override
        public Object getItem(int position) {
            return this.people.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Holder holder = null;
         if( convertView == null ) {
             // convertView가 없으면 초기화합니다.
             convertView = new LinearLayout(context);
             ((LinearLayout) convertView).setOrientation(LinearLayout.HORIZONTAL);
             TextView tvId = new TextView(context);
             tvId.setPadding(10, 0, 20, 0);
             tvId.setTextColor(Color.rgb(0, 0, 0));
             TextView tvName = new TextView(context);
             tvName.setPadding(20, 0, 20, 0);
             tvName.setTextColor(Color.rgb(0, 0, 0));
             TextView tvAge = new TextView(context);
             tvAge.setPadding(20, 0, 20, 0);
             tvAge.setTextColor(Color.rgb(0, 0, 0));
             TextView tvPhone = new TextView(context);
             tvPhone.setPadding(20, 0, 20, 0);
             tvPhone.setTextColor(Color.rgb(0, 0, 0));
             ( (LinearLayout) convertView).addView(tvId);
             ( (LinearLayout) convertView).addView(tvName);
             ( (LinearLayout) convertView).addView(tvAge);
             ( (LinearLayout) convertView).addView(tvPhone);
             holder = new Holder();
             holder.tvId = tvId;
             holder.tvName = tvName;
             holder.tvAge = tvAge;
             holder.tvPhone = tvPhone;
             convertView.setTag(holder);
         } else {
             // convertView가 있으면 홀더를 꺼냅니다.
             holder = (Holder) convertView.getTag();
         } // 한명의 데이터를 받아와서 입력합니다.
            Person person = (Person) getItem(position);
            holder.tvId.setText(person.get_id() + "");
            holder.tvName.setText(person.getName());
            holder.tvAge.setText(person.getAge() + "");
            holder.tvPhone.setText(person.getPhone());
            return convertView;
        }
    }
    class Holder {
        public TextView tvId;
        public TextView tvName;
        public TextView tvAge;
        public TextView tvPhone;
    }












