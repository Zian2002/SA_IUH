package com.iuh.fit;

/**
 * created-date 2024
 */
public class Student {
    private String name;
    private String phone;
    private String email;

    private String doSomething;

    private void name(){
        System.out.println(name);
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
