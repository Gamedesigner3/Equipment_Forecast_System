package gsq.booker.sys.service.impl;

import gsq.booker.sys.common.Constants;
import gsq.booker.sys.common.ServiceResultEnum;
import gsq.booker.sys.controller.vo.SysUserVO;
import gsq.booker.sys.dao.SysUserMapper;
import gsq.booker.sys.entity.SysUser;
import gsq.booker.sys.service.SysUserService;
import gsq.booker.sys.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public PageResult getNewBeeMallUsersPage(PageQueryUtil pageUtil) {
        List<SysUser> mallUsers = sysUserMapper.findMallUserList(pageUtil);
        int total = sysUserMapper.getTotalMallUsers(pageUtil);
        PageResult pageResult = new PageResult(mallUsers, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

    @Override
    public String register(String loginName, String password) {
        if (sysUserMapper.selectByLoginName(loginName) != null) {
            return ServiceResultEnum.SAME_LOGIN_NAME_EXIST.getResult();
        }
        SysUser registerUser = new SysUser();
        registerUser.setLoginName(loginName);
        registerUser.setNickName(loginName);
        String passwordMD5 = MD5Util.MD5Encode(password, "UTF-8");
        registerUser.setPasswordMd5(passwordMD5);
        if (sysUserMapper.insertSelective(registerUser) > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    @Override
    public String login(String loginName, String passwordMD5, HttpSession httpSession) {
        SysUser user = sysUserMapper.selectByLoginNameAndPasswd(loginName, passwordMD5);
        if (user != null && httpSession != null) {
            if (user.getLockedFlag() == 1) {
                return ServiceResultEnum.LOGIN_USER_LOCKED.getResult();
            }
            //昵称太长 影响页面展示
            if (user.getNickName() != null && user.getNickName().length() > 7) {
                String tempNickName = user.getNickName().substring(0, 7) + "..";
                user.setNickName(tempNickName);
            }
            SysUserVO sysUserVO = new SysUserVO();
            BeanUtil.copyProperties(user, sysUserVO);
            httpSession.setAttribute(Constants.SYS_USER_SESSION_KEY, sysUserVO);
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.LOGIN_ERROR.getResult();
    }

    @Override
    public SysUserVO updateUserInfo(SysUser sysUser, HttpSession httpSession) {
        SysUserVO userTemp = (SysUserVO) httpSession.getAttribute(Constants.SYS_USER_SESSION_KEY);
        SysUser userFromDB = sysUserMapper.selectByPrimaryKey(userTemp.getUserId());
        if (userFromDB != null) {
            if (!StringUtils.isEmpty(sysUser.getNickName())) {
                userFromDB.setNickName(SysUtils.cleanString(sysUser.getNickName()));
            }
            if (!StringUtils.isEmpty(sysUser.getAddress())) {
                userFromDB.setAddress(SysUtils.cleanString(sysUser.getAddress()));
            }
            if (!StringUtils.isEmpty(sysUser.getIntroduceSign())) {
                userFromDB.setIntroduceSign(SysUtils.cleanString(sysUser.getIntroduceSign()));
            }
            if (sysUserMapper.updateByPrimaryKeySelective(userFromDB) > 0) {
                SysUserVO sysUserVO = new SysUserVO();
                userFromDB = sysUserMapper.selectByPrimaryKey(sysUser.getUserId());
                BeanUtil.copyProperties(userFromDB, sysUserVO);
                httpSession.setAttribute(Constants.SYS_USER_SESSION_KEY, sysUserVO);
                return sysUserVO;
            }
        }
        return null;
    }

    @Override
    public Boolean lockUsers(Integer[] ids, int lockStatus) {
        if (ids.length < 1) {
            return false;
        }
        return sysUserMapper.lockUserBatch(ids, lockStatus) > 0;
    }
}
