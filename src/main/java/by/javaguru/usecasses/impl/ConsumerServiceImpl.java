package by.javaguru.usecasses.impl;

import by.javaguru.usecasses.ConsumerService;
import by.javaguru.usecasses.ListServices;
import by.javaguru.usecasses.dto.RequestStringInfoFromService;
import by.javaguru.usecasses.dto.ResponseStringInfoFromService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class ConsumerServiceImpl implements ConsumerService {

    @Value("${spring.application.name}")
    private String nameThisInstanceService;

    private final Set<ListServices> listServicesSet;

    private final ProducerServiceImpl producerService;

    @KafkaListener(topicPattern = "topic-name")
    @Override
    public void listenStringKafkaTopic(Message<RequestStringInfoFromService> message) {

        try{
            String[] callServiceAndMethod = message.getPayload().callMethod().split(".");
            Optional<Object> argumentsForCallMethodRow = Optional.of(
                    message.getPayload().argumentForCallMethod());

//          get service from list
            ListServices beanService = listServicesSet.stream()
                    .filter(bean -> bean.getClass().toString().equals(callServiceAndMethod[0]))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException(String.format("Invalid seviceName=%s in payloads message", callServiceAndMethod[0])));

//          get method from service
            Method nameMethod = Arrays.stream(beanService.getClass().getDeclaredMethods())
                    .filter(method -> method.getName().equals(callServiceAndMethod[1]))
                    .findFirst().orElseThrow(() -> new RuntimeException(String.format("Invalid methodName=%s in payloads message", callServiceAndMethod[1])));

            Class<?> returnType = nameMethod.getReturnType();

            log.info(String.format("In request find serviceName=%s, method=%s with return type=%s",
                    beanService.getClass().toString(),
                    nameMethod.getName(),
                    returnType.getName()));

//          invoke method from service
            Object result = nameMethod.invoke(beanService, argumentsForCallMethodRow.orElse((Object[]) null));

            Message<ResponseStringInfoFromService> response = MessageBuilder
                    .withPayload(
                            ResponseStringInfoFromService
                                    .builder()
                                    .withKey(message.getPayload().key())
                                    .withNameServiceFrom(message.getPayload().nameServiceTo())
                                    .withNameServiceTo(message.getPayload().nameServiceFrom())
                                    .withDataMessage(LocalDateTime.now())
                                    .withPayload(result)
                                    .build()
                    ).build();

            String topic = String.format("response-%s-personal-data", message.getPayload().nameServiceTo());
            String key = message.getPayload().key();
            producerService.sendMessageToKafka(topic, key, response);

        } catch (Exception e) {
            log.error(e.getMessage());
           e.printStackTrace();
           throw new RuntimeException(e.getMessage());
        }
    }
}
