package ecma.ai.codingbatapp.service;

import ecma.ai.codingbatapp.entity.Help;
import ecma.ai.codingbatapp.entity.Solution;
import ecma.ai.codingbatapp.payload.ApiResponse;
import ecma.ai.codingbatapp.repository.HelpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HelpService {
    @Autowired
    HelpRepository repository;

    public List<Help> getAll() {
        return repository.findAll();
    }

    public Help getById(Integer id) {
        Optional<Help> byId = repository.findById(id);
        return byId.orElseGet(Help::new);
    }

    public ApiResponse add(Help help) {
        if (repository.existsByUrlLink(help.getUrlLink())) {
            return new ApiResponse("this url is exist", false);
        }
        Help solution1 = new Help(help.getText(), help.getUrlLink());
        repository.save(solution1);
        return new ApiResponse("Success", true, solution1);
    }

    public ApiResponse update(Integer id, Help help) {
        Optional<Help> byId = repository.findById(id);
        if (!byId.isPresent()) {
            return new ApiResponse("id not found", false);
        }
        if (repository.existsByUrlLinkAndIdNot(help.getUrlLink(), id)) {
            return new ApiResponse("this solution is exist", false);
        }
        Help solution1 = byId.get();
        solution1.setText(help.getText());
        solution1.setUrlLink( help.getUrlLink());
        repository.save(solution1);
        return new ApiResponse("Success", true, solution1);
    }

    public ApiResponse delete(Integer id) {
        Optional<Help> byId = repository.findById(id);
        if (!byId.isPresent()) {
            return new ApiResponse("id not found", false);
        }
        repository.deleteById(id);
        return new ApiResponse("success", true);
    }

}
