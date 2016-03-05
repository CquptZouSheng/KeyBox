package com.example.zousheng.keybox;

/**
 * Created by Zou Sheng on 2016/2/18.
 */
public class KeyBox {
    public int id;
    public String name,account,password,remark,time;
    public KeyBox(int id,String name,String account,String password,String remark,String time){
        this.time=time;
        this.name=name;
        this.account=account;
        this.password=password;
        this.remark=remark;
        this.id=id;
    }
    public String getName(){
        return name;
    }
    public String getAccount(){
        return account;
    }
    public String getPassword(){
        return password;
    }
    public String getRemark(){
        return remark;
    }
    public String getTime(){
        return time;
    }
    public int getId(){
        return id;
    }
}
