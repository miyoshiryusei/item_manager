package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Item;
import com.example.form.ItemForm;
import com.example.repository.ItemRepository;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }
    
    public List<Item> findAll() {
        return this.itemRepository.findAll();
    }
    
 // データ保存用のメソッド
    public Item save(ItemForm itemForm) {
        // Entityクラスのインスタンスを生成
        Item item = new Item();
        // フィールドのセットを行う
        item.setName(itemForm.getName());
        item.setPrice(itemForm.getPrice());
        // repository.saveメソッドを利用してデータの保存を行う
        return this.itemRepository.save(item);
    }
    
  //IDカラムを利用してデータ検索
    public Item findById(Integer id) {
        Optional<Item> optionalItem = this.itemRepository.findById(id);
        Item item  = optionalItem.get();
        return item;
    }
}