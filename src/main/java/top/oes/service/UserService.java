package top.oes.service;

import java.sql.Timestamp;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;
import top.oes.model.User;
import top.oes.repository.UserRepository;
import top.oes.security.service.UserDetailsServiceImpl;
import top.oes.utils.JwtTokenUtil;

/**
 * @author bwang
 * @version 1.0.0
 * @Description UserService
 * @CreateTime 2021/9/16 10:39 下午
 */
@Log4j2
@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    /**
     * 登陆方法
     *
     * @param loginName 登陆名
     * @param password  密码
     * @return token
     */
    public String login(String loginName, String password) {
        String token = null;
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(loginName);
            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                throw new BadCredentialsException("密码不正确");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
            ((User) userDetails).setLastLogin(new Timestamp(System.currentTimeMillis()));
            userRepository.save((User) userDetails);
        } catch (AuthenticationException e) {
            log.warn("登录异常:{}", e.getMessage());
        }
        return token;
    }

    /**
     * 注册用户
     *
     * @param user 用户信息
     * @return true：成功  false：失败
     */
    public String register(User user) {
        //检查登陆名是否重复
        User repeatUser = userRepository.findUserByLoginName(user.getLoginName());
        if (Objects.nonNull(repeatUser)) {
            return "登陆名重复，请重新设置";
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        user.setCreateDate(new Timestamp(System.currentTimeMillis()));
        user.setUserEnable(true);
        userRepository.save(user);
        return null;
    }

    /**
     * 更新用户
     * @param user user实体
     * @return 更新后的user实体
     */
    public User update(User user) {
        user.setModifyDate(new Timestamp(System.currentTimeMillis()));
        return userRepository.save(user);
    }

    /**
     * 根据Id删除用户
     * @param id 用户id
     */
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
