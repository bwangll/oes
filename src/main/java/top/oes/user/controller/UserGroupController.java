package top.oes.user.controller;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import top.oes.common.CommonResult;
import top.oes.user.model.UserGroup;
import top.oes.user.service.UserGroupService;

/**
 * @author bwang
 * @version 1.0.0
 * @Description UserGroupController 用户组访问接口
 * @CreateTime 2021/9/21 5:29 下午
 */
@RestController
public class UserGroupController {

    @Autowired
    private UserGroupService userGroupService;

    /**
     * 查询所有的组，只有root用户才可以查询所有的组，其他用户只可以查询当前组和其子组
     *
     * @return list 用户组信息
     */
    @RolesAllowed({"ROLE_ADMIN"})
    @GetMapping("/userGroup")
    public CommonResult<?> findAll() {
        return CommonResult.success(userGroupService.findAll());
    }

    /**
     * 根据Id查询用户组
     *
     * @param userGroupId id
     * @return 用户组信息
     */
    @RolesAllowed({"ROLE_USER"})
    @GetMapping("/userGroup/{userGroupId}")
    public CommonResult<?> findById(@PathVariable Long userGroupId) {
        return CommonResult.success(userGroupService.findById(userGroupId));
    }

    /**
     * 新的用户组信息
     *
     * @param userGroup 要更新的用户组信息
     * @return 用户组信息
     */
    @RolesAllowed({"ROLE_USER"})
    @PutMapping("/userGroup")
    public CommonResult<?> update(@RequestBody UserGroup userGroup) {
        return CommonResult.success(userGroupService.update(userGroup));
    }

    /**
     * 根据Id删除用户组
     *
     * @param userGroupId id
     * @return sucess
     */
    @RolesAllowed({"ROLE_ADMIN"})
    @DeleteMapping("/userGroup/{userGroupId}")
    public CommonResult<?> deleteById(@PathVariable Long userGroupId) {
        userGroupService.delete(userGroupId);
        return CommonResult.success(null);
    }
}
