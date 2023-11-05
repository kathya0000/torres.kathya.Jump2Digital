package torres.kathya.Jump2Digital.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import torres.kathya.Jump2Digital.models.SkinModel;

@Repository
public interface ISkinRepository extends JpaRepository<SkinModel, Long> {
}
