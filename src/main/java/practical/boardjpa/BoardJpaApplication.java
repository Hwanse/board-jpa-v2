package practical.boardjpa;

import com.querydsl.jpa.impl.JPAQueryFactory;
import javax.persistence.EntityManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing  // 애플리케이션 전체에 JPA Auditing 애노테이션 활성화를 위한 애노테이션
@SpringBootApplication
public class BoardJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(BoardJpaApplication.class, args);
    }

    @Bean
    public JPAQueryFactory jpaQueryFactory(EntityManager em) {
        return new JPAQueryFactory(em);
    }

}
