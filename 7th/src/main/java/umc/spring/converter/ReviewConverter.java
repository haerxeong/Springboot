package umc.spring.converter;

import umc.spring.domain.Member;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.web.dto.ReviewRequestDTO;
import umc.spring.web.dto.ReviewResponseDTO;

import java.time.LocalDateTime;

public class ReviewConverter {
    public static ReviewResponseDTO.ReviewRegisterResultDto toRegisterResultDTO(Review review){
        return ReviewResponseDTO.ReviewRegisterResultDto.builder()
                .reviewId(review.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Review toReview(ReviewRequestDTO.ReviewRegisterDto request, Member member, Store store){
        return Review.builder()
                .member(member)
                .store(store)
                .body(request.getBody())
                .score(request.getScore())
                .build();
    }
}
