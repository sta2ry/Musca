package org.featx.sta2ry.musca.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.featx.spec.entity.AbstractUnified;

import java.io.Serial;

/**
 * @author Excepts
 * @since 2020/4/12 13:52
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TopicEntity extends AbstractUnified<Long> {

    @Serial
    private static final long serialVersionUID = -4441039419344895608L;

    private String description;
}
