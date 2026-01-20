package com.antongulevich.pastebox.dto.request;

import lombok.Data;

@Data
public class PasteboxRequest {
    private String date;
    private long expirationTimeSeconds;
    private PublicStatus publicStatus;
}
