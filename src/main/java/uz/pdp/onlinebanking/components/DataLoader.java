package uz.pdp.onlinebanking.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.pdp.onlinebanking.repository.RoleRepository;
import uz.pdp.onlinebanking.repository.UserRepository;
import uz.pdp.onlinebanking.security.JwtProvider;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtProvider provider;

    @Override
    public void run(String... args) throws Exception {
//        for (RoleName value : RoleName.values()) {
//            roleRepository.save(new Roles(value));
//        }
//
//
//        Users user1 = new Users();
//        user1.setLogin("admin");
//        user1.setFullName("Abdulaziz Shakhabutdinov");
//        user1.setPhoneNumber("+998974120801");
//        user1.setPassword(passwordEncoder.encode("root123"));
//        user1.setActive(true);
//        user1.setRoles(
//                new HashSet<>(
//                        Collections.singletonList(
//                                roleRepository
//                                        .findByRoleName(
//                                                RoleName.ROLE_ADMIN))));
//        Users user2 = new Users();
//        user2.setLogin("user");
//        user2.setFullName("Abdulmalik");
//        user2.setPhoneNumber("+9989836308010801");
//        user2.setPassword(passwordEncoder.encode("root123"));
//        user2.setActive(true);
//        user2.setRoles(new HashSet<>(Collections.singletonList(roleRepository.findByRoleName(RoleName.ROLE_USER))));
//
//        userRepository.save(user1);
//        userRepository.save(user2);
//
    }
}
