package top.oes.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cn.hutool.core.util.StrUtil;
import top.oes.common.CommonResult;
import top.oes.model.User;
import top.oes.model.dto.LoginParam;
import top.oes.service.UserService;

/**
 * @author bwang
 * @version 1.0.0
 * @Description UserController
 * @CreateTime 2021/9/16 10:39 下午
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @PostMapping("/login")
    public CommonResult<?> login(@RequestBody LoginParam loginParam) {
        String token = userService.login(loginParam.getLoginName(), loginParam.getPassword());
        if (StrUtil.isEmpty(token)) {
            return CommonResult.validateFailed("用户名或者密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);
    }

    @PostMapping("/register")
    public CommonResult<?> register(@Valid @RequestBody User user, BindingResult result) {
        if (result.hasErrors()) {
            return CommonResult.failed(result.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList()).toString());
        }
        String register = userService.register(user);
        if (StrUtil.isNotBlank(register)) {
            return CommonResult.failed(register);
        }
        return CommonResult.success(null);
    }

    @PutMapping("/user")
    @RolesAllowed({"ROLE_NONE", "ROLE_USER"})
    public CommonResult<?> update(@RequestBody User user) {
        return CommonResult.success(userService.update(user));
    }

    @RolesAllowed({"ROLE_ADMIN"})
    @DeleteMapping("/user/{id}")
    public CommonResult<?> delete(@PathVariable Long id) {
        userService.delete(id);
        return CommonResult.success(null);
    }
}
