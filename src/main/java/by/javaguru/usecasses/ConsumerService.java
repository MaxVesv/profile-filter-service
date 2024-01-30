package by.javaguru.usecasses;

import by.javaguru.usecasses.dto.RequestStringInfoFromService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

public interface ConsumerService {

    void listenStringKafkaTopic(Message<RequestStringInfoFromService> message);
}
