package com.example.TempApi.Repository;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.TempApi.Model.User;

@Repository
public interface UserRepository extends CrudRepository<User,Long>{
	User findByUserName(String userName);



}
