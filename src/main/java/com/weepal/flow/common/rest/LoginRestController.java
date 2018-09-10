package com.weepal.flow.common.rest;

import com.weepal.flow.common.utils.ResponseMessage;
import com.weepal.flow.common.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 * <b>flow</b> is 登录
 * </p>
 *
 * @author Bin
 * @version $Id: flow 62440 2018-08-06 17:40:15Z Bin $
 * @since 2018年08月06日
 **/
@RestController
@Api(description = "登录接口")
public class LoginRestController {

    private Logger log = LoggerFactory.getLogger(LoginRestController.class);

    @ApiOperation(value = "登录")
    @RequestMapping(value = "/wplogin",method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage loginRest(HttpServletRequest request, HttpServletResponse response,
                                     @RequestParam("userId")String userId,
                                     @RequestParam("password")String password) {

        String msg = null;
        Subject currentUser = SecurityUtils.getSubject();
        if (!currentUser.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(userId, password);
            token.setRememberMe(true);
            try {
                currentUser.login(token);
                response.sendRedirect("/index.html");
            } catch (UnknownAccountException uae) {
                msg = "There is no user with username of " + token.getPrincipal();
                log.info(msg);
                return Result.error(msg);
            } catch (IncorrectCredentialsException ice) {
                msg = "Password for account " + token.getPrincipal() + " was incorrect!";
                log.info(msg);
                return Result.error(msg);
            } catch (LockedAccountException lae) {
                msg = "The account for username " + token.getPrincipal() + " is locked.  " +
                        "Please contact your administrator to unlock it.";
                log.info(msg);
                return Result.error(msg);
            } catch (AuthenticationException ae) {
                msg = "The account for authentication " + token.getPrincipal() + " is unAuthentication.  " +
                        "Please contact your administrator to have it.";
                log.info(msg);
                return Result.error(msg);
            }catch (IOException e){
                msg = "IOException";
                log.info(msg);
                return Result.error(msg);
            }
        }

        return Result.success();
    }

}
