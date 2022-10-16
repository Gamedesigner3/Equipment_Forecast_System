package gsq.booker.sys.dao;


import gsq.booker.sys.entity.Para;
import gsq.booker.sys.util.PageQueryUtil;

import java.util.List;

public interface ParaMapper {

    /**
     *  修改记录
     * @param record
     * @return
     */
    int updateSelective(Para record);

    /**
     * 查询分页数据
     * @param pageUtil
     * @return
     */
    List<Para> findParaList(PageQueryUtil pageUtil);

    /**
     * 根据 ID 查询参数值
     * @param paraId
     * @return
     */
    Para selectByPrimaryKey(Integer paraId);

    List<Para> selectByPrimaryKeys(List<Integer> Id);
}
