package org.featx.sta2ry.musca.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.featx.spec.entity.AbstractUnified;

/**
 * @author Excepts
 * @since 2020/4/12 13:52
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MentionEntity extends AbstractUnified<Long> {

    private static final long serialVersionUID = -4441039419344895608L;

    private String code

    private Integer type

    private String target

    private String targetCode

    private String userCode

    private String where

    private String whereCode


}