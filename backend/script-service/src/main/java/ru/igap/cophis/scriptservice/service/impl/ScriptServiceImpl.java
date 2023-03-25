package ru.igap.cophis.scriptservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.igap.cophis.scriptservice.dto.ScriptDTO;
import ru.igap.cophis.scriptservice.model.Script;
import ru.igap.cophis.scriptservice.repository.ScriptRepository;
import ru.igap.cophis.scriptservice.service.ScriptService;
import ru.igap.cophis.scriptservice.service.client.ExecuteRestTemplateClient;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ScriptServiceImpl implements ScriptService {

    private final ScriptRepository scriptRepository;
    private final ExecuteRestTemplateClient restTemplate;

    @Autowired
    public ScriptServiceImpl(ScriptRepository scriptRepository, ExecuteRestTemplateClient restTemplateBuilder) {
        this.scriptRepository = scriptRepository;
        this.restTemplate = restTemplateBuilder;
    }

    @Override
    public List<Script> getScriptsPage(Integer page, Integer scripts_per_page) {
        if (page != null && scripts_per_page != null) {
            return scriptRepository.findAll(PageRequest.of(page, scripts_per_page)).getContent();
        } else {
            return scriptRepository.findAll();
        }
    }

    @Override
    public Script getScript(String script_id) {
        Optional<Script> scriptOp = scriptRepository.findById(script_id);
        return scriptOp.orElse(null);
    }

    @Override
    public Script createScript(Script script) {
        script.setId(UUID.randomUUID().toString())
                .setCreated_at(LocalDateTime.now());
        return scriptRepository.save(script);
    }

    @Override
    public Script updateScript(String script_id, Script scriptRequest) {
        Optional<Script> scriptOp = scriptRepository.findById(script_id);

        if (scriptOp.isPresent()) {
            Script script = scriptOp.get()
                    .setName(scriptRequest.getName())
                    .setLast_start_datetime(scriptRequest.getLast_start_datetime())
                    .setLast_finish_datetime(scriptRequest.getLast_finish_datetime())
                    .setComplete(scriptRequest.isComplete())
                    .setError_message(scriptRequest.getError_message());
            return scriptRepository.save(script);
        }
        return null;
    }

    @Override
    public void deleteScript(String script_id) {
        Optional<Script> script_op = scriptRepository.findById(script_id);
        if (script_op.isPresent()) {
            Script script = script_op.get();
            scriptRepository.delete(script);
        }
    }

    @Override
    public String executeScript(String script_id) {
        Optional<Script> scriptOp = scriptRepository.findById(script_id);
        if (scriptOp.isPresent()) {
            return restTemplate.getOrganization(scriptOp.get().getName());
        }
        return "Нет скрипта с таким id!";
    }
}
