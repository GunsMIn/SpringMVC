package hello.itemservice.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach(){
        itemRepository.clearStore();
    }

    @Test
    public void save() throws Exception {
        //given
        Item item = new Item("ITEMA",10000,10);

        //when
        Item item1 = itemRepository.save(item);
        Item finditem = itemRepository.findOne(item1.getId());

        //then
        Assertions.assertThat(item1).isEqualTo(finditem);
    }

    @Test
    public void finaAll() throws Exception {
        //given
        Item itemA = new Item("itemA", 10000, 10);
        Item itemb = new Item("itemb", 12000, 12);
        itemRepository.save(itemA);
        itemRepository.save(itemb);
        //when
        List<Item> all = itemRepository.findAll();

        //then
        Assertions.assertThat(all.size()).isEqualTo(2);
    }

    @Test
    public void update() throws Exception {
        //given
        Item item = new Item("피아노", 2000000, 30);
        itemRepository.save(item);
        Long id = item.getId();
        //when
        Item updateItem = new Item("기타", 1000000, 1000);
        itemRepository.update(id,updateItem);
        Item findItem = itemRepository.findOne(id);
        //then
        Assertions.assertThat(findItem.getItemName()).isEqualTo(item.getItemName());

    }



}
