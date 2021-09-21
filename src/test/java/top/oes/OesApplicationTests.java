package top.oes;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import top.oes.user.model.User;
import top.oes.user.repository.RoleRepository;
import top.oes.user.repository.UserRepository;

@SpringBootTest
class OesApplicationTests {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void contextLoads() {

        List<User> all = userRepository.findAll();
        all.forEach(System.out::println);
        //
        //List<Role> roles = roleRepository.findAll();
        //roles.forEach(System.out::println);
        //
    }

}
