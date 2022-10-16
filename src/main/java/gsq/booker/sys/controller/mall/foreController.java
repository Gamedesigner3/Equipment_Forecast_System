package gsq.booker.sys.controller.mall;


import gsq.booker.sys.common.Constants;
import gsq.booker.sys.common.ForeSysException;
import gsq.booker.sys.controller.vo.SysUserVO;
import gsq.booker.sys.controller.vo.cartVO;
import gsq.booker.sys.service.SysCartService;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class foreController {

    @Resource
    public SysCartService sysCartService;

    @GetMapping("/forecast")
    public String ListPage(HttpServletRequest request, HttpSession httpSession){
        SysUserVO user = (SysUserVO) httpSession.getAttribute(Constants.SYS_USER_SESSION_KEY);
        int itemsTotal = 0;
        int priceTotal = 0;
        List<cartVO> paras = sysCartService.getMyCartItems(user.getUserId());
        if(!CollectionUtils.isEmpty(paras)){
            itemsTotal = (int)paras.stream().mapToInt(cartVO::getId).count();
            if (itemsTotal < 1) {
                ForeSysException.fail("购物项不能为空");
            }
            for(cartVO c : paras){
                priceTotal += c.getParamCount() * c.getIdx1();
            }
            if (priceTotal < 1) {
                ForeSysException.fail("购物项价格异常");
            }
        }
        request.setAttribute("itemsTotal", itemsTotal);
        request.setAttribute("priceTotal", priceTotal);
        request.setAttribute("paras", paras);
        return "sys/forecast";
    }

}
