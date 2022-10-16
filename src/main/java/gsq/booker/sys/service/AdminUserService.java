package gsq.booker.sys.service;


import gsq.booker.sys.entity.AdminUser;
import gsq.booker.sys.entity.Para;
import gsq.booker.sys.util.PageQueryUtil;
import gsq.booker.sys.util.PageResult;

public interface AdminUserService {

    /**
     * 查询后台系统
     * @param pageUtil
     * @return
     */
    PageResult getParaPage(PageQueryUtil pageUtil);

    /**
     * 修改一条参数记录
     * @param para
     * @return
     */
    String updateParams(Para para);

    /**
     * 根据id 查找实体
     * @param id
     * @return
     */
    Para getParaById(Integer id);


    AdminUser login(String userName, String password);

    /**
     * 获取用户信息
     *
     * @param loginUserId
     * @return
     */
    AdminUser getUserDetailById(Integer loginUserId);

    /**
     * 修改当前登录用户的密码
     *
     * @param loginUserId
     * @param originalPassword
     * @param newPassword
     * @return
     */
    Boolean updatePassword(Integer loginUserId, String originalPassword, String newPassword);

    /**
     * 修改当前登录用户的名称信息
     *
     * @param loginUserId
     * @param loginUserName
     * @param nickName
     * @return
     */
    Boolean updateName(Integer loginUserId, String loginUserName, String nickName);


}
