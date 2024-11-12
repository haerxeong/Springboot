package umc.spring.repository.MissionRepository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import umc.spring.domain.Mission;
import umc.spring.domain.QMission;
import umc.spring.domain.QStore;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.QMemberMission;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MissionRepositoryImpl implements MissionRepositoryCustom {
    private final JPAQueryFactory queryFactory;
    private final QMission mission = QMission.mission;
    private final QStore store = QStore.store;
    private final QMemberMission memberMission = QMemberMission.memberMission;

    @Override
    public Page<Mission> findMissionsByMemberIdAndStatus(Long memberId, String status, Long cursor, Pageable pageable) {
        BooleanBuilder predicate = new BooleanBuilder();

        // Dynamic conditions
        if (memberId != null) {
            predicate.and(memberMission.member.id.eq(memberId));
        }
        if (status != null) {
            predicate.and(memberMission.status.eq(MissionStatus.valueOf(status)));
        }
        if (cursor != null) {
            predicate.and(mission.id.lt(cursor));
        }

        // Execute query
        List<Mission> missions = queryFactory
                .select(mission)
                .from(memberMission)
                .join(memberMission.mission, mission)
                .join(mission.store, store)
                .where(predicate)
                .orderBy(mission.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        // Get total count and convert to Page
        long total = queryFactory
                .select(mission)
                .from(memberMission)
                .join(memberMission.mission, mission)
                .join(mission.store, store)
                .where(predicate)
                .fetchCount();

        return PageableExecutionUtils.getPage(missions, pageable, () -> total);
    }
}