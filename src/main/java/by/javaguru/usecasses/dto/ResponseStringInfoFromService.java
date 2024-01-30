package by.javaguru.usecasses.dto;

import by.javaguru.usecasses.annotation.ValidServiceName;
import by.javaguru.usecasses.annotation.ValidTypeMessage;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDateTime;


@Builder(setterPrefix = "with")
public record ResponseStringInfoFromService(
        @NotNull
        String key,
        @NotNull
        @ValidTypeMessage
        String typeMessage,
        @NotNull
        @ValidServiceName
        String nameServiceFrom,
        @NotNull
        @ValidServiceName
        String nameServiceTo,
        @NotNull
        LocalDateTime dataMessage,
        @NotNull
        Object payload
) {
}
