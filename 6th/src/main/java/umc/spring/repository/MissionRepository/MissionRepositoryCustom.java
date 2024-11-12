package umc.spring.repository.MissionRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import umc.spring.domain.Mission;

public interface MissionRepositoryCustom {
    Page<Mission> findMissionsByMemberIdAndStatus(Long memberId, String status, Long cursor, Pageable pageable);
}