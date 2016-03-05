package com.example.zousheng.keybox;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SignUpActivity extends AppCompatActivity {

    private ImageView check,arrow;
    private EditText name,account,password,remark;
    private KeyBoxDatabaseHelper dbHelper;
    private Date date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        final Intent intent_rewrite=getIntent();
        date=new Date();
        check= (ImageView) findViewById(R.id.check);
        arrow= (ImageView) findViewById(R.id.arrow);
        name= (EditText) findViewById(R.id.name);
        account= (EditText) findViewById(R.id.account);
        password= (EditText) findViewById(R.id.password);
        remark= (EditText) findViewById(R.id.remark);
        name.setText(intent_rewrite.getStringExtra("name"));
        account.setText(intent_rewrite.getStringExtra("account"));
        password.setText(intent_rewrite.getStringExtra("password"));
        remark.setText(intent_rewrite.getStringExtra("remark"));
        final int ID=intent_rewrite.getIntExtra("id",-1);
        dbHelper=new KeyBoxDatabaseHelper(this,"KeyBoxStore.db",null,1);
        check.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String string1 = name.getText().toString();
                    String string2 = account.getText().toString();
                    String string3 = password.getText().toString();
                    String string4 = remark.getText().toString();
                    DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    String string5 = format.format(date);
                    if ("".equals(string1) || string1 == null) {
                        Toast.makeText(SignUpActivity.this, "名字不能为空", Toast.LENGTH_SHORT).show();
                    } else if ("".equals(string2) || string2 == null) {
                        Toast.makeText(SignUpActivity.this, "账号不能为空", Toast.LENGTH_SHORT).show();
                    } else if ("".equals(string3) || string3 == null) {
                        Toast.makeText(SignUpActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
                    } else {

                        SQLiteDatabase db = dbHelper.getWritableDatabase();
                        if (ID==-1) {
                            db.execSQL("INSERT INTO KeyBox(name, account, password, remark, time) VALUES(?, ?, ?, ?, ?)", new String[]{string1, string2, string3, string4, string5});
                        }
                        else
                        {
                            db.execSQL("update KeyBox set name=? where id=" + ID, new String[]{string1});
                            db.execSQL("update KeyBox set account=? where id=" + ID, new String[]{string2});
                            db.execSQL("update KeyBox set password=? where id="+ID,new String[]{string3});
                            db.execSQL("update KeyBox set remark=? where id="+ID,new String[]{string4});
                        }
                        Intent intent = new Intent();
                        intent.putExtra("name_back",string1);
                        intent.putExtra("account_back",string2);
                        intent.putExtra("password_back",string3);
                        intent.putExtra("remark_back",string4);
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                }
            });
        arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent();
                    setResult(RESULT_CANCELED, intent);
                    finish();
            }
        });
    }
}
