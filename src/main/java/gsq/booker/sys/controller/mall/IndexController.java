package gsq.booker.sys.controller.mall;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {


    @GetMapping({"/index", "/", "/index.html"})
        public String indexPage(HttpServletRequest request) {
        return "sys/index";
    }

}