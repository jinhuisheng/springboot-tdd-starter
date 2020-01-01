package com.test.common.ddd;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 使用jpa的entity都应该从这个接口进行继承
 *
 * @author huisheng.jin
 * @version 2019/1/25
 */
@Getter
@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
@EqualsAndHashCode
public abstract class BaseEntity implements Entity, AggregateRoot {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    protected String id;

    @CreatedDate
    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @LastModifiedDate
    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    /**
     * 是否有效(软删除)
     */
    @Column(nullable = false, name = "deleted")
    private Integer deleted = 0;
}