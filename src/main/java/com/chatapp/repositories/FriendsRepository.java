/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chatapp.repositories;

import com.chatapp.domains.Friends;
import com.chatapp.domains.User;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Yousef
 */
public interface FriendsRepository extends CrudRepository<Friends,Long>{
    
}
