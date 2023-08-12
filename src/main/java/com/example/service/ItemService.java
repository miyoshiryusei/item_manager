package com.example.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
    
 // データ更新用のメソッド
    public Item update(Integer id, ItemForm itemForm) {
        // データ１件分のEntityクラスを取得
        Item item = this.findById(id);
        // Formクラスのフィールドをセットする
        item.setName(itemForm.getName());
        item.setPrice(itemForm.getPrice());
        // repository.saveメソッドを利用してデータの保存を行う
        return this.itemRepository.save(item);
    }
    
 // データ削除用のメソッド
    /*public void delete(Integer id) {
        this.itemRepository.deleteById(id);
    }
    */
    
    public List<Item> findByDeletedAtIsNull() {
        return this.itemRepository.findByDeletedAtIsNull();
    }
    
    
 // saveメソッドはEntityを返り値にもつため、返り値の型を変更
    public Item delete(Integer id) {
        // idから該当のEntityクラスを取得します
        Item item = this.findById(id);
        // EntityクラスのdeletedAtフィールドを現在日時で上書きします
        item.setDeletedAt(LocalDateTime.now());
        // 更新処理
        return this.itemRepository.save(item);
    }
}