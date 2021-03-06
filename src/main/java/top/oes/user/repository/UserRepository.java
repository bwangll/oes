package top.oes.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import top.oes.user.model.User;

/**
 * @author bwang
 * @version 1.0.0
 * @Description UserRepository
 * @CreateTime 2021/9/12 9:01 下午
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * 根据用户名查询用户信息
     * @param schoolNumber schoolNumber
     * @return user
     */
    User findUserBySchoolNumber(String schoolNumber);
}
