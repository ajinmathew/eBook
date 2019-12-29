package com.cs.ajinmathew.project.ebook;

public class Student {
    String name,email,password,admission_no,place,district,parent_name,mobile;

    public Student() {
    }

    public Student(String name, String email, String password, String admission_no, String place, String district, String parent_name, String mobile) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.admission_no = admission_no;
        this.place = place;
        this.district = district;
        this.parent_name = parent_name;
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAdmission_no() {
        return admission_no;
    }

    public void setAdmission_no(String admission_no) {
        this.admission_no = admission_no;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getParent_name() {
        return parent_name;
    }

    public void setParent_name(String parent_name) {
        this.parent_name = parent_name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
