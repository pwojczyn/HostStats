package com.pwojczyn.HostStats.services;

import com.pwojczyn.HostStats.utils.PasswordHash;

import lombok.Data;


import javax.persistence.*;



@Data
@Entity
@Table(name = "users")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String email;
    private String password;
    private String apikey;
    private int role;

    public UserModel() {
    }

    public UserModel(String email, String password) {
        this.email = email;

        this.password = PasswordHash.hash(password);

    }

    public UserModel(String email) {
        this.email = email;
    }

    public UserModel(String email, String password, String apikey, int role) {
        this.email = email;
        this.password = password;
        this.apikey = apikey;
        this.role = role;
    }
}
