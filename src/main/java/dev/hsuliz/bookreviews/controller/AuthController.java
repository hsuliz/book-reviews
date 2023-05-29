package dev.hsuliz.bookreviews.controller;

import dev.hsuliz.bookreviews.dto.SingleMessageResponse;
import dev.hsuliz.bookreviews.dto.UserLoginRequest;
import dev.hsuliz.bookreviews.exception.UserAlreadyExistException;
import dev.hsuliz.bookreviews.model.User;
import dev.hsuliz.bookreviews.service.TokenService;
import dev.hsuliz.bookreviews.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final TokenService tokenService;
    private final UserService userService;

    @PostMapping("/signup")
    public Mono<Void> signup(@RequestBody UserLoginRequest request) {
        return userService
                .findUserByUsername(request.username())
                .hasElement()
                .flatMap(it -> {
                    if(it) {
                        return Mono.error(UserAlreadyExistException::new);
                    } else {
                        return userService
                                .saveUser(new User(request.username(), request.password()))
                                .then(Mono.empty());
                    }
                })
                .then();
    }

    @PostMapping("/login")
    public Mono<SingleMessageResponse> login(@RequestBody UserLoginRequest request) {
        return tokenService
                .generateToken(new UsernamePasswordAuthenticationToken(request.username(), request.password()))
                .map(it -> new SingleMessageResponse("token", it));
    }
}
