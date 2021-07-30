package org.featx.sta2ry.musca.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.featx.spec.model.FlowRequest;

/**
 * @author Excepts
 * @since 2020/4/11 23:26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AttitudeFlowQuery extends FlowRequest {

    private static final long serialVersionUID = -3378756542362683471L;

    private String code

    private String target

    private String targetCode

    private String userCode

    private Integer score

    private Integer type

}
