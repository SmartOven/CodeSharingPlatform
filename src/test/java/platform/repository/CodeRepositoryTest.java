package platform.repository;

import java.time.Instant;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import platform.model.Code;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
class CodeRepositoryTest {

    @Test
    void findFirst10ByOrderByIdDesc() {

        // 0 items
        var list = codeRepository.findFirst10ByOrderByLoadDateTimeDesc();
        assertNotNull(list);
        assertEquals(0, list.size());

        // 1 item
        testEntityManager.persistAndFlush(
                new Code(null, "some code", Instant.now(), Instant.now().plusSeconds(100L), 100L)
        );
        assertEquals(1, codeRepository.findFirst10ByOrderByLoadDateTimeDesc().size());

        // 10 items
        for (int i = 0; i < 9; i++) {
            testEntityManager.persistAndFlush(
                    new Code(null, "some code", Instant.now(), Instant.now().plusSeconds(100L), 100L)
            );
        }

        list = codeRepository.findFirst10ByOrderByLoadDateTimeDesc();
        var all = codeRepository.findAll();
        assertEquals(10, list.size());

        // 11 items
        testEntityManager.persistAndFlush(
                new Code(null, "some code", Instant.now(), Instant.now().plusSeconds(100L), 100L)
        );
        list = codeRepository.findFirst10ByOrderByLoadDateTimeDesc();
        assertEquals(10, list.size());
    }

    public CodeRepositoryTest(@Autowired CodeRepository codeRepository,
                              @Autowired TestEntityManager testEntityManager) {
        this.codeRepository = codeRepository;
        this.testEntityManager = testEntityManager;
    }

    private final CodeRepository codeRepository;
    private final TestEntityManager testEntityManager;
}