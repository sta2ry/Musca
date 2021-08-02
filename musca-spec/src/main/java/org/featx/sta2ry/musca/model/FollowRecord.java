package org.featx.sta2ry.musca.model;

import java.io.Serial;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.featx.spec.model.Record;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 *
 */
@Data
@EqualsAndHashCode
@ToString(callSuper = true)
public class FollowRecord implements Serializable {

    @Serial
    private static final long serialVersionUID = 3542473394788128677L;

    private String code;

    private Integer type;

    private Integer status;

    private Integer target;

    private String targetCode;

    private String userCode;


}
