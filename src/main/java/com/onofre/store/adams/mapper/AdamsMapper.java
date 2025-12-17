package com.onofre.store.adams.mapper;

import com.onofre.store.store.infraestructure.dao.StoreDAO;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Component
public class AdamsMapper {

    public Map<String, Object> toCreateDebs(StoreDAO dao) {

        Map<String, Object> amount = Map.of(
                "currency", "PYG",
                "value", dao.getAmount().toString() + ".0000"
        );
        Map<String, Object> target = Map.of(
                "type", dao.getClient().getType(),
                "number", dao.getClient().getNumber(),
                "label", dao.getClient().getLabel()
        );

        ZonedDateTime startTime = ZonedDateTime.now(ZoneOffset.UTC).withNano(0);
        ZonedDateTime endTime = startTime.plusHours(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssX");
        Map<String, Object> validPeriod = Map.of(
                "start", startTime.format(formatter),
                "end", endTime.format(formatter)
        );

        Map<String, Object> result = new HashMap<>();
        result.put("docId", dao.getId());
        result.put("amount", amount);
        result.put("label", dao.getLabel());
        result.put("target", target);
        result.put("validPeriod", validPeriod);

        return Map.of("debt", result);
    }
}
