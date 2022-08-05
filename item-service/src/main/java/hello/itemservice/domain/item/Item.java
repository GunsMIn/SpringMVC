package hello.itemservice.domain.item;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

//@Data를 쓰는 것은 위험한 경우가있다.
/*@Getter
@Setter*/
@Data
public class Item {

    private Long id;
    private String itemName;
    private Integer price; // Integer로 쓴 이유는 null로 들어갈 수도 있다는 뜻
    private Integer quantity;

    public Item(){}

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
