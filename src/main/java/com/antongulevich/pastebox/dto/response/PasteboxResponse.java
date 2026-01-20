package com.antongulevich.pastebox.dto.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PasteboxResponse {
    private final String date;
    private final boolean isPublic;
}
