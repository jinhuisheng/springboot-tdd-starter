package com.test.common.ddd;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Value Object
 *
 * @author huisheng.jin
 * @version 2019/1/25
 */
@Data
@MappedSuperclass
@EqualsAndHashCode
@EntityListeners({AuditingEntityListener.class})
public abstract class BaseValueObject<T> implements ValueObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected T id;

    @CreatedDate
    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
}