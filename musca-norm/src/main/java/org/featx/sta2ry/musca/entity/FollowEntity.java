package org.featx.sta2ry.musca.entity;

import java.io.Serial;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.featx.spec.entity.AbstractUnified;

/**
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class FollowEntity extends AbstractUnified<Long> {
    @Serial
    private static final long serialVersionUID = -4441039419344895608L;

    private String code;

    private Integer type;

    private Integer status;

    private String target;

    private String targetCode;

    private String userCode;


}
