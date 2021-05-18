package ecma.ai.codingbatapp.repository;

import ecma.ai.codingbatapp.entity.Help;
import ecma.ai.codingbatapp.entity.Solution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HelpRepository extends JpaRepository<Help, Integer> {
    boolean existsByUrlLink(String name);

    boolean existsByUrlLinkAndIdNot(String name, Integer id);
}
