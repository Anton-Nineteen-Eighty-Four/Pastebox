package com.antongulevich.pastebox.service;

import com.antongulevich.pastebox.dto.request.PasteboxRequest;
import com.antongulevich.pastebox.dto.request.PublicStatus;
import com.antongulevich.pastebox.dto.response.PasteboxResponse;
import com.antongulevich.pastebox.dto.response.PasteboxUrlResponse;
import com.antongulevich.pastebox.repository.PasteboxRepository;
import com.antongulevich.pastebox.model.PasteboxEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@ConfigurationProperties(prefix = "app")
public class PasteboxServiceImpl implements PasteboxService{

    @Autowired
    private PasteboxRepository repository;

    private AtomicInteger idGenerator = new AtomicInteger(0);

    private String host = "http:/abc.ru";

    private int publicListSize = 10;

    @Override
    public PasteboxResponse getByHash(String hash) {
        PasteboxEntity entity = repository.getByHash(hash);
        return new PasteboxResponse(entity.getDate(),entity.isPublic());
    }

    @Override
    public List<PasteboxResponse> getFirstPublicPastebox() {
        List<PasteboxEntity> list = repository.getListPublicAndAlive(publicListSize);

        return list.stream().map(entity ->
                 new PasteboxResponse(entity.getDate(), entity.isPublic()))
                .collect(Collectors.toList());
    }

    @Override
    public PasteboxUrlResponse create(PasteboxRequest request) {
        int hash = generateId();
        PasteboxEntity entity = new PasteboxEntity();
        entity.setDate(request.getDate());
        entity.setId(hash);
        entity.setHash(Integer.toHexString(hash));
        entity.setPublic(request.getPublicStatus() == PublicStatus.PUBLIC);
        entity.setLifetime(LocalDateTime.now().plusSeconds(request.getExpirationTimeSeconds()));
        repository.add(entity);
        return  new PasteboxUrlResponse(host + "/" + entity.getHash());
    }

    private int generateId() {
        return idGenerator.getAndIncrement();
    }
}
