package org.featx.sta2ry.musca.criteria;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author Excepts
 * @since 2020/6/21 11:15
 */
@Data
@EqualsAndHashCode
@ToString
public class TopicCriteria {

    private String code;

    private Integer type;

    private String name;

}
