package ru.igap.cophis.scriptservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.igap.cophis.scriptservice.dto.ScriptDTO;
import ru.igap.cophis.scriptservice.model.Script;
import ru.igap.cophis.scriptservice.service.ScriptService;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/script")
@RequiredArgsConstructor
public class ScriptController {

    private final ScriptService scriptService;

    @GetMapping
    public ResponseEntity<List<Script>> getScriptPage(@RequestParam(required = false) Integer page,
                                                      @RequestParam(required = false) Integer scripts_per_page) {
        return ResponseEntity.ok(scriptService.getScriptPage(page, scripts_per_page));
    }


    @PostMapping
    public ResponseEntity<Script> createScript(@RequestBody ScriptDTO scriptDTO) {
        return ResponseEntity.ok(scriptService.createScript(scriptDTO));
    }
}
