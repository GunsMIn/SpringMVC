package hello.servlet.web.frontcontroller;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class ModelView {
    //view의 논리적 이름
    private String viewName;

    //String으로 키 값을 넣어줄것이도 value로 객체를 넣어 줄 것이다.
    private Map<String, Object> model = new HashMap<>();

    public ModelView(String viewName){
        this.viewName = viewName;
    }
}
