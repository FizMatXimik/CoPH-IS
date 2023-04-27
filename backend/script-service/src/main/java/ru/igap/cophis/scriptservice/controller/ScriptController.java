package ru.igap.cophis.scriptservice.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.igap.cophis.scriptservice.dto.ScriptDTO;
import ru.igap.cophis.scriptservice.model.Script;
import ru.igap.cophis.scriptservice.service.ScriptService;
import ru.igap.cophis.scriptservice.utils.DataValidationException;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/v1/script")
@RequiredArgsConstructor
public class ScriptController {

    private final ScriptService scriptService;

    private final ModelMapper modelMapper;

    private static final Logger logger = LoggerFactory.getLogger(ScriptController.class);

    @GetMapping
    public List<ScriptDTO> getScriptPage(@RequestParam(required = false) Integer page,
                                                      @RequestParam(required = false) Integer scripts_per_page) {
        return scriptService.getScriptsPage(page, scripts_per_page).stream().map(script -> modelMapper.map(script, ScriptDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{scriptId}")
    public ResponseEntity<ScriptDTO> getScript(@PathVariable("scriptId") String scriptId) {
        Script script = scriptService.getScript(scriptId);

        ScriptDTO scriptResponse = modelMapper.map(script, ScriptDTO.class);

        return ResponseEntity.ok().body(scriptResponse);

    }


    @PostMapping
    public ResponseEntity<ScriptDTO> createScript(@RequestBody ScriptDTO scriptDTO) {
        Script scriptRequest = modelMapper.map(scriptDTO, Script.class);
        Script script = scriptService.createScript(scriptRequest);

        // convert entity to DTO
        ScriptDTO scriptResponse = modelMapper.map(script, ScriptDTO.class);
        return new ResponseEntity<ScriptDTO>(scriptResponse, HttpStatus.CREATED);

    }

    @PatchMapping
    public ResponseEntity<ScriptDTO> updateScript(@RequestParam String script_id,
                                               @RequestBody ScriptDTO scriptDTO) {
        Script scriptRequest = modelMapper.map(scriptDTO, Script.class);
        Script script = scriptService.updateScript(script_id, scriptRequest);

        // convert entity to DTO
        ScriptDTO scriptResponse = modelMapper.map(script, ScriptDTO.class);
        return ResponseEntity.ok().body(scriptResponse);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteScript(@RequestParam String script_id) {
        scriptService.deleteScript(script_id);
        return new ResponseEntity<String>("Скрипт был удален успешно!", HttpStatus.OK);

    }

    @GetMapping("/execute/{scriptId}")
    public ResponseEntity<String> executeScript(@PathVariable("scriptId") String scriptId) {
        logger.debug("ScriptService-Controller-executeScript --- Script id: {}", scriptId);
        String res = scriptService.executeScript(scriptId);
        logger.debug("ScriptService-Controller-executeScript --- Result of script: {}", res);
        return ResponseEntity.ok(res);
    }


}
