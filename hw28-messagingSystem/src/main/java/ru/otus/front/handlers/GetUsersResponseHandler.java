package ru.otus.front.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.Serializers;
import ru.otus.front.FrontendService;
import ru.otus.messagesystem.Message;
import ru.otus.messagesystem.RequestHandler;

import java.util.Optional;
import java.util.UUID;

public class GetUsersResponseHandler implements RequestHandler {
    private static final Logger logger = LoggerFactory.getLogger(GetUsersResponseHandler.class);
    private final Serializers serializer;
    private final FrontendService frontendService;

    public GetUsersResponseHandler(Serializers serializer, FrontendService frontendService) {
        this.serializer = serializer;
        this.frontendService = frontendService;
    }

    @Override
    public Optional<Message> handle(Message msg) {
        logger.info("new message:{}", msg);
        try {
            String userData = serializer.deserialize(msg.getPayload(), String.class);
            UUID sourceMessageId = msg.getSourceMessageId().orElseThrow(() -> new RuntimeException("Not found sourceMsg for message:" + msg.getId()));
            frontendService.takeConsumer(sourceMessageId, String.class).ifPresent(consumer -> consumer.accept(userData));

        } catch (Exception ex) {
            logger.error("msg:" + msg, ex);
        }
        return Optional.empty();
    }
}
