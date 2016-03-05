package com.example.zousheng.keybox;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Canvas;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import java.security.Key;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static List<KeyBox> mDatas = new ArrayList<>();
    private CardView additem;
    public static KeyBoxDatabaseHelper keyBoxDatabaseHelper;
    private MyAdapter myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        keyBoxDatabaseHelper=new KeyBoxDatabaseHelper(this,"KeyBoxStore.db",null,1);
        initData();
        RecyclerView recyclerView= (RecyclerView) findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        myAdapter = new MyAdapter(mDatas,MainActivity.this);
        recyclerView.setAdapter(myAdapter);
        additem= (CardView) findViewById(R.id.additem);
        additem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,SignUpActivity.class);
                startActivityForResult(intent,1);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        switch (requestCode){
            case 1:
                if (resultCode==RESULT_OK){
                    initData();
                    myAdapter.notifyDataSetChanged();
                }
                break;
            default:
        }
    }

    public static void initData(){
        mDatas.clear();
        SQLiteDatabase db=keyBoxDatabaseHelper.getWritableDatabase();
        Cursor cursor=db.query("KeyBox", null, null, null, null, null, null);
        if (cursor.moveToFirst()){
            do {
                int id=cursor.getInt(cursor.getColumnIndex("id"));
                String name=cursor.getString(cursor.getColumnIndex("name"));
                String account=cursor.getString(cursor.getColumnIndex("account"));
                String password=cursor.getString(cursor.getColumnIndex("password"));
                String remark=cursor.getString(cursor.getColumnIndex("remark"));
                String time=cursor.getString(cursor.getColumnIndex("time"));
                KeyBox keyBox=new KeyBox(id,name,account,password,remark,time);
                mDatas.add(keyBox);
            }while (cursor.moveToNext());
        }
        cursor.close();
    }
}
