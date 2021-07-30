package org.featx.sta2ry.musca.mapper;

import lombok.extern.slf4j.Slf4j;
import org.featx.sta2ry.musca.SpringDataTestSuit;
import org.featx.sta2ry.musca.entity.SharedEntity;
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
@DisplayName("Mapper: Shared")
public class SharedMapperTest extends SpringDataTestSuit {

    @Resource
    private SharedMapper sharedMapper;

    private SharedEntity sharedEntity;

    private SharedEntity generate() {
        SharedEntity entity = generate(SharedEntity.class);
        entity.setCode(entity.getCode().length() > 32 ? entity.getCode().substring(0, 32) : entity.getCode());
        entity.setName(entity.getName().length() > 32 ? entity.getName().substring(0, 32) : entity.getName());
        entity.setDescription(entity.getDescription().length() > 255 ? entity.getDescription().substring(0, 255) :
                entity.getDescription());
        return entity;
    }

    @BeforeEach
    void prepare() {
        sharedEntity = generate();
        sharedMapper.insert(sharedEntity);
        SharedEntity retrieveEntity = sharedMapper.selectByCode(sharedEntity.getCode());
        sharedEntity.setDeleted(retrieveEntity.getDeleted());
        sharedEntity.setCreatedAt(retrieveEntity.getCreatedAt());
        sharedEntity.setUpdatedAt(retrieveEntity.getUpdatedAt());
    }

    @Test
    void testUpdate() {
        SharedEntity newEntity = new SharedEntity();
        newEntity.setName("name");
        newEntity.setType(2);
        newEntity.setDescription("description");
        assertEquals(0, sharedMapper.update(newEntity));

        newEntity.setCode(sharedEntity.getCode());
        assertEquals(1, sharedMapper.update(newEntity));


        SharedEntity retrieveEntity = sharedMapper.selectByCode(sharedEntity.getCode());
        sharedEntity.setName("name");
        sharedEntity.setType(2);
        sharedEntity.setDescription("description");
        sharedEntity.setUpdatedAt(retrieveEntity.getUpdatedAt());

        assertEquals(sharedEntity, retrieveEntity);
    }

    @Test
    void testDelete() {
        assertEquals(1, sharedMapper.delete(sharedEntity.getCode(), true));
        assertNull(sharedMapper.selectByCode(sharedEntity.getCode()));
    }
}
