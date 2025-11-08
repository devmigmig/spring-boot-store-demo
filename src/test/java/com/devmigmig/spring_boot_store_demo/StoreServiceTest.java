package com.devmigmig.spring_boot_store_demo;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.text.SimpleDateFormat;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.devmigmig.spring_boot_store_demo.pojo.Item;
import com.devmigmig.spring_boot_store_demo.repository.StoreRepository;
import com.devmigmig.spring_boot_store_demo.service.StoreService;

@RunWith(MockitoJUnitRunner.class)
public class StoreServiceTest {

    @Mock
    private StoreRepository storeRepository;

    @InjectMocks
    private StoreService storeService;

    @Test
    public void getItemsFromRepoTest() throws Exception{
        when(storeRepository.getItems()).thenReturn(
            Arrays.asList(
                new Item("Cabinet", 25.0, 13.25, new SimpleDateFormat("yyyy-MM-dd").parse("2024-12-01"))
            )
        );

        List<Item> result = storeService.getItems();

        assertEquals("Cabinet", result.get(0).getName());

    }
    

}
