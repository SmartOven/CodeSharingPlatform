package platform.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import platform.model.dto.CodeDto;
import platform.pojo.CodeCreatedResponse;
import platform.service.CodeService;

@RestController
@RequestMapping("/api/code")
public class CodeApiController {

    @GetMapping("/latest")
    public List<CodeDto> getLatestCodes() {
        return codeService.getLatestCodes();
    }

    @GetMapping("/{id}")
    public CodeDto getCodeById(@PathVariable UUID id) {
        return codeService.getCodeById(id);
    }

    @PostMapping("/new")
    public CodeCreatedResponse putCode(@RequestBody CodeDto codeDto) {
        UUID createdId = codeService.putCode(codeDto);
        return new CodeCreatedResponse(String.valueOf(createdId));
    }

    public CodeApiController(@Autowired CodeService codeService) {
        this.codeService = codeService;
    }

    private final CodeService codeService;
}
