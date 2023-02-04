package ru.igap.cophis.scriptservice.service;

import ru.igap.cophis.scriptservice.dto.ScriptDTO;
import ru.igap.cophis.scriptservice.model.Script;

import java.util.List;

public interface ScriptService {

    List<Script> getScriptPage(Integer page, Integer scripts_per_page);
    Script createScript(ScriptDTO scriptDTO);

}
