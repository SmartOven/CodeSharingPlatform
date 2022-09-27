package platform.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import platform.model.dto.CodeMapper;
import platform.repository.CodeRepository;
import platform.service.CodeService;

@DataJpaTest
class CodeWebControllerTest {

    @Test
    void getLatestCode() {
//        for (int i = 1; i <= 10; i++) {
//            testEntityManager.persistAndFlush(new Code(null, "code " + i, LocalDateTime.now()));
//        }
//
//        ModelAndView modelAndView = controller.getLatestCode();
    }

    public CodeWebControllerTest(@Autowired CodeRepository codeRepository,
                                 @Autowired TestEntityManager testEntityManager) {
        this.controller = new CodeWebController(
                new CodeService(
                        new CodeMapper(),
                        codeRepository
                )
        );
        this.testEntityManager = testEntityManager;
    }

    private final CodeWebController controller;
    private final TestEntityManager testEntityManager;

}