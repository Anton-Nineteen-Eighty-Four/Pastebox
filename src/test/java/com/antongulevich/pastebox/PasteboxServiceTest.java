package com.antongulevich.pastebox;

import com.antongulevich.pastebox.dto.response.PasteboxResponse;
import com.antongulevich.pastebox.exception.ClassNotFoundEntityException;
import com.antongulevich.pastebox.repository.PasteboxRepository;
import com.antongulevich.pastebox.model.PasteboxEntity;
import com.antongulevich.pastebox.service.PasteboxService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PasteboxServiceTest {
    @Autowired
    private PasteboxService pasteboxService;

    @MockBean
    private PasteboxRepository pasteboxRepository;


    @Test
    public void notExistHash(){
        when(pasteboxRepository.getByHash(anyString())).thenThrow(ClassNotFoundEntityException.class);
        assertThrows(ClassNotFoundEntityException.class, () -> pasteboxService.getByHash("f3rv2dws"));
    }

    @Test
    public void getExistHash(){
        PasteboxEntity entity = new PasteboxEntity();
        entity.setHash("1");
        entity.setDate("11");
        entity.setPublic(true);

        when(pasteboxRepository.getByHash("1")).thenReturn(entity);

        PasteboxResponse expected = new PasteboxResponse("11",true);
        PasteboxResponse actuality = pasteboxService.getByHash("1");

        assertEquals(expected, actuality);
    }
}
