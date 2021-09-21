package top.oes.model.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

/**
 * @author bwang
 * @version 1.0.0
 * @Description LoginParam 登陆参数实体类
 * @CreateTime 2021/9/16 10:43 下午
 */
@Getter
@Setter
public class LoginParam {

    @NotEmpty(message = "用户名不可为空")
    private String loginName;

    @NotEmpty(message = "密码不可为空")
    private String password;
}
