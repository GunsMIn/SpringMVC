package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;
import org.thymeleaf.processor.templateboundaries.ITemplateBoundariesProcessor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {

    private static final Map<Long, Item> store = new HashMap<>();
    private static Long sequence = 0L;

    //상품 저장 로직
    public Item save(Item item){
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    //아이템 하나 조회
    public Item findOne(Long id){
        return store.get(id);
    }

    //아이템 목롣 조회
    public List<Item> findAll(){
        return new ArrayList<>(store.values());
    }

    public void update(Long itemId, Item updateParam){
        Item findItem = findOne(itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setQuantity(updateParam.getQuantity());
        findItem.setPrice(updateParam.getPrice());
    }


    public void clearStore(){
        store.clear();
    }
}
