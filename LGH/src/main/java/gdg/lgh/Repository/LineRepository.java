package gdg.lgh.Repository;

import gdg.lgh.Domain.KorailLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LineRepository extends JpaRepository<KorailLine, Long> {
}