package umc.spring.service.StoreService;

import org.springframework.data.domain.Page;
import umc.spring.domain.Review;

public interface StoreQueryService {
    void getReviewList(Long storeId, Integer page);
    Page<Review> getReviewList(Long StoreId, Integer page);
}
