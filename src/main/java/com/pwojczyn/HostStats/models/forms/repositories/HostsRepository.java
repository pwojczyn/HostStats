package com.pwojczyn.HostStats.models.forms.repositories;

import com.pwojczyn.HostStats.services.HostsModel;
import com.pwojczyn.HostStats.services.UserModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HostsRepository extends CrudRepository<HostsModel,Integer> {
    boolean existsByhostId(String hostApiKey);
    HostsModel findByhostId(String hostApiKey);

}
