package com.pakiboy612gmailtest.hajjdost;

import com.google.firebase.database.DataSnapshot;

public class User {


    public String userName , address, groupName, gender,age,zphoneNumber;
   // public String latitude , longitude,accuracy,phoneNumber;

    public User() {
    }

    public User (String userName,String address, String groupName , String gender , String age, String phoneNumber){
        this.userName = userName;
        this.address = address;
        this.groupName = groupName;
        this.gender = gender;
        this.age = age;
        this.zphoneNumber = phoneNumber;
    }



//    public String getUserName() {
//        return userName;
//    }
//
//    public void setUserName(String userName) {
//        this.userName = userName;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
//
//    public String getGroupName() {
//        return groupName;
//    }
//
//    public void setGroupName(String groupName) {
//        this.groupName = groupName;
//    }
//
//    public String getGender() {
//        return gender;
//    }
//
//    public void setGender(String gender) {
//        this.gender = gender;
//    }
//
//    public Double getAge() {
//        return age;
//    }
//
//    public void setAge(Double age) {
//        this.age = age;
//    }

}
