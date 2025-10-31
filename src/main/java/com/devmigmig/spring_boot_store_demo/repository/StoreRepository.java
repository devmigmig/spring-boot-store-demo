package com.devmigmig.spring_boot_store_demo.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.devmigmig.spring_boot_store_demo.pojo.Item;

@Repository
public class StoreRepository {
    private List<Item> items = new ArrayList<>();

    public Item getItem(int index) {
        return items.get(index);
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void updateItem(Item item, int index) {
        items.set(index, item);
    }
    
    public List<Item> getItems() {
        return items;
    }
}
