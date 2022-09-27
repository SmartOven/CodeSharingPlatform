package platform.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import platform.model.dto.CodeDto;
import platform.service.CodeService;

@RestController
@RequestMapping("/code")
public class CodeWebController {

    @GetMapping("/latest")
    public ModelAndView getLatestCode() {
        ModelAndView model = new ModelAndView();
        model.setViewName("code-list");
        model.addObject("codeDtoList", codeService.getLatestCodes());

        return model;
    }

    @GetMapping("/{id}")
    public ModelAndView getCodeById(@PathVariable UUID id) {
        CodeDto codeDto = codeService.getCodeById(id);

        ModelAndView model = new ModelAndView();
        model.setViewName("code");

        model.addObject("date", codeDto.getDate());
        model.addObject("views", codeDto.getViews());
        model.addObject("time", codeDto.getTime());
        model.addObject("code", codeDto.getCode());

        return model;
    }

    @GetMapping("/new")
    public ModelAndView putCode() {
        ModelAndView model = new ModelAndView();
        model.setViewName("form");

        return model;
    }

    public CodeWebController(@Autowired CodeService codeService) {
        this.codeService = codeService;
    }

    private final CodeService codeService;
}
