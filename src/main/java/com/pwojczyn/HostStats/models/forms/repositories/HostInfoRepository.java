package com.pwojczyn.HostStats.models.forms.repositories;

import com.pwojczyn.HostStats.services.HostInfoModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HostInfoRepository extends CrudRepository<HostInfoModel,Integer> {
    boolean existsByhostId(String hostApiKey);
    HostInfoModel findByHostId(String hostApiKey);
    List<HostInfoModel> findByUserId(int userId);


}


