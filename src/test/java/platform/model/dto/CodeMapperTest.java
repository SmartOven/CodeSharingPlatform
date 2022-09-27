package platform.model.dto;

import java.time.Instant;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import platform.model.Code;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class CodeMapperTest {

    @Test
    void convertToDto() {
        Code code = new Code(
                UUID.randomUUID(),
                "some code 1",
                Instant.now(),
                Instant.now().plusSeconds(600L),
                10L
        );

        CodeDto codeDto = codeMapper.convertToDto(code);
        assertNotNull(codeDto);
    }

    private final CodeMapper codeMapper = new CodeMapper();
}