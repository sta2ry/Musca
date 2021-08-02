package org.featx.sta2ry.musca.criteria;

import java.io.Serial;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 *
 */
@Data
@EqualsAndHashCode
@ToString
public class FollowCriteria {

    @Serial
    private String code;

    private Integer type;

    private Integer status;

    private String target;

    private String targetCode;

    private String userCode;

}
