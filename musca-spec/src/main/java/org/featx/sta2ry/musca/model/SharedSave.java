package org.featx.sta2ry.musca.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.featx.spec.model.BaseUnified;

import java.io.Serial;


/**
 * @author Excepts
 * @since 2020/4/11 22:33
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SharedSave extends BaseUnified {

    @Serial
    private static final long serialVersionUID = 5570811073514589053L;

    private String code;

    private Integer type;

    private Integer target;

    private String targetCode;

    private String platform;

    private String platformCode;

    private String userCode;

}
