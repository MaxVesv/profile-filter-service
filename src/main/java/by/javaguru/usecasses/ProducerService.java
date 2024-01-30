package by.javaguru.usecasses;

import by.javaguru.usecasses.dto.ResponseStringInfoFromService;
import org.springframework.messaging.Message;

public interface ProducerService {

    void sendMessageToKafka(String topic, String key, Message<ResponseStringInfoFromService> payload);
}
