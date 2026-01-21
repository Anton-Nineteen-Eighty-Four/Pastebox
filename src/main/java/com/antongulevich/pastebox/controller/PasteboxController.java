package com.antongulevich.pastebox.controller;

import com.antongulevich.pastebox.dto.request.PasteboxRequest;
import com.antongulevich.pastebox.dto.response.PasteboxResponse;
import com.antongulevich.pastebox.dto.response.PasteboxUrlResponse;
import com.antongulevich.pastebox.service.PasteboxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class PasteboxController {

    @Autowired
    private PasteboxService pasteboxService;

    @GetMapping("/{hash}")
    public PasteboxResponse getByHash(@PathVariable String hash){
        return pasteboxService.getByHash(hash);
    }

    @GetMapping("/")
    public Collection<PasteboxResponse> getPublicPasteList(){
        return pasteboxService.getFirstPublicPastebox();
    }

    @PostMapping("/")
    public PasteboxUrlResponse add(@RequestBody PasteboxRequest request){
        return pasteboxService.create(request);
    }
}
