package com.antongulevich.pastebox.repository;

import com.antongulevich.pastebox.model.PasteboxEntity;

import java.util.List;

public interface PasteboxRepository {
    PasteboxEntity getByHash(String hash);

    List<PasteboxEntity> getListPublicAndAlive(int amount);

    void add(PasteboxEntity pasteboxEntity);

}
