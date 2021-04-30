package com.example.mytutionteacher;

public class Users {
    private String name, phone, subjects, url,mail, occupation;

    public Users() {
    }

    public Users(String name, String phone, String subjects,String url, String mail, String occupation) {
        this.name = name;
        this.phone = phone;
        this.subjects = subjects;
        this.url=url;
        this.mail=mail;
        this.occupation= occupation;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSubjects() {
        return subjects;
    }

    public void setSubjects(String subjects) {
        this.subjects = subjects;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
