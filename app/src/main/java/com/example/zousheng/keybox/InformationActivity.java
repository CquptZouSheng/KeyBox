package com.example.zousheng.keybox;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class InformationActivity extends AppCompatActivity {

    private KeyBoxDatabaseHelper keyBoxDatabaseHelper;
    TextView name,account,password,remark,data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        final Intent intent=getIntent();
        keyBoxDatabaseHelper=new KeyBoxDatabaseHelper(this,"KeyBoxStore.db",null,1);
        name= (TextView) findViewById(R.id.Name);
        account= (TextView) findViewById(R.id.Account);
        password= (TextView) findViewById(R.id.Password);
        remark= (TextView) findViewById(R.id.Remark);
        data= (TextView) findViewById(R.id.Data);
        name.setSelected(true);
        name.setText(intent.getStringExtra("name"));

        account.setText(intent.getStringExtra("account"));
        password.setText(intent.getStringExtra("password"));
        remark.setText(intent.getStringExtra("remark"));
        data.setText(intent.getStringExtra("data"));
        final int ID=intent.getIntExtra("id",-1);
        ImageView arrow= (ImageView) findViewById(R.id.arrow_information);
        arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_information = new Intent(InformationActivity.this, MainActivity.class);
                startActivity(intent_information);
            }
        });
        ImageView delete= (ImageView) findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db=MainActivity.keyBoxDatabaseHelper.getWritableDatabase();
                db.execSQL("delete from KeyBox where id=" + ID);
                MainActivity.mDatas.clear();
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
                        MainActivity.mDatas.add(keyBox);
                    }while (cursor.moveToNext());
                }
                cursor.close();
                db.close();
                Intent intent1=new Intent(InformationActivity.this,MainActivity.class);
                startActivity(intent1);
            }
        });
        CardView cardView= (CardView) findViewById(R.id.rewrite);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2=new Intent(InformationActivity.this,SignUpActivity.class);
                intent2.putExtra("id",intent.getIntExtra("id",-1));
                intent2.putExtra("name",name.getText().toString());
                intent2.putExtra("account",account.getText().toString());
                intent2.putExtra("password",password.getText().toString());
                intent2.putExtra("remark", remark.getText().toString());
                startActivityForResult(intent2, 2);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        switch (requestCode){
            case 2:
                if (resultCode==RESULT_OK){
                    MainActivity.initData();
                    name.setText(data.getStringExtra("name_back"));
                    account.setText(data.getStringExtra("account_back"));
                    password.setText(data.getStringExtra("password_back"));
                    remark.setText(data.getStringExtra("remark_back"));
                }
                break;
            default:
        }
    }
}
