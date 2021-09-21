package top.oes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import top.oes.model.Role;

/**
 * @author bwang
 * @version 1.0.0
 * @Description RoleRepository
 * @CreateTime 2021/9/12 9:02 下午
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
