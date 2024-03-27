package top.wayne06.generalbackend.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import top.wayne06.generalbackend.model.dto.user.UserQueryRequest;
import top.wayne06.generalbackend.model.entity.User;
import top.wayne06.generalbackend.model.vo.LoginUserVO;
import top.wayne06.generalbackend.model.vo.UserVO;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import me.chanjar.weixin.common.bean.WxOAuth2UserInfo;

/**
 * user service
 *
 * @author wayne06
 */
public interface UserService extends IService<User> {

    /**
     * user register
     *
     * @param userAccount
     * @param userPassword
     * @param checkPassword
     * @return
     */
    long userRegister(String userAccount, String userPassword, String checkPassword);

    /**
     * user login
     *
     * @param userAccount
     * @param userPassword
     * @param request
     * @return
     */
    LoginUserVO userLogin(String userAccount, String userPassword, HttpServletRequest request);

    /**
     * user login from Wexin open platform
     *
     * @param wxOAuth2UserInfo user info from Wexin
     * @param request
     * @return
     */
    LoginUserVO userLoginByMpOpen(WxOAuth2UserInfo wxOAuth2UserInfo, HttpServletRequest request);

    /**
     * get current login user
     *
     * @param request
     * @return
     */
    User getLoginUser(HttpServletRequest request);

    /**
     * get current login user（允许未登录）
     *
     * @param request
     * @return
     */
    User getLoginUserPermitNull(HttpServletRequest request);

    /**
     * is admin by request
     *
     * @param request
     * @return
     */
    boolean isAdmin(HttpServletRequest request);

    /**
     * is admin by user
     *
     * @param user
     * @return
     */
    boolean isAdmin(User user);

    /**
     * user logout
     *
     * @param request
     * @return
     */
    boolean userLogout(HttpServletRequest request);

    /**
     * get login user VO
     *
     * @return
     */
    LoginUserVO getLoginUserVO(User user);

    /**
     * get user VO
     *
     * @param user
     * @return
     */
    UserVO getUserVO(User user);

    /**
     * get login user VO list
     *
     * @param userList
     * @return
     */
    List<UserVO> getUserVO(List<User> userList);

    /**
     * get query wrapper
     *
     * @param userQueryRequest
     * @return
     */
    QueryWrapper<User> getQueryWrapper(UserQueryRequest userQueryRequest);

}
