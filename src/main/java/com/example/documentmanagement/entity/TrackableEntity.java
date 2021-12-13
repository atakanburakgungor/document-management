package com.example.documentmanagement.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@EntityListeners(AuditingEntityListener.class)
public class TrackableEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(name = "createdDate", hidden = true)
    @CreatedDate
    private LocalDateTime createdDate;

    @ApiModelProperty(name = "updatedDate", hidden = true)
    @LastModifiedDate
    private LocalDateTime updatedDate;

    @ApiModelProperty(name = "createdBy", hidden = true)
    @CreatedBy
    private String createdBy;

    @ApiModelProperty(name = "updatedBy", hidden = true)
    @LastModifiedBy
    private String updatedBy;

}
