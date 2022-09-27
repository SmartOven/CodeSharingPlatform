package platform.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import platform.model.Code;

public interface CodeRepository extends JpaRepository<Code, UUID> {
    List<Code> findFirst10ByOrderByLoadDateTimeDesc();
}
