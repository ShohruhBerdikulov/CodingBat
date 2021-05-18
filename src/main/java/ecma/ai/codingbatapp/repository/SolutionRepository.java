package ecma.ai.codingbatapp.repository;

import ecma.ai.codingbatapp.entity.Solution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Repository
public interface SolutionRepository extends JpaRepository<Solution, Integer> {
    boolean existsByText(String text);

    boolean existsByTextAndIdNot(@NotNull(message = "bo'sh bo'lmasin") @NotEmpty(message = "Empty bo'lmasin") String text, Integer id);
}
