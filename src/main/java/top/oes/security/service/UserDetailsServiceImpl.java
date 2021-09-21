package top.oes.security.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import top.oes.model.User;
import top.oes.repository.UserRepository;

/**
 * @author bwang
 * @version 1.0.0
 * @Description UserDetailsServiceImpl
 * @CreateTime 2021/9/16 10:09 下午
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String loginName) throws UsernameNotFoundException {
        User user = userRepository.findUserByLoginName(loginName);
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("用户不存在");
        }
        return user;
    }
}
