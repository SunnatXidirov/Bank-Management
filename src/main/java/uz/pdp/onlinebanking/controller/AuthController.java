package uz.pdp.onlinebanking.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uz.pdp.onlinebanking.payload.RestToken;
import uz.pdp.onlinebanking.payload.SingIn;
import uz.pdp.onlinebanking.payload.SingUp;
import uz.pdp.onlinebanking.service.AuthService;

@Controller
@RequestMapping("/api/auth/")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("login")
    public HttpEntity<?> signIn(
            @RequestBody SingIn singIn
    ) {
        RestToken restToken = authService.signIn(singIn);
        return ResponseEntity.status(restToken != null ? 200 : 401).body(
                restToken
        );
    }

    @PostMapping("signUp")
    public HttpEntity<?> signUp(
            @RequestBody SingUp singUp
    ) {
        RestToken restToken = authService.signUp(singUp);
        return ResponseEntity.status(restToken != null ? 200 : 401).body(
                restToken
        );
    }
}
