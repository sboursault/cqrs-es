package poc.sb.cqrses.custom.view.repository;

import org.springframework.data.repository.CrudRepository;
import poc.sb.cqrses.custom.view.EmailView;


public interface EmailViewRepository extends CrudRepository<EmailView, String>, EmailViewRepositoryCustom {

}