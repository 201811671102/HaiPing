package pre.ysl.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import pre.ysl.pojo.User;
import pre.ysl.redis.RedisUtil;
import pre.ysl.service.URService;
import pre.ysl.service.UserService;


/**
 * 自定义Realm
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    @Autowired
    private URService urService;
    @Autowired
    private RedisUtil redisUtil;
    /**
     * 执行授权逻辑
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Subject subject= SecurityUtils.getSubject();
        return info;
    }

    /**
     * 执行认证逻辑
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        User user = new User();
        try {
            if (redisUtil.hasKey("haiping"+token.getUsername())){
                user = (User) redisUtil.get("haiping"+token.getUsername());
            }else{
                user = userService.selectByAccount(token.getUsername());
                redisUtil.set("haiping"+token.getUsername(),user);
            }
            if (user==null){
                return null;
            }
        }catch (NullPointerException e){
            return null;
        }
        return new SimpleAuthenticationInfo(user,user.getUpassword(),getName());
    }
}
