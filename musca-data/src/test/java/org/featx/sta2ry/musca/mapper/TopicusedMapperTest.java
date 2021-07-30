package org.featx.sta2ry.musca.mapper;

import lombok.extern.slf4j.Slf4j;
import org.featx.sta2ry.musca.SpringDataTestSuit;
import org.featx.sta2ry.musca.entity.TopicusedEntity;
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
@DisplayName("Mapper: Topicused")
public class TopicusedMapperTest extends SpringDataTestSuit {

    @Resource
    private TopicusedMapper topicusedMapper;

    private TopicusedEntity topicusedEntity;

    private TopicusedEntity generate() {
        TopicusedEntity entity = generate(TopicusedEntity.class);
        entity.setCode(entity.getCode().length() > 32 ? entity.getCode().substring(0, 32) : entity.getCode());
        entity.setName(entity.getName().length() > 32 ? entity.getName().substring(0, 32) : entity.getName());
        entity.setDescription(entity.getDescription().length() > 255 ? entity.getDescription().substring(0, 255) :
                entity.getDescription());
        return entity;
    }

    @BeforeEach
    void prepare() {
        topicusedEntity = generate();
        topicusedMapper.insert(topicusedEntity);
        TopicusedEntity retrieveEntity = topicusedMapper.selectByCode(topicusedEntity.getCode());
        topicusedEntity.setDeleted(retrieveEntity.getDeleted());
        topicusedEntity.setCreatedAt(retrieveEntity.getCreatedAt());
        topicusedEntity.setUpdatedAt(retrieveEntity.getUpdatedAt());
    }

    @Test
    void testUpdate() {
        TopicusedEntity newEntity = new TopicusedEntity();
        newEntity.setName("name");
        newEntity.setType(2);
        newEntity.setDescription("description");
        assertEquals(0, topicusedMapper.update(newEntity));

        newEntity.setCode(topicusedEntity.getCode());
        assertEquals(1, topicusedMapper.update(newEntity));


        TopicusedEntity retrieveEntity = topicusedMapper.selectByCode(topicusedEntity.getCode());
        topicusedEntity.setName("name");
        topicusedEntity.setType(2);
        topicusedEntity.setDescription("description");
        topicusedEntity.setUpdatedAt(retrieveEntity.getUpdatedAt());

        assertEquals(topicusedEntity, retrieveEntity);
    }

    @Test
    void testDelete() {
        assertEquals(1, topicusedMapper.delete(topicusedEntity.getCode(), true));
        assertNull(topicusedMapper.selectByCode(topicusedEntity.getCode()));
    }
}
