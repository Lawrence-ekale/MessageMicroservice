package com.lawrenceekale.gRPCservice.repository;

import com.lawrenceekale.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message,Long> {
    @Query("SELECT m FROM Message m WHERE m.senderId = :userId OR m.recipientId = :userId")
    List<Message> findBySenderOrRecipientId(long recipientId);
}
