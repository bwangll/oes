package top.oes.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import top.oes.user.model.UserGroup;

/**
 * @author bwang
 * @version 1.0.0
 * @Description UserGroupRepository
 * @CreateTime 2021/9/21 5:30 下午
 */
@Repository
public interface UserGroupRepository extends JpaRepository<UserGroup, Long> {

    /**
     * 根据名称模糊查询用户组信息
     * @param userGroupName 用户组名称
     * @return 用户组信息
     */
    List<UserGroup> findByUserGroupNameContains(String userGroupName);
}
