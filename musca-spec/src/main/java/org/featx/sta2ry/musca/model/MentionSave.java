package org.featx.sta2ry.musca.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.featx.spec.model.BaseUnified;


/**
 * @author Excepts
 * @since 2020/4/11 22:33
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MentionSave extends BaseUnified {

    private static final long serialVersionUID = 5570811073514589053L;

    private String code

    private Integer type

    private String target

    private String targetCode

    private String userCode

    private String where

    private String whereCode


}
