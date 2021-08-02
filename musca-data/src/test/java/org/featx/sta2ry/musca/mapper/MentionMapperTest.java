package org.featx.sta2ry.musca.mapper;

import lombok.extern.slf4j.Slf4j;
import org.featx.sta2ry.musca.SpringDataTestSuit;
import org.featx.sta2ry.musca.entity.MentionEntity;
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
@DisplayName("Mapper: Mention")
public class MentionMapperTest extends SpringDataTestSuit {

    @Resource
    private MentionMapper mentionMapper;

    private MentionEntity mentionEntity;

    private MentionEntity generate() {
        MentionEntity entity = generate(MentionEntity.class);
        entity.setCode(entity.getCode().length() > 32 ? entity.getCode().substring(0, 32) : entity.getCode());
        return entity;
    }

    @BeforeEach
    void prepare() {
        mentionEntity = generate();
        mentionMapper.insert(mentionEntity);
        MentionEntity retrieveEntity = mentionMapper.selectByCode(mentionEntity.getCode());
        mentionEntity.setDeleted(retrieveEntity.getDeleted());
        mentionEntity.setCreatedAt(retrieveEntity.getCreatedAt());
        mentionEntity.setUpdatedAt(retrieveEntity.getUpdatedAt());
    }

    @Test
    void testUpdate() {
        MentionEntity newEntity = new MentionEntity();
        newEntity.setType(2);
        assertEquals(0, mentionMapper.update(newEntity));

        newEntity.setCode(mentionEntity.getCode());
        assertEquals(1, mentionMapper.update(newEntity));


        MentionEntity retrieveEntity = mentionMapper.selectByCode(mentionEntity.getCode());
        mentionEntity.setType(2);
        mentionEntity.setUpdatedAt(retrieveEntity.getUpdatedAt());

        assertEquals(mentionEntity, retrieveEntity);
    }

    @Test
    void testDelete() {
        assertEquals(1, mentionMapper.delete(mentionEntity.getCode(), true));
        assertNull(mentionMapper.selectByCode(mentionEntity.getCode()));
    }
}
