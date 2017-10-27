package com.pwojczyn.HostStats.services;

import com.pwojczyn.HostStats.utils.PasswordHash;
import com.pwojczyn.HostStats.utils.RandomAESKeyGen;
import lombok.Data;

import javax.persistence.*;
import java.security.NoSuchAlgorithmException;


@Data
@Entity
@Table(name = "users")
public class UserRegisterModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String email;
    private String password;
    private String apikey;
    private int role;

    public UserRegisterModel(String email, String password) {
        this.email = email;

        this.password = PasswordHash.hash(password);

        String key = null;
        try {
            key = RandomAESKeyGen.generate(128);
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Exception caught");
            e.printStackTrace();
        }
        this.apikey = key;
        this.role = 0;
    }

}
