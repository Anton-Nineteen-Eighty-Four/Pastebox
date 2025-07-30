package com.antongulevich.pastebox.api.request;

import lombok.Data;

@Data
public class PasteBoxRequest {
    private String date;
    private long expirationTimeSeconds;
    private PublicStatus publicStatus;
}
