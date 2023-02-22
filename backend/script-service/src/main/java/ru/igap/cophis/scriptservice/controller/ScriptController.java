package ru.igap.cophis.scriptservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.igap.cophis.scriptservice.dto.ScriptDTO;
import ru.igap.cophis.scriptservice.model.Script;
import ru.igap.cophis.scriptservice.service.ScriptService;
import ru.igap.cophis.scriptservice.utils.DataValidationException;

import java.util.List;


@RestController
@RequestMapping("/api/v1/script")
@RequiredArgsConstructor
public class ScriptController {

    private final ScriptService scriptService;

    @GetMapping
    public ResponseEntity<List<Script>> getScriptPage(@RequestParam(required = false) Integer page,
                                                      @RequestParam(required = false) Integer scripts_per_page) {
        return ResponseEntity.ok(scriptService.getScriptsPage(page, scripts_per_page));
    }

    @GetMapping("/{scriptId}")
    public ResponseEntity<Script> getScript(@PathVariable("scriptId") String scriptId) throws DataValidationException {
        return scriptService.getScript(scriptId).map(s -> new ResponseEntity<>(s, HttpStatus.OK))
                .orElseThrow(() -> new DataValidationException("Скрипт с таким id не найден"));
    }


    @PostMapping
    public ResponseEntity<Script> createScript(@RequestBody ScriptDTO scriptDTO) throws DataValidationException {
        return scriptService.createScript(scriptDTO).map(s -> new ResponseEntity<>(s, HttpStatus.OK))
                .orElseThrow(() -> new DataValidationException("Скрипт не был сохранен"));
    }

    @PatchMapping
    public ResponseEntity<Script> updateScript(@RequestParam String script_id,
                                               @RequestBody ScriptDTO scriptDTO) throws DataValidationException {
        return scriptService.updateScript(script_id, scriptDTO).map(s -> new ResponseEntity<>(s, HttpStatus.OK))
                .orElseThrow(() -> new DataValidationException("Скрипт не был обновлен"));
    }

    @DeleteMapping
    public ResponseEntity<String> deleteScript(@RequestParam String script_id) throws DataValidationException {
        if (scriptService.deleteScript(script_id)) {
            return ResponseEntity.ok(String.format("Скрипт с id %s удален!", script_id));
        } else {
            throw new DataValidationException("Скрипт не был удален");
        }
    }

    @GetMapping("/execute/{scriptId}")
    public ResponseEntity<String> executeScript(@PathVariable("scriptId") String scriptId) {
        return ResponseEntity.ok(scriptService.executeScript(scriptId));
    }


}
