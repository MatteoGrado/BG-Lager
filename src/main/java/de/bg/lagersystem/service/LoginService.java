package de.bg.lagersystem.service;

import de.bg.lagersystem.model.User;
import de.bg.lagersystem.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService
{
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean authenticate(String username, String password)
    {
        User user = userRepository.findByUsername(username).orElse(null);
        if (user == null || !matchesPhpBcrypt(password, user.getPassword())) {
            throw new IllegalArgumentException("Invalid username or password!");
        }
        return true;
    }

    private boolean matchesPhpBcrypt(String raw, String stored)
    {
        if (stored == null)
            return false;
        // PHP password_hash() normally emits $2y$; normalize to BCrypt's compatible $2a$ form.
        String compatible = stored.startsWith("$2y$") ? "$2a$" + stored.substring(4) : stored;
        return encoder.matches(raw, compatible);
    }
}
