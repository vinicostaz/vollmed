package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.user.AuthenticationData;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    private final AuthenticationManager manager;

    public AuthenticationController(AuthenticationManager manager) {
        this.manager = manager;
    }

    @PostMapping
    public ResponseEntity<?> executeLogin(@RequestBody @Valid AuthenticationData data) {
        var token = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var authentication = manager.authenticate(token);

        return ResponseEntity.ok().build();
    }
}