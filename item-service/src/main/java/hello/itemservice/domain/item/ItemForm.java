package hello.itemservice.domain.item;

import lombok.Data;

@Data
public class ItemForm {

    public String itemName;

    public Integer price;

    public Integer quantity;
}
