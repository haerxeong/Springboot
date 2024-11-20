package umc.spring.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.exception.handler.RegionHandler;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.Region;
import umc.spring.domain.Store;
import umc.spring.repository.RegionRepository;
import umc.spring.repository.StoreRepository;
import umc.spring.web.dto.StoreRequestDTO;

@Service
@RequiredArgsConstructor
public class StoreCommandServiceImpl implements StoreCommandService {
    private final StoreRepository storeRepository;
    private final RegionRepository regionRepository;

    @Override
    @Transactional
    public Store addStoreToRegion(Long regionId, StoreRequestDTO.RegisterDto request) {
        Region region = regionRepository.findById(regionId)
                .orElseThrow(() -> new RegionHandler(ErrorStatus.REGION_NOT_FOUND));
        Store newStore = StoreConverter.toStore(request);
        newStore.setRegion(region);
        return storeRepository.save(newStore);
    }
}