package uz.pdp.onlinebanking.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.onlinebanking.entity.Users;
import uz.pdp.onlinebanking.entity.enums.RoleName;
import uz.pdp.onlinebanking.payload.RestToken;
import uz.pdp.onlinebanking.payload.SingIn;
import uz.pdp.onlinebanking.payload.SingUp;
import uz.pdp.onlinebanking.repository.RoleRepository;
import uz.pdp.onlinebanking.repository.UserRepository;
import uz.pdp.onlinebanking.security.JwtProvider;

import java.util.Collections;
import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    public RestToken signIn(SingIn singIn) {
        try {
            Users user = userRepository.findByLogin(singIn.getLogin());
            boolean matches = passwordEncoder.matches(singIn.getPassword(), user.getPassword());
            if (matches && user.isActive()) {
                return new RestToken(jwtProvider.generateJwtToken(user.getId()),
                        jwtProvider.generateRefreshJwtToken(user.getId()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public RestToken signUp(SingUp singUp) {
        Users users = new Users();
        users.setLogin(singUp.getLogin());
        users.setPhoneNumber(singUp.getPhoneNumber());
        users.setFullName(singUp.getFullName());
        users.setPassword(passwordEncoder.encode(singUp.getPassword()));
        users.setRoles(new HashSet<>(
                        Collections.singletonList(
                                roleRepository
                                        .findByRoleName(
                                                RoleName.ROLE_USER))));
        userRepository.save(users);
        Users user = userRepository.findByLogin(users.getLogin());
        return new RestToken(jwtProvider.generateJwtToken(user.getId()),
                jwtProvider.generateRefreshJwtToken(users.getId()));
    }
}
