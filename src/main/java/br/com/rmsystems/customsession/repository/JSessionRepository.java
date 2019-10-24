package br.com.rmsystems.customsession.repository;

import br.com.rmsystems.customsession.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JSessionRepository extends JpaRepository<Session, String> {

    Session findBySessionId(String sessionId);
}
