package org.featx.sta2ry.musca.model;

import java.io.Serial;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.featx.spec.model.BaseUnified;

/**
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CommentInfo extends BaseUnified {

    @Serial
    private static final long serialVersionUID = -3141043832603954339L;

    private String code;

    private Integer target;

    private String targetCode;

    private String userCode;

    private String content;

    private Integer type;


}
