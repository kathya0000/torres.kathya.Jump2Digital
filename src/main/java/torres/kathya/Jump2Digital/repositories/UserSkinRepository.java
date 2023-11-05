package torres.kathya.Jump2Digital.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import torres.kathya.Jump2Digital.models.SkinModel;
import torres.kathya.Jump2Digital.models.UserSkin;
import torres.kathya.Jump2Digital.security.user.User;

import java.util.List;

public interface UserSkinRepository extends JpaRepository<UserSkin, Long> {
    List<UserSkin> findByUserId(Long userId);
    List<UserSkin> findBySkinId(Long skinId);
    boolean existsByUserAndSkin(User user, SkinModel skin);
}

