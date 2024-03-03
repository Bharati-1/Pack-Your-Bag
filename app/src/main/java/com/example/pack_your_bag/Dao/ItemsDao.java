package com.example.pack_your_bag.Dao;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.pack_your_bag.Models.Items;

import java.util.List;

@Dao
public interface ItemsDao {
    @Insert(onConflict = REPLACE)
    void saveItems(Items items);

    @Query("select * from items where category=:category order by id asc")
    List<Items> getAll(String category);

    @Delete
    void delete(Items items);

    @Query("update items set checked=:checked where id=:id")
    void checkUncheck(int id,Boolean checked);

    @Query("select count(*) from items")
    Integer getItemsCount();

    @Query("delete from items where addedBy=:addedBy")
    Integer deleteAllSystemItem(String addedBy);
    @Query("delete from items where category=:category")
    Integer deleteAllByCategory(String category);

    @Query("delete from items where category=:category and addedBy=:addedBy")
    Integer deleteALlByCategoryAndAddedBy(String category,String addedBy);

    @Query("select * from items where checked=:checked order by id asc")
    List<Items> getAllSelected(Boolean checked);


}
