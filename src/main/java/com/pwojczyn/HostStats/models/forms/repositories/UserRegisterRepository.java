package com.pwojczyn.HostStats.models.forms.repositories;

import com.pwojczyn.HostStats.services.UserModel;
import com.pwojczyn.HostStats.services.UserRegisterModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRegisterRepository extends CrudRepository<UserRegisterModel,Integer> {
boolean existsByemail(String email);
String findByemail(String email);
String findBypassword(String password);
}
