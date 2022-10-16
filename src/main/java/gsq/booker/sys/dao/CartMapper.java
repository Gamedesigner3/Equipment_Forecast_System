package gsq.booker.sys.dao;


import gsq.booker.sys.entity.Cart;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CartMapper {
    /**
     * 根据 userId 和 goodsId 查询记录
     * @param userId
     * @param paramId
     * @return
     */
    Cart selectByUserIdAndParamId(@Param("userId") Long userId, @Param("paramId") Integer paramId);

    /**
     * 根据userId查询当前用户已添加了多少条记录
     *
     * @param newBeeMallUserId
     * @return
     */
    int selectCountByUserId(Long newBeeMallUserId);

    List<Cart> selectByUserId(@Param("userId") Long userId, @Param("number") int number);

}
