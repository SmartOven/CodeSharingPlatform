package platform.service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.model.Code;
import platform.model.dto.CodeDto;
import platform.model.dto.CodeMapper;
import platform.repository.CodeRepository;

@Service
public class CodeService {

    public List<CodeDto> getLatestCodes() {
        List<Code> codes = codeRepository.findFirst10ByOrderByLoadDateTimeDesc();

        // Decrement possible views count for everything
        codes.forEach(this::decrementPossibleViewsCount);

        return codes.stream()
                .filter(this::isNotExpired)
                .map(codeMapper::convertToDto)
                .collect(Collectors.toList());
    }

    public CodeDto getCodeById(UUID id) {
        Code code = codeRepository.getById(id);

        if (!isNotExpired(code)) {
            codeRepository.delete(code);
            throw new EntityNotFoundException("Code lifetime expired");
        }

        return codeMapper.convertToDto(code);
    }

    public UUID putCode(CodeDto codeDto) {
        Code code = codeMapper.convertToEntity(codeDto);
        return codeRepository.save(code).getId();
    }

    boolean isNotExpired(Code code) {
        return code.getExpirationDateTime().isAfter(Instant.now())
                && code.getPossibleViewsCount() > 0;
    }

    void decrementPossibleViewsCount(Code code) {
        code.setPossibleViewsCount(code.getPossibleViewsCount() - 1);
        codeRepository.save(code);
    }

    public CodeService(@Autowired CodeMapper codeMapper,
                       @Autowired CodeRepository codeRepository) {
        this.codeMapper = codeMapper;
        this.codeRepository = codeRepository;
    }

    private final CodeMapper codeMapper;
    private final CodeRepository codeRepository;
}
