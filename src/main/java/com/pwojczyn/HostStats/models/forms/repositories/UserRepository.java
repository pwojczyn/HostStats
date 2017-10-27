package com.pwojczyn.HostStats.models.forms.repositories;

import com.pwojczyn.HostStats.services.UserModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserModel,Integer> {
boolean existsByemail(String email);
UserModel findByEmail(String email);
UserModel findById(int id);
UserModel findByApikey(String apikey);

String findBypassword(String password);
boolean existsByapikey(String apikey);
}
