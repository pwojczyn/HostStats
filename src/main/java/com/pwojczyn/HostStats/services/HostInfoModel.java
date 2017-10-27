package com.pwojczyn.HostStats.services;

import lombok.Data;

import javax.persistence.*;
import java.time.format.DateTimeFormatter;

/*
id
hostId
hostTitle
hostMemory
hostDisk
hostCpu
hostUpdate
hostIp
hostUptime
 */
@Data
@Entity
@Table(name = "host_info")
public class HostInfoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "host_id")
    private String hostId;
    @Column(name = "host_title")
    private String hostTitle;
    @Column(name = "host_os")
    private String hostOs;
    @Column(name = "host_memory")
    private String hostMemory;
    @Column(name = "host_disk")
    private String hostDisk;
    @Column(name = "host_cpu")
    private String hostCpu;
    @Column(name = "host_update")
    private String hostUpdate;
    @Column(name = "host_ip")
    private String hostIp;
    @Column(name = "host_uptime")
    private String hostUptime;
    @Column(name = "user_id")
    private int userId;

}
