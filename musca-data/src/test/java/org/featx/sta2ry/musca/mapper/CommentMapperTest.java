package org.featx.sta2ry.musca.mapper;

import lombok.extern.slf4j.Slf4j;
import org.featx.sta2ry.musca.SpringDataTestSuit;
import org.featx.sta2ry.musca.entity.CommentEntity;
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
@DisplayName("Mapper: Comment")
public class CommentMapperTest extends SpringDataTestSuit {

    @Resource
    private CommentMapper commentMapper;

    private CommentEntity commentEntity;

    private CommentEntity generate() {
        CommentEntity entity = generate(CommentEntity.class);
        entity.setCode(entity.getCode().length() > 32 ? entity.getCode().substring(0, 32) : entity.getCode());
        return entity;
    }

    @BeforeEach
    void prepare() {
        commentEntity = generate();
        commentMapper.insert(commentEntity);
        CommentEntity retrieveEntity = commentMapper.selectByCode(commentEntity.getCode());
        commentEntity.setDeleted(retrieveEntity.getDeleted());
        commentEntity.setCreatedAt(retrieveEntity.getCreatedAt());
        commentEntity.setUpdatedAt(retrieveEntity.getUpdatedAt());
    }

    @Test
    void testUpdate() {
        CommentEntity newEntity = new CommentEntity();
        newEntity.setType(2);
        assertEquals(0, commentMapper.update(newEntity));

        newEntity.setCode(commentEntity.getCode());
        assertEquals(1, commentMapper.update(newEntity));


        CommentEntity retrieveEntity = commentMapper.selectByCode(commentEntity.getCode());
        commentEntity.setType(2);
        commentEntity.setUpdatedAt(retrieveEntity.getUpdatedAt());

        assertEquals(commentEntity, retrieveEntity);
    }

    @Test
    void testDelete() {
        assertEquals(1, commentMapper.delete(commentEntity.getCode(), true));
        assertNull(commentMapper.selectByCode(commentEntity.getCode()));
    }
}
