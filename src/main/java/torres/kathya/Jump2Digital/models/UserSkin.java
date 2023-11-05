package torres.kathya.Jump2Digital.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import torres.kathya.Jump2Digital.security.user.User;

@Entity
@Setter
@Getter
@RequiredArgsConstructor
@Table(name="user_skin")
public class UserSkin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="skin_id")
    private SkinModel skin;


}
