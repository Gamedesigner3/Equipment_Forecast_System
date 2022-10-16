package gsq.booker.sys.service;


import gsq.booker.sys.controller.vo.cartVO;

import java.util.List;

public interface SysCartService {

    List<cartVO> getMyCartItems(Long userId);
}
