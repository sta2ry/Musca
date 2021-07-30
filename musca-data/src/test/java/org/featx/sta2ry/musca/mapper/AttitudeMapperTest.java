package org.featx.sta2ry.musca.mapper;

import lombok.extern.slf4j.Slf4j;
import org.featx.sta2ry.musca.SpringDataTestSuit;
import org.featx.sta2ry.musca.entity.AttitudeEntity;
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
@DisplayName("Mapper: Attitude")
public class AttitudeMapperTest extends SpringDataTestSuit {

    @Resource
    private AttitudeMapper attitudeMapper;

    private AttitudeEntity attitudeEntity;

    private AttitudeEntity generate() {
        AttitudeEntity entity = generate(AttitudeEntity.class);
        entity.setCode(entity.getCode().length() > 32 ? entity.getCode().substring(0, 32) : entity.getCode());
        entity.setName(entity.getName().length() > 32 ? entity.getName().substring(0, 32) : entity.getName());
        entity.setDescription(entity.getDescription().length() > 255 ? entity.getDescription().substring(0, 255) :
                entity.getDescription());
        return entity;
    }

    @BeforeEach
    void prepare() {
        attitudeEntity = generate();
        attitudeMapper.insert(attitudeEntity);
        AttitudeEntity retrieveEntity = attitudeMapper.selectByCode(attitudeEntity.getCode());
        attitudeEntity.setDeleted(retrieveEntity.getDeleted());
        attitudeEntity.setCreatedAt(retrieveEntity.getCreatedAt());
        attitudeEntity.setUpdatedAt(retrieveEntity.getUpdatedAt());
    }

    @Test
    void testUpdate() {
        AttitudeEntity newEntity = new AttitudeEntity();
        newEntity.setName("name");
        newEntity.setType(2);
        newEntity.setDescription("description");
        assertEquals(0, attitudeMapper.update(newEntity));

        newEntity.setCode(attitudeEntity.getCode());
        assertEquals(1, attitudeMapper.update(newEntity));


        AttitudeEntity retrieveEntity = attitudeMapper.selectByCode(attitudeEntity.getCode());
        attitudeEntity.setName("name");
        attitudeEntity.setType(2);
        attitudeEntity.setDescription("description");
        attitudeEntity.setUpdatedAt(retrieveEntity.getUpdatedAt());

        assertEquals(attitudeEntity, retrieveEntity);
    }

    @Test
    void testDelete() {
        assertEquals(1, attitudeMapper.delete(attitudeEntity.getCode(), true));
        assertNull(attitudeMapper.selectByCode(attitudeEntity.getCode()));
    }
}
