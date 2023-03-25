package ru.igap.cophis.scriptservice.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class ScriptDTO {

    private String name;

    private LocalDateTime last_start_datetime;

    private LocalDateTime last_finish_datetime;

    private boolean complete;

    private String error_message;

}
