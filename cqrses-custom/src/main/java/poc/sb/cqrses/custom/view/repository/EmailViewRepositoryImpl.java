package poc.sb.cqrses.custom.view.repository;

import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.util.StringUtils;
import poc.sb.cqrses.custom.view.EmailView;

import static org.elasticsearch.common.unit.Fuzziness.ONE;

public class EmailViewRepositoryImpl implements EmailViewRepositoryCustom {

    private ElasticsearchTemplate template;

    @Override
    public Page<EmailView> search(String text, Pageable pageable) {
        QueryBuilder queryBuilder;
        if (StringUtils.isEmpty(text)) {
            queryBuilder = new MatchAllQueryBuilder();
        } else {
            queryBuilder = new MultiMatchQueryBuilder(text)
                    .field("subject", 2)
                    .field("content")
                    .fuzziness(ONE);
        }
        NativeSearchQuery query = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder)
                .withPageable(pageable)
                .build();
        return template.queryForPage(query, EmailView.class);
    }
}

