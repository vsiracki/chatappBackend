/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chatapp.repositories;

import com.chatapp.domains.UnreadMessages;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Yousef
 */
@Transactional
public interface UnreadMessagesRepository extends CrudRepository<UnreadMessages,Long>{
    @Query(value = "select CONVERSATION_ID,COUNT(*) FROM T_UNREAD_MESSAGES WHERE USER_ID = :id GROUP BY CONVERSATION_ID",nativeQuery = true)
    List<Object[]> findUnreadMessagesByUserId(@Param(value = "id")Long id);
    
//    @Query("delete from UnreadMessages u where u.userId.id = :id and u.conversationId.id = :convId")
    List<UnreadMessages> findAllByUserIdIdAndConversationIdId(Long userId,Long conversationId);
    
    @Modifying
    @Transactional
    @Query(value="delete from UnreadMessages u where u.userId.id = :id and u.conversationId.id = :convId")
    void deleteByUserNameAndConversation(@Param("id")Long id,@Param("convId")Long convId);
    
    @Modifying
    @Transactional
    Long deleteByUserIdIdAndConversationIdId(Long id,Long convId);
}
