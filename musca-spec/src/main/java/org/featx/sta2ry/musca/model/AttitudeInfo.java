package org.featx.sta2ry.musca.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.featx.spec.model.BaseUnified;

import java.io.Serial;

/**
 * @author Excepts
 * @since 2020/4/11 23:11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AttitudeInfo extends BaseUnified {

    @Serial
    private static final long serialVersionUID = -3141043832603954339L;

    private String code;

    private Integer target;

    private String targetCode;

    private String userCode;

    private Integer score;

    private Integer type;

}
