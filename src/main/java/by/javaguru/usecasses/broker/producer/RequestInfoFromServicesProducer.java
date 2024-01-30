package by.javaguru.usecasses.broker.producer;

import by.javaguru.usecasses.dto.RequestStringInfoFromService;
import by.javaguru.usecasses.dto.ResponseStringInfoFromService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class RequestInfoFromServicesProducer {

    private final KafkaTemplate kafkaTemplate;

    public void sendRequestToService(String topic, String key, Message<RequestStringInfoFromService> request) {
        kafkaTemplate.send(topic, key, request);
    }

    public void sendResponseToService(String topic, String key, Message<ResponseStringInfoFromService> request) {
        kafkaTemplate.send(topic, key, request);
    }
}
