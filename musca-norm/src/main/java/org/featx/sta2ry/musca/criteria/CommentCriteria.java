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
public class CommentCriteria {

    @Serial
    private String code;

    private String target;

    private String targetCode;

    private String userCode;

    private String content;

    private Integer type;

}
