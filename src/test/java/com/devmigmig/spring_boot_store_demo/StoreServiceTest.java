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

import com.devmigmig.spring_boot_store_demo.constant.Constants;
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
                new Item("Cabinet", 25.0, 13.25, new SimpleDateFormat("yyyy-MM-dd").parse("2024-12-01")),
                new Item("Desktop", 128.33, 35.78, new SimpleDateFormat("yyyy-MM-dd").parse("2023-05-02"))
            )
        );

        List<Item> result = storeService.getItems();

        assertEquals(2, result.size());
       
        Item firstItem = result.get(0);
        assertEquals("Cabinet", firstItem.getName());
        assertEquals(25.0, firstItem.getPrice(), 0.001);
        assertEquals(13.25, firstItem.getDiscount(), 0.001);
        assertEquals(new SimpleDateFormat("yyyy-MM-dd").parse("2024-12-01"), firstItem.getDate());

        Item secondItem = result.get(1);
        assertEquals("Desktop", secondItem.getName());
        assertEquals(128.33, secondItem.getPrice(), 0.001);
        assertEquals(35.78, secondItem.getDiscount(), 0.001);
        assertEquals(new SimpleDateFormat("yyyy-MM-dd").parse("2023-05-02"), secondItem.getDate());
    }

    @Test
    public void itemIndexTest() throws Exception{
        Item item = new Item("Cabinet", 25.0, 13.25, new SimpleDateFormat("yyyy-MM-dd").parse("2024-12-01"));

        when(storeRepository.getItems()).thenReturn(
            Arrays.asList(item)
        );

        when(storeRepository.getItem(0)).thenReturn(
            item
        );

        int valid = storeService.getIndexFromId(
            item.getId()
        );

        int notFound = storeService.getIndexFromId("123");

        assertEquals(0, valid);
        assertEquals(Constants.NOT_FOUND, notFound);
    }
    


}
