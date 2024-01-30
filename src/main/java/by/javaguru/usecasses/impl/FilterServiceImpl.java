package by.javaguru.usecasses.impl;

import by.javaguru.persistence.model.TypeMessage;
import by.javaguru.usecasses.FilterService;
import by.javaguru.usecasses.ListServices;
import by.javaguru.usecasses.dto.RequestStringInfoFromService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class FilterServiceImpl implements FilterService, ListServices {

    @Value()

    @Qualifier("getAllEnabledServiceForFilter")
    private final Set<String> tempListAllEnabledServices;

    private final Environment environment;

    @Override
    public Set<String> getListEnabledFilters() {
        return tempListAllEnabledServices;
    }

    @Override
    public Message<RequestStringInfoFromService> generateMessageForRequest(String payload) {
        RequestStringInfoFromService request = RequestStringInfoFromService
                .builder()
                .withTypeMessage(TypeMessage.REQUEST)
                .withNameServiceTo()
        return null;
    }

    @Override
    public List<Message<RequestStringInfoFromService>> collectAllMessageForSend() {
        return null;
    }


}
