package org.featx.sta2ry.musca.mapper;

import lombok.extern.slf4j.Slf4j;
import org.featx.sta2ry.musca.SpringDataTestSuit;
import org.featx.sta2ry.musca.entity.ProsecuteEntity;
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
@DisplayName("Mapper: Prosecute")
public class ProsecuteMapperTest extends SpringDataTestSuit {

    @Resource
    private ProsecuteMapper prosecuteMapper;

    private ProsecuteEntity prosecuteEntity;

    private ProsecuteEntity generate() {
        ProsecuteEntity entity = generate(ProsecuteEntity.class);
        entity.setCode(entity.getCode().length() > 32 ? entity.getCode().substring(0, 32) : entity.getCode());
        entity.setName(entity.getName().length() > 32 ? entity.getName().substring(0, 32) : entity.getName());
        entity.setDescription(entity.getDescription().length() > 255 ? entity.getDescription().substring(0, 255) :
                entity.getDescription());
        return entity;
    }

    @BeforeEach
    void prepare() {
        prosecuteEntity = generate();
        prosecuteMapper.insert(prosecuteEntity);
        ProsecuteEntity retrieveEntity = prosecuteMapper.selectByCode(prosecuteEntity.getCode());
        prosecuteEntity.setDeleted(retrieveEntity.getDeleted());
        prosecuteEntity.setCreatedAt(retrieveEntity.getCreatedAt());
        prosecuteEntity.setUpdatedAt(retrieveEntity.getUpdatedAt());
    }

    @Test
    void testUpdate() {
        ProsecuteEntity newEntity = new ProsecuteEntity();
        newEntity.setName("name");
        newEntity.setType(2);
        newEntity.setDescription("description");
        assertEquals(0, prosecuteMapper.update(newEntity));

        newEntity.setCode(prosecuteEntity.getCode());
        assertEquals(1, prosecuteMapper.update(newEntity));


        ProsecuteEntity retrieveEntity = prosecuteMapper.selectByCode(prosecuteEntity.getCode());
        prosecuteEntity.setName("name");
        prosecuteEntity.setType(2);
        prosecuteEntity.setDescription("description");
        prosecuteEntity.setUpdatedAt(retrieveEntity.getUpdatedAt());

        assertEquals(prosecuteEntity, retrieveEntity);
    }

    @Test
    void testDelete() {
        assertEquals(1, prosecuteMapper.delete(prosecuteEntity.getCode(), true));
        assertNull(prosecuteMapper.selectByCode(prosecuteEntity.getCode()));
    }
}
