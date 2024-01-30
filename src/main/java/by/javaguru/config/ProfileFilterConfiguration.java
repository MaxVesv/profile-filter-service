package by.javaguru.config;

import by.javaguru.usecasses.ListServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Configuration
@Slf4j
public class ProfileFilterConfiguration {


    @Value("#{'${custom_test.list_services_for_filter}'.split(',')}")
    private List<String> listString;


    @Bean
    Set<String> getAllEnabledServiceForFilter() {
        Set<String> allEnabledService = new HashSet<>(listString);
        log.info("Enabled services for filter: " + allEnabledService.toString());
        return allEnabledService;
    }

    @Bean
    Set<ListServices> getListBeanOfServices(Set<ListServices> list) {
        log.info("Create list of all services: " + list.toString());
        return list;
    }

}
