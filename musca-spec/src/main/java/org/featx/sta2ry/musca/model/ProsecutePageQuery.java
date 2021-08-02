package org.featx.sta2ry.musca.model;

import java.io.Serial;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.featx.spec.model.PageRequest;

/**
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ProsecutePageQuery extends PageRequest {

    @Serial
    private static final long serialVersionUID = 564823245124563273L;

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
