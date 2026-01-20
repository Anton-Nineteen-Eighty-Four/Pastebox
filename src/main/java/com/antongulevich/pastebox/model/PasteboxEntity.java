package com.antongulevich.pastebox.model;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class PasteboxEntity {
    private int id;
    private String date;
    private String hash;
    private LocalDateTime lifetime;
    private boolean isPublic;
}
