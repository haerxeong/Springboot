package umc.spring.repository.ReviewRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc.spring.domain.Member;
import umc.spring.domain.Review;
import umc.spring.domain.Store;

import java.time.LocalDateTime;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepositoryCustom {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public void insertReview(Long memberId, Long storeId, String body, Float score) {
        Review review = Review.builder()
                .member(entityManager.getReference(Member.class, memberId))
                .store(entityManager.getReference(Store.class, storeId))
                .body(body)
                .score(score)
                .createdAt(LocalDateTime.now())
                .build();
        entityManager.persist(review);
    }
}