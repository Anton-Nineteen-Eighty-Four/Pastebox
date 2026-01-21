package com.antongulevich.pastebox.service;

import com.antongulevich.pastebox.dto.request.PasteboxRequest;
import com.antongulevich.pastebox.dto.response.PasteboxResponse;
import com.antongulevich.pastebox.dto.response.PasteboxUrlResponse;

import java.util.List;

public interface PasteboxService {
    PasteboxResponse getByHash(String hash);
    List<PasteboxResponse> getFirstPublicPastebox();
    PasteboxUrlResponse create(PasteboxRequest request);
}
