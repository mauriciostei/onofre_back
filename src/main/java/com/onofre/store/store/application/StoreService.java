package com.onofre.store.store.application;

import com.onofre.store.adams.application.AdamsService;
import com.onofre.store.store.domain.PayStatus;
import com.onofre.store.store.infraestructure.dao.StoreDAO;
import com.onofre.store.store.infraestructure.repository.StoreRepository;
import com.onofre.store.store.mapper.StoreMapper;
import com.onofre.store.store.presentation.dto.StoreDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StoreService {
    private final StoreRepository repository;
    private final StoreMapper mapper;
    private final AdamsService adams;

    public StoreService(StoreRepository repository, StoreMapper mapper, AdamsService adams) {
        this.repository = repository;
        this.mapper = mapper;
        this.adams = adams;
    }

    public void updateStores(Map<String, Object> hook) throws Exception {
        Map<String, Object> debt = (Map<String, Object>) hook.get("debt");
        String id = (String) debt.get("docId");

        StoreDAO dao = repository.findById(id).orElseThrow(() -> new RuntimeException("Id not found"));

        Map<String, Object> amount = (Map<String, Object>) debt.get("amount");
        String value = (String) amount.get("value");
        String paid = (String) amount.get("paid");

        if(value.equalsIgnoreCase(paid)){
            dao.setStatus(PayStatus.SUCCESS);
        } else {
            dao.setStatus(PayStatus.PENDING);
        }

        repository.save(dao);
    }

    public List<StoreDAO> findStores(String ci) throws Exception {
        return repository.findByClientNumberAndStatus(ci, PayStatus.PENDING.toString());
    }

    public Map<String, Object> create(StoreDTO dto) throws Exception {
        StoreDAO dao = mapper.toDAO(dto);
        dao = repository.save(dao);

        Map<String, Object> adamsResponse = adams.createDebs(dao);

        Map<String, Object> debt = (Map<String, Object>) adamsResponse.get("debt");
        String url = (String) debt.get("payUrl");

        dao.setPayUrl(url);
        repository.save(dao);

        return adamsResponse;
    }
}
