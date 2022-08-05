package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
@Slf4j
public class BasicItemController {

    private final ItemRepository itemRepository;

    //상품 목록 페이지 이동
    @GetMapping
    public String items(Model model){
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }
    //상품 상세보기 페이지로 이동
    @GetMapping("/{itemId}")
    public String item(@PathVariable Long itemId,Model model){
        Item item = itemRepository.findOne(itemId);
        model.addAttribute("item",item);
        return "basic/item";
    }

    @GetMapping("/add")
    public String addItem(){
        return "basic/addForm";
    }

    //상품 등록
    //@PostMapping("/add")
    public String PostaddItem(@RequestParam String itemName,@RequestParam int price, @RequestParam int quantity, Model model){
        Item item = new Item();
        item.setItemName(itemName);
        item.setQuantity(quantity);
        item.setPrice(price);
        itemRepository.save(item);
        model.addAttribute("item", item);
        log.info("item={}",item);
        return "basic/item";
    }

    //상품 등록
    @PostMapping("/add")
    public String PostAddItemModel(@ModelAttribute("item") Item item, Model model){
        /*
        * @ModelAttribute의 용도
        * 1. 모델 객체를 만들어주고
        * 2. model.addAttribute까지 해줄 수 있다
        * @ModelAttribute("item") 키 값 item
        * 만약 name속성을 안적어준다면 첫글자만 소문자로 담기게된다
        * */
         itemRepository.save(item);
        //model.addAttribute("item", item);
        return "redirect:/basic/item"; // 새로 고침을 예방하기위해서
        /*웹 브라우저의 새로 고침은 마지막에 서버에 전송한 데이터를 다시 전송한다.
        새로 고침 문제를 해결하려면 상품 저장 후에 뷰 템플릿으로 이동하는 것이 아니라, 상품 상세 화면으로
        리다이렉트를 호출해주면 된다.
        웹 브라우저는 리다이렉트의 영향으로 상품 저장 후에 실제 상품 상세 화면으로 다시 이동한다. 따라서
        마지막에 호출한 내용이 상품 상세 화면인 GET /items/{id} 가 되는 것이다.
        이후 새로고침을 해도 상품 상세 화면으로 이동하게 되므로 새로 고침 문제를 해결할 수 있다.*/

    }
    /*1.@ModelAttribute - 요청 파라미터 처리
    @ModelAttribute 는 Item 객체를 생성하고, 요청 파라미터의 값을 프로퍼티 접근법(setXxx)으로
    입력해준다.
    2.@ModelAttribute - Model 추가
    @ModelAttribute 는 중요한 한가지 기능이 더 있는데, 바로 모델(Model)에 @ModelAttribute 로
    지정한 객체를 자동으로 넣어준다. 지금 코드를 보면 model.addAttribute("item", item) 가 주석처리
    되어 있어도 잘 동작하는 것을 확인할 수 있다.
    모델에 데이터를 담을 때는 이름이 필요하다. 이름은 @ModelAttribute 에 지정한 name(value) 속성을
    사용한다. 만약 다음과 같이 @ModelAttribute 의 이름을 다르게 지정하면 다른 이름으로 모델에
    포함된다.
    @ModelAttribute("hello") Item item 이름을 hello 로 지정
    model.addAttribute("hello", item); 모델에 hello 이름으로 저장*/



    //상품 수정 폼페이지 이동
    @GetMapping("/{itemId}/edit")
    public String getUpdate(@PathVariable Long itemId,Model model){
        Item item = itemRepository.findOne(itemId);
        model.addAttribute("item",item);
        return "/basic/editForm";
    }
    @PostMapping("/{itemId}/edit")
    public String update(@PathVariable Long itemId, @ModelAttribute Item updateItem){
        //@ModelAttribute에서 name를 생략하면 앞글자가 소문자인 item으로 된다.
        itemRepository.update(itemId,updateItem);
        return "redirect:/basic/items/{itemId}";
    }



    @PostConstruct
    public void init() {
        itemRepository.save(new Item("testA", 10000, 10));
        itemRepository.save(new Item("testB", 20000, 20));
        itemRepository.save(new Item("testC", 30000, 30));
    }
}
