package umc.spring.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.repository.ReviewRepository;
import umc.spring.repository.StoreRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StoreQueryServiceImpl implements StoreQueryService {

    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public Optional<Store> findStore(Long id) {
        return storeRepository.findById(id);
    }

    @Override
    public List<Store> findStoresByNameAndScore(String name, Float score) {
        List<Store> filteredStores = storeRepository.dynamicQueryWithBooleanBuilder(name, score);
        filteredStores.forEach(store -> System.out.println("Store: " + store));
        return filteredStores;
    }

    @Override
    public Page<Review> getReviewList(Long storeId, Integer page) {
        Store store = storeRepository.findById(storeId).orElseThrow(() -> new IllegalArgumentException("Store not found"));
        return reviewRepository.findAllByStore(store, PageRequest.of(page, 10));
    }

    @Override
    public Page<Review> getMyReviewList(Long userId, Integer page) {
        return reviewRepository.findAllByMemberId(userId, PageRequest.of(page - 1, 10));
    }
}