package ru.igap.cophis.scriptservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.igap.cophis.scriptservice.dto.ScriptDTO;
import ru.igap.cophis.scriptservice.model.Script;
import ru.igap.cophis.scriptservice.repository.ScriptRepository;
import ru.igap.cophis.scriptservice.service.ScriptService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ScriptServiceImpl implements ScriptService {

    private final ScriptRepository scriptRepository;

    @Override
    public List<Script> getScriptsPage(Integer page, Integer scripts_per_page) {
        if (page != null && scripts_per_page != null) {
            return scriptRepository.findAll(PageRequest.of(page, scripts_per_page)).getContent();
        } else {
            return scriptRepository.findAll();
        }
    }

    @Override
    public Optional<Script> getScript(String script_id) {
        return scriptRepository.findById(script_id);
    }

    @Override
    public Optional<Script> createScript(ScriptDTO scriptDTO) {
        Script script = new Script()
                .setName(scriptDTO.getName())
                .setPath(scriptDTO.getPath())
                .setUrl(scriptDTO.getUrl())
                .setId(UUID.randomUUID().toString())
                .setCreated_at(LocalDateTime.now());

        return Optional.of(scriptRepository.save(script));
    }

    @Override
    public Optional<Script> updateScript(String script_id, ScriptDTO scriptDTO) {
        Optional<Script> script_op = scriptRepository.findById(script_id);

        if (script_op.isPresent()) {
            Script script = script_op.get()
                    .setName(scriptDTO.getName())
                    .setPath(scriptDTO.getPath())
                    .setUrl(scriptDTO.getUrl());

            return Optional.of(scriptRepository.save(script));
        }
        return Optional.empty();
    }

    @Override
    public boolean deleteScript(String script_id) {
        Optional<Script> script_op = scriptRepository.findById(script_id);
        if (script_op.isPresent()) {
            scriptRepository.deleteById(script_id);
            return true;
        }
        return false;
    }

    @Override
    public String executeScript(String script_id) {
        Optional<Script> script_op = scriptRepository.findById(script_id);
        if (script_op.isPresent()) {

            ProcessBuilder processBuilder = new ProcessBuilder();
            processBuilder.command(script_op.get().getPath());
            try {
                Process process = processBuilder.start();
                StringBuilder output = new StringBuilder();
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(process.getInputStream()));
                BufferedReader reader1 = new BufferedReader(
                        new InputStreamReader(process.getErrorStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    output.append(line + "\n");
                }
                while ((line = reader1.readLine()) != null) {
                    output.append(line + "\n");
                }
                int exitVal = process.waitFor();
                if (exitVal == 0) {
                    System.out.println("Success!");
                    System.out.println(output);
                    return "Success!" + "\n" + output;
                } else {
                    return "ERROR!" + "\n" + output;
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
        return "ERROR!";
    }
}
