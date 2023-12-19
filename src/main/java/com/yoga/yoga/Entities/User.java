package com.yoga.yoga.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Size(min = 2,max = 20,message = "min 2 and max 20 character are allowed !!")
    @NotBlank(message = "Name field is required")
    private String name;

    @NotNull(message = "age field should not be empty")
    @Min(value = 18, message = "Age should be atleast 18")
    @Max(value = 65, message = "Age should be atmost 65")
    private int age;

    @Column(unique = true)
    @Email(message = "Enter Valid Email")
    private String email;
    
    @Size(min = 6, message = "Password size must be greater then 5 charcaters")
    @NotBlank(message = "Password is required")
    private String password;

    // 1-> Batch(6-7am); 2-> Batch(7-8am); 3-> Batch(8-9am); 4-> Batch(5-6pm)
    private int batch;

    private boolean isPaid=false;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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

    public int getBatch() {
        return batch;
    }

    public void setBatch(int batch) {
        this.batch = batch;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean isPaid) {
        this.isPaid = isPaid;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", age=" + age + ", email=" + email + ", password=" + password
                + ", batch=" + batch + ", isPaid=" + isPaid + "]";
    }

    

    

}
