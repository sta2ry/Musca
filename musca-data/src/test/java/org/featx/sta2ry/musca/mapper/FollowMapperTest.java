package org.featx.sta2ry.musca.mapper;

import lombok.extern.slf4j.Slf4j;
import org.featx.sta2ry.musca.SpringDataTestSuit;
import org.featx.sta2ry.musca.entity.FollowEntity;
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
@DisplayName("Mapper: Follow")
public class FollowMapperTest extends SpringDataTestSuit {

    @Resource
    private FollowMapper followMapper;

    private FollowEntity followEntity;

    private FollowEntity generate() {
        FollowEntity entity = generate(FollowEntity.class);
        entity.setCode(entity.getCode().length() > 32 ? entity.getCode().substring(0, 32) : entity.getCode());
        entity.setName(entity.getName().length() > 32 ? entity.getName().substring(0, 32) : entity.getName());
        entity.setDescription(entity.getDescription().length() > 255 ? entity.getDescription().substring(0, 255) :
                entity.getDescription());
        return entity;
    }

    @BeforeEach
    void prepare() {
        followEntity = generate();
        followMapper.insert(followEntity);
        FollowEntity retrieveEntity = followMapper.selectByCode(followEntity.getCode());
        followEntity.setDeleted(retrieveEntity.getDeleted());
        followEntity.setCreatedAt(retrieveEntity.getCreatedAt());
        followEntity.setUpdatedAt(retrieveEntity.getUpdatedAt());
    }

    @Test
    void testUpdate() {
        FollowEntity newEntity = new FollowEntity();
        newEntity.setName("name");
        newEntity.setType(2);
        newEntity.setDescription("description");
        assertEquals(0, followMapper.update(newEntity));

        newEntity.setCode(followEntity.getCode());
        assertEquals(1, followMapper.update(newEntity));


        FollowEntity retrieveEntity = followMapper.selectByCode(followEntity.getCode());
        followEntity.setName("name");
        followEntity.setType(2);
        followEntity.setDescription("description");
        followEntity.setUpdatedAt(retrieveEntity.getUpdatedAt());

        assertEquals(followEntity, retrieveEntity);
    }

    @Test
    void testDelete() {
        assertEquals(1, followMapper.delete(followEntity.getCode(), true));
        assertNull(followMapper.selectByCode(followEntity.getCode()));
    }
}
