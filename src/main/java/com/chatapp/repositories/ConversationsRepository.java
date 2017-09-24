/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chatapp.repositories;

import com.chatapp.domains.Conversations;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Yousef
 */
public interface ConversationsRepository extends CrudRepository<Conversations,Long>{
//    List<Conversations> findAllByConversationsRecepientsListOwnerIdIdOrConversationsRecepientsListUserIdId(Long id);
    @Query("select c from Conversations c where c.id in "
            + "(select r.conversationId.id from ConversationsRecepients r "
            + "where r.ownerId.id = :id "
            + "or r.userId.id = :id) order by c.lastMessageTime desc")
    List<Conversations> findMyConversations(@Param("id")Long id);
    
    @Query("select c from Conversations c where c.id in "
            + "(select r.conversationId.id from ConversationsRecepients r "
            + "where (r.ownerId.id = :id and r.userId.id = :id2 )"
            + "or (r.userId.id = :id and r.ownerId.id = :id2)) and c.conversationTypeId.id = 1 ")
    List<Conversations> findConversation(@Param("id")Long id,@Param("id2")Long id2);
}
