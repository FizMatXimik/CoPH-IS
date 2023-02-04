package ru.igap.cophis.scriptservice.model;

import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Accessors(chain = true)
@Table(name = "scripts")
public class Script {

    @Id
    private String id;

    private String name;

    private String path;

    private String url;

    private LocalDateTime last_start_datetime;

    private LocalDateTime last_finish_datetime;

    private boolean complete;

    private String error_message;

    private LocalDateTime created_at;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Script script = (Script) o;
        return id != null && Objects.equals(id, script.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
