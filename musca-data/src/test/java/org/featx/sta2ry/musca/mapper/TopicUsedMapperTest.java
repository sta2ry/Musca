package org.featx.sta2ry.musca.mapper;

import lombok.extern.slf4j.Slf4j;
import org.featx.sta2ry.musca.SpringDataTestSuit;
import org.featx.sta2ry.musca.entity.TopicUsedEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
@Slf4j
@Transactional
@EnableAutoConfiguration
@DisplayName("Mapper: TopicUsed")
public class TopicUsedMapperTest extends SpringDataTestSuit {

    @Resource
    private TopicUsedMapper topicUsedMapper;

    private TopicUsedEntity TopicUsedEntity;

    private TopicUsedEntity generate() {
        TopicUsedEntity entity = generate(TopicUsedEntity.class);
        entity.setCode(entity.getCode().length() > 32 ? entity.getCode().substring(0, 32) : entity.getCode());
        return entity;
    }

    @BeforeEach
    void prepare() {
        TopicUsedEntity = generate();
        topicUsedMapper.insert(TopicUsedEntity);
        TopicUsedEntity retrieveEntity = topicUsedMapper.selectByCode(TopicUsedEntity.getCode());
        TopicUsedEntity.setDeleted(retrieveEntity.getDeleted());
        TopicUsedEntity.setCreatedAt(retrieveEntity.getCreatedAt());
        TopicUsedEntity.setUpdatedAt(retrieveEntity.getUpdatedAt());
    }

    @Test
    void testUpdate() {
        TopicUsedEntity newEntity = new TopicUsedEntity();
        assertEquals(0, topicUsedMapper.update(newEntity));

        newEntity.setCode(TopicUsedEntity.getCode());
        assertEquals(1, topicUsedMapper.update(newEntity));


        TopicUsedEntity retrieveEntity = topicUsedMapper.selectByCode(TopicUsedEntity.getCode());
         TopicUsedEntity.setUpdatedAt(retrieveEntity.getUpdatedAt());

        assertEquals(TopicUsedEntity, retrieveEntity);
    }

    @Test
    void testDelete() {
        assertEquals(1, topicUsedMapper.delete(TopicUsedEntity.getCode(), true));
        assertNull(topicUsedMapper.selectByCode(TopicUsedEntity.getCode()));
    }
}
