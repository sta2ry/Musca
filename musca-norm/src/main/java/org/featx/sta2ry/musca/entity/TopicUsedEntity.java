package org.featx.sta2ry.musca.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.featx.spec.entity.AbstractUnified;
import org.featx.spec.entity.AbstractUpdate;

import java.io.Serial;

/**
 * @author Excepts
 * @since 2020/4/12 13:52
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TopicUsedEntity extends AbstractUpdate<Long> {

    @Serial
    private static final long serialVersionUID = -4441039419344895608L;

    private String code;

    private Integer target;

    private String targetCode;

    private String topicCode;

}
