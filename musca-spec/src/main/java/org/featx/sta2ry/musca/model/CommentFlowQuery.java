package org.featx.sta2ry.musca.model;

import java.io.Serial;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.featx.spec.model.FlowRequest;

/**
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CommentFlowQuery extends FlowRequest {

    @Serial
    private static final long serialVersionUID = -3378756542362683471L;

    private String code;

    private String target;

    private String targetCode;

    private String userCode;

    private String content;

    private Integer type;

}
