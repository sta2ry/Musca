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
public class ProsecuteCriteria {

    @Serial
    private String code;

    private Integer type;

    private Integer status;

    private Integer cause;

    private Integer reason;

    private String target;

    private String targetCode;

    private String userCode;

    private String description;

}
