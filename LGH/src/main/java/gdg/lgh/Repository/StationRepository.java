package gdg.lgh.Repository;

import gdg.lgh.Domain.KorailStation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StationRepository extends JpaRepository<KorailStation, Long> {
}