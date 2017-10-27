package com.pwojczyn.HostStats.services;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "hosts")
public class HostsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "user_id")
    private int userId;
    @Column(name = "host_id")
    private String hostId;

    public HostsModel() {
    }

    public HostsModel(int userId, String hostId) {
        this.userId = userId;
        this.hostId = hostId;
    }


}
