package org.featx.sta2ry.musca.mapper;

import lombok.extern.slf4j.Slf4j;
import org.featx.sta2ry.musca.SpringDataTestSuit;
import org.featx.sta2ry.musca.entity.TopicEntity;
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
@DisplayName("Mapper: Topic")
public class TopicMapperTest extends SpringDataTestSuit {

    @Resource
    private TopicMapper topicMapper;

    private TopicEntity topicEntity;

    private TopicEntity generate() {
        TopicEntity entity = generate(TopicEntity.class);
        entity.setCode(entity.getCode().length() > 32 ? entity.getCode().substring(0, 32) : entity.getCode());
        entity.setName(entity.getName().length() > 32 ? entity.getName().substring(0, 32) : entity.getName());
        entity.setDescription(entity.getDescription().length() > 255 ? entity.getDescription().substring(0, 255) :
                entity.getDescription());
        return entity;
    }

    @BeforeEach
    void prepare() {
        topicEntity = generate();
        topicMapper.insert(topicEntity);
        TopicEntity retrieveEntity = topicMapper.selectByCode(topicEntity.getCode());
        topicEntity.setDeleted(retrieveEntity.getDeleted());
        topicEntity.setCreatedAt(retrieveEntity.getCreatedAt());
        topicEntity.setUpdatedAt(retrieveEntity.getUpdatedAt());
    }

    @Test
    void testUpdate() {
        TopicEntity newEntity = new TopicEntity();
        newEntity.setName("name");
        newEntity.setType(2);
        newEntity.setDescription("description");
        assertEquals(0, topicMapper.update(newEntity));

        newEntity.setCode(topicEntity.getCode());
        assertEquals(1, topicMapper.update(newEntity));


        TopicEntity retrieveEntity = topicMapper.selectByCode(topicEntity.getCode());
        topicEntity.setName("name");
        topicEntity.setType(2);
        topicEntity.setDescription("description");
        topicEntity.setUpdatedAt(retrieveEntity.getUpdatedAt());

        assertEquals(topicEntity, retrieveEntity);
    }

    @Test
    void testDelete() {
        assertEquals(1, topicMapper.delete(topicEntity.getCode(), true));
        assertNull(topicMapper.selectByCode(topicEntity.getCode()));
    }
}
