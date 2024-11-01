package umc.spring.service;

import umc.spring.model.User;
import umc.spring.repository.UserRepository;

// POJO 클래스 (Spring IoC 컨테이너가 UserRepository 객체를 자동으로 주입)
public class UserService {
    private UserRepository userRepository;

    // 생성자로 의존성 주입
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User FindUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}
