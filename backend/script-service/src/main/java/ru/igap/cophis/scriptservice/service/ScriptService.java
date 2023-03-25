package ru.igap.cophis.scriptservice.service;

import ru.igap.cophis.scriptservice.dto.ScriptDTO;
import ru.igap.cophis.scriptservice.model.Script;

import java.util.List;
import java.util.Optional;

public interface ScriptService {

    List<Script> getScriptsPage(Integer page, Integer scripts_per_page);
    Script getScript(String script_id);
    Script createScript(Script script);
    Script updateScript(String script_id, Script scriptRequest);
    void deleteScript(String script_id);

    String executeScript(String script_id);


}
