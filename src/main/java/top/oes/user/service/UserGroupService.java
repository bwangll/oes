package top.oes.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import top.oes.user.model.UserGroup;
import top.oes.user.repository.UserGroupRepository;

/**
 * @author bwang
 * @version 1.0.0
 * @Description UserGroupService
 * @CreateTime 2021/9/21 5:31 下午
 */
@Service
public class UserGroupService {

    @Autowired
    private UserGroupRepository userGroupRepository;

    /**
     * 获取所有的分组信息
     * 树型结构的构造由前端进行
     *
     * @return list
     */
    public List<UserGroup> findAll() {
        return userGroupRepository.findAll(Sort.by(Sort.Direction.ASC, "user_group_id"));
    }


    /**
     * 根据Id获取用户组
     *
     * @param userGroupId 用户组Id
     * @return 用户组信息
     */
    public UserGroup findById(Long userGroupId) {
        return userGroupRepository.findById(userGroupId).orElse(null);
    }

    /**
     * 更新用户组信息
     *
     * @param userGroup 用户组信息
     * @return 更新后的用户组信息
     */
    public UserGroup update(UserGroup userGroup) {
        return userGroupRepository.save(userGroup);
    }

    /**
     * 根据用户组Id删除用户组
     *
     * @param userGroupId 用户组Id
     */
    public void delete(Long userGroupId) {
        userGroupRepository.deleteById(userGroupId);
    }
}
