/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chatapp.repositories;

import com.chatapp.domains.Messages;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Yousef
 */
public interface MessagesRepository extends CrudRepository<Messages,Long>{
//    @Query("select s from ( select m from Messages m "
//            + "where m.conversationId.id = ?1 order by m.time desc) as s where rownum <11")
    List<Messages> findAllByConversationIdId(Long conversationId);
    
    List<Messages> findFirst10ByConversationIdIdOrderByIdDesc(Long conversationId);
    
    List<Messages> findAllByMessageTypeIdAndConversationIdId(Long messageTypeId,Long conversationId);
}
