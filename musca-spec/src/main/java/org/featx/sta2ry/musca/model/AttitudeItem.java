package org.featx.sta2ry.musca.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.featx.spec.model.BaseUnified;

/**
 * @author Excepts
 * @since 2020/4/11 23:12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AttitudeItem extends BaseUnified {

    private static final long serialVersionUID = 1668164802765732399L;

    private String code

    private String target

    private String targetCode

    private String userCode

    private Integer score

    private Integer type


}
