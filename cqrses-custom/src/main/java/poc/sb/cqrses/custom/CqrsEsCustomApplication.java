package poc.sb.cqrses.custom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import poc.sb.cqrses.custom.view.repository.EmailViewRepository;

import java.util.Arrays;

import static poc.sb.cqrses.custom.view.EmailView.Builder.anEmailView;

@SpringBootApplication
@EnableElasticsearchRepositories(basePackages = "poc.sb.cqrses.custom.view.repository")
public class CqrsEsCustomApplication implements CommandLineRunner {

    public static void main(String[] args) {
		SpringApplication.run(CqrsEsCustomApplication.class, args);
	}

	@Autowired
    EmailViewRepository emailViewRepository;

    @Override
    public void run(String... args) throws Exception {

        // emailViewRepository.deleteAll();

        emailViewRepository.saveAll(Arrays.asList(
                anEmailView()
                        .id("f9d00996-6e06-405e-b270-000000000001")
                        .from("royce@ryria.com")
                        .to("arista@ryria.com")
                        .cc("adrian@ryria.com")
                        .subject("What's the plan ?")
                        .content("So, you want us to escape from this prison, kidnap the king, cross the countryside with him in tow while dodging soldiers who I assume might not accept our side of the story, and go to another secret prison so that he can visit an inmate ?")
                        .build(),
                anEmailView()
                        .id("f9d00996-6e06-405e-b270-000000000002")
                        .from("arista@ryria.com")
                        .to("royce@ryria.com, adrian@ryria.com")
                        .subject("RE: What's the plan ?")
                        .content("Either that, or you can be tortured to death in four hours.")
                        .build(),
                anEmailView()
                        .id("f9d00996-6e06-405e-b270-000000000003")
                        .from("adrian@ryria.com")
                        .to("arista@ryria.com, royce@ryria.com")
                        .subject("RE: What's the plan ?")
                        .content("Sounds like a really good plan to me. Royce ?")
                        .build()
        ));


        // some other quotes : https://www.goodreads.com/work/quotes/15702572-theft-of-swords

        //emailViewRepository.findAll();
    }
}
