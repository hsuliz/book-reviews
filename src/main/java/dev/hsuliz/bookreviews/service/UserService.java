package dev.hsuliz.bookreviews.service;

import dev.hsuliz.bookreviews.exception.UserAlreadyExistException;
import dev.hsuliz.bookreviews.exception.UserNotFoundException;
import dev.hsuliz.bookreviews.model.User;
import dev.hsuliz.bookreviews.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Mono<Void> saveUser(User user) {
        return findUserByUsername(user)
                .flatMap(it -> Mono.error(UserAlreadyExistException::new))
                .onErrorResume(UserNotFoundException.class, it -> userRepository.save(user))
                .then();

    }

    public Mono<User> findUserByUsername(User user) {
        return userRepository
                .findUserByUsername(user.username())
                .switchIfEmpty(Mono.error(UserNotFoundException::new));
    }

    public Mono<User> findUserByUsername(String username) {
        return userRepository
                .findUserByUsername(username)
                .switchIfEmpty(Mono.error(UserNotFoundException::new));
    }
}
