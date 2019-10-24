package br.com.rmsystems.customsession.repository;

import br.com.rmsystems.customsession.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    User findUserByLogin(String login);
}
