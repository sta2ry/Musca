package org.featx.sta2ry.musca.service;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.featx.spec.feature.IdGenerate;
import org.featx.spec.model.QuerySection;
import org.featx.spec.util.CollectionUtil;
import org.featx.spec.util.StringUtil;
import org.featx.sta2ry.musca.convert.CommentConvert;
import org.featx.sta2ry.musca.criteria.CommentCriteria;
import org.featx.sta2ry.musca.entity.CommentEntity;
import org.featx.sta2ry.musca.mapper.CommentMapper;
import org.featx.sta2ry.musca.model.CommentInfo;
import org.featx.sta2ry.musca.model.CommentItem;
import org.featx.sta2ry.musca.model.CommentPageQuery;
import org.featx.sta2ry.musca.model.CommentSave;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.featx.sta2ry.musca.convert.CommentConvert.*;
/**
 *
 */
@Slf4j
@Service
@DubboService
public class CommentServiceImpl implements CommentService {

    @Resource
    private CommentMapper commentMapper;

    @Resource
    private IdGenerate idGenerate;

    @Override
    @Transactional
    public CommentItem save(CommentSave commentSave) {
        CommentEntity entity = toEntity(commentSave);
        if (StringUtil.isBlank(entity.getCode())) {
            entity.setCode(String.format("%s%s", "DFM", Long.toString(idGenerate.nextId(), 36)));
            commentMapper.insert(entity);
        } else {
            commentMapper.upsert(entity);
        }
        return toItem(entity);
    }

    @Override
    @Transactional
    public CommentItem update(CommentSave commentSave) {
        CommentEntity entity = toEntity(commentSave);
        commentMapper.update(entity);
        return toItem(entity);
    }

    @Override
    public void delete(String code) {
        commentMapper.delete(code, true);
    }

    @Override
    public CommentInfo findOne(String code) {
        return toInfo(commentMapper.selectByCode(code));
    }

    @Override
    public List<CommentItem> listByCodes(List<String> codes) {
        return Optional.ofNullable(codes)
                .map(list -> list.stream().filter(StringUtil::isNotBlank).collect(Collectors.toList()))
                .filter(CollectionUtil::isNotEmpty)
                .map(list -> commentMapper.selectByCodes(list))
                .filter(CollectionUtil::isNotEmpty)
                .map(list -> list.stream()
                        .map(CommentConvert::toItem)
                        .sorted(Comparator.comparingInt(dme -> codes.indexOf(dme.getCode())))
                        .collect(Collectors.toList()))
                .orElseGet(Lists::newArrayList);
    }

    @Override
    @Transactional(readOnly = true)
    public QuerySection<CommentItem> page(CommentPageQuery pageQuery) {
        CommentCriteria criteria = toCriteria(pageQuery);
        long count = commentMapper.countByQuery(criteria);
        if(count <= 0) {
            return QuerySection.of(Lists.newArrayList());
        }
        List<CommentEntity> moduleEntities =
                commentMapper.selectByPage(criteria, pageQuery.correctProperties());
        return QuerySection.of(moduleEntities.stream().map(CommentConvert::toItem).collect(Collectors.toList()))
                .total(count);
    }

}
