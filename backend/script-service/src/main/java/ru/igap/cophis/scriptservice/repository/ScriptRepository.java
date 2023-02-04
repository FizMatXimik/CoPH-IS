package ru.igap.cophis.scriptservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.igap.cophis.scriptservice.model.Script;

@Repository
public interface ScriptRepository extends JpaRepository<Script, String> {

}
