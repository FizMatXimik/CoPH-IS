package ru.igap.cophis.scriptservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.igap.cophis.scriptservice.dto.ScriptDTO;
import ru.igap.cophis.scriptservice.model.Script;
import ru.igap.cophis.scriptservice.repository.ScriptRepository;
import ru.igap.cophis.scriptservice.service.ScriptService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ScriptServiceImpl implements ScriptService {

    private final ScriptRepository scriptRepository;

    @Override
    public List<Script> getScriptPage(Integer page, Integer scripts_per_page) {
        if (page != null && scripts_per_page != null) {
            return scriptRepository.findAll(PageRequest.of(page, scripts_per_page)).getContent();
        } else {
            return scriptRepository.findAll();
        }
    }

    @Override
    public Script createScript(ScriptDTO scriptDTO) {

        Script script = new Script()
                .setName(scriptDTO.getName())
                .setPath(scriptDTO.getPath())
                .setUrl(scriptDTO.getUrl())
                .setId(UUID.randomUUID().toString())
                .setCreated_at(LocalDateTime.now());

        return scriptRepository.save(script);
    }
}
