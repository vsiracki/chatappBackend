/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chatapp.repositories;

import com.chatapp.domains.User;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Yousef
 */
public interface UserRepository extends CrudRepository<User,Long>{
    List<User> findAllByUserName(String userName);
    List<User> findAllByUserNameAndPassword(String userName,String password);
    
    @Query("select u from User u where (LOWER(u.userName) like %:keyword% "
            + "or LOWER(u.firstName||' '||u.lastName) like %:keyword%) and u.id not in ( select f.friendId.id from Friends f where f.userId.id = :userId) and u.id != :userId")
    List<User> searchPeople(@Param("keyword")String keyword,@Param("userId")Long userId);
    @Query("select u from User u where u.email = :email")
    List<User> findAllByEmail(@Param("email")String email);
}
