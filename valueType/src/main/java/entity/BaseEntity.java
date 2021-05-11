package entity;

import lombok.Data;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@Data
public abstract class BaseEntity {

    // 엔티티 x, 테이블과 매핑 x -> 직접사용할일 없음
    // 상속관계 매핑 x
    // 공통으로 사용하는 속성을 모으는 역할
    private String createdBy;
    private LocalDateTime createdDateTime;
    private String lastModifiedBy;
    private LocalDateTime lasModifiedDateTime;
}
