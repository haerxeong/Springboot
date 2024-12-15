package umc.spring.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc.spring.domain.Store;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class StoreRepositoryImpl implements StoreRepositoryCustom {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public List<Store> dynamicQueryWithBooleanBuilder(String name, Float score) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Store> query = cb.createQuery(Store.class);
        Root<Store> store = query.from(Store.class);

        List<Predicate> predicates = new ArrayList<>();

        if (name != null) {
            predicates.add(cb.equal(store.get("name"), name));
        }

        if (score != null) {
            predicates.add(cb.greaterThanOrEqualTo(store.get("score"), score));
        }

        query.where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(query).getResultList();
    }
}