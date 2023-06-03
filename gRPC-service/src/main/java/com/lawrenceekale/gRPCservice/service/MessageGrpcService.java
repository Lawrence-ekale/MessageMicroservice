package com.lawrenceekale.gRPCservice.service;

import com.lawrenceekale.GetMessageByIdRequest;
import com.lawrenceekale.Message;
import com.lawrenceekale.MessageResponse;
import com.lawrenceekale.MessageServiceGrpc;
import com.lawrenceekale.gRPCservice.repository.MessageRepository;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@GrpcService
public class MessageGrpcService extends MessageServiceGrpc.MessageServiceImplBase {
    @Autowired
    private  MessageRepository messageRepository;

    @Override
    public void sendMessage(Message request, StreamObserver<MessageResponse> responseObserver) {
        messageRepository.save(request);
        responseObserver.onCompleted();
    }

    @Override
    public void getMessageById(GetMessageByIdRequest request, StreamObserver<MessageResponse> responseObserver) {
        List<Message> messages = messageRepository.findBySenderOrRecipientId(request.getRecipientId());
        MessageResponse.Builder responseBuilder = MessageResponse.newBuilder();
        responseBuilder.addAllMessages(messages);

        responseObserver.onNext(responseBuilder.build());
        responseObserver.onCompleted();
    }
}
