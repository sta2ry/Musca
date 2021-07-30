package org.featx.sta2ry.musca.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.featx.spec.model.Record;

import java.time.LocalDateTime;


/**
 * @author Excepts
 * @since 2020/4/11 22:34
 */
@Data
@EqualsAndHashCode
@ToString(callSuper = true)
public class AttitudeRecord {

    private static final long serialVersionUID = 3542473394788128677L;

    private String code

    private String target

    private String targetCode

    private String userCode

    private Integer score

    private Integer type


}
