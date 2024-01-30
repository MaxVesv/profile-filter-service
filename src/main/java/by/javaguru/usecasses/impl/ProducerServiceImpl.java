package by.javaguru.usecasses.impl;

import by.javaguru.usecasses.ProducerService;
import by.javaguru.usecasses.broker.producer.RequestInfoFromServicesProducer;
import by.javaguru.usecasses.dto.RequestStringInfoFromService;
import by.javaguru.usecasses.dto.ResponseStringInfoFromService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProducerServiceImpl implements ProducerService {

    private final RequestInfoFromServicesProducer producer;
    @Override
    public void sendMessageToKafka(String topic, String key, Message<ResponseStringInfoFromService> payload) {
        producer.sendResponseToService(topic, key, payload);
    }
}
