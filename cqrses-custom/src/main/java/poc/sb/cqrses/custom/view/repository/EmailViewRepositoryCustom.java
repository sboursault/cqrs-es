package poc.sb.cqrses.custom.view.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import poc.sb.cqrses.custom.view.EmailView;

import java.util.List;

public interface EmailViewRepositoryCustom {
 
    Page<EmailView> search(String text, Pageable pageable);
}