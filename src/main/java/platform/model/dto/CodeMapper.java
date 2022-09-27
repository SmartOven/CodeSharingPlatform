package platform.model.dto;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;
import platform.model.Code;

@Service
public class CodeMapper {

    public CodeDto convertToDto(Code codePost) {
        String code = codePost.getCode();
        Long time = codePost.getExpirationDateTime().getEpochSecond() - Instant.now().getEpochSecond();
        Long views = codePost.getPossibleViewsCount();
        String date = formatter.format(codePost.getLoadDateTime());

        return new CodeDto(code, time, views, date);
    }

    public Code convertToEntity(CodeDto codeDto) {
        String code = codeDto.getCode();
        Instant loadDateTime = Instant.now();
        Instant expirationDateTime = loadDateTime.plusSeconds(codeDto.getTime());
        Long possibleViewsCount = codeDto.getViews();

        return new Code(null, code, loadDateTime, expirationDateTime, possibleViewsCount);
    }

    public CodeMapper() {
        this.formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")
                .withZone(ZoneId.systemDefault());
        ;
    }

    private final DateTimeFormatter formatter;
}
