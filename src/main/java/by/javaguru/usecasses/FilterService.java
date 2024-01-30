package by.javaguru.usecasses;

import by.javaguru.usecasses.dto.RequestStringInfoFromService;
import org.springframework.messaging.Message;

import java.util.List;
import java.util.Set;

public interface FilterService {

    Set<String> getListEnabledFilters();

    Message<RequestStringInfoFromService> generateMessageForRequest();

    List<Message<RequestStringInfoFromService>> collectAllMessageForSend();
}
