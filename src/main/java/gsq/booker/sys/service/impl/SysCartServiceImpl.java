package gsq.booker.sys.service.impl;

import gsq.booker.sys.common.Constants;
import gsq.booker.sys.controller.vo.cartVO;
import gsq.booker.sys.dao.CartMapper;
import gsq.booker.sys.dao.ParaMapper;
import gsq.booker.sys.entity.Cart;
import gsq.booker.sys.entity.Para;
import gsq.booker.sys.service.SysCartService;
import gsq.booker.sys.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class SysCartServiceImpl implements SysCartService {

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private ParaMapper paraMapper;

    @Override
    public List<cartVO> getMyCartItems(Long newBeeMallUserId){
        List<cartVO> cartVOS = new ArrayList<>();
        List<Cart> carts = cartMapper.selectByUserId(newBeeMallUserId, Constants.PARAM_CART_ITEM_TOTAL_NUMBER);
        if(!CollectionUtils.isEmpty(carts)){
            List<Integer> paramIds = carts.stream().map(Cart::getParamId).collect(Collectors.toList());
            List<Para> paras = paraMapper.selectByPrimaryKeys(paramIds);
            Map<Integer, Para> parasMap = new HashMap<>();
            if (!CollectionUtils.isEmpty(paras)) {
                parasMap = paras.stream().collect(Collectors.toMap(Para::getId, Function.identity(), (entity1, entity2) -> entity1));
            }
            for (Cart c : carts) {
                cartVO cartVO = new cartVO();
                BeanUtil.copyProperties(c, cartVO);
                if (parasMap.containsKey(c.getParamId())) {
                    Para temp = parasMap.get(c.getParamId());
                    cartVO.setId(temp.getId());
                    cartVO.setIdx1(temp.getIdx1());
                    cartVOS.add(cartVO);
                }
            }
        }
        return cartVOS;
    }
}
