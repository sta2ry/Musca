package org.featx.sta2ry.musca.service;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.featx.spec.feature.IdGenerate;
import org.featx.spec.model.QuerySection;
import org.featx.spec.util.CollectionUtil;
import org.featx.spec.util.StringUtil;
import org.featx.sta2ry.musca.convert.TopicConvert;
import org.featx.sta2ry.musca.criteria.TopicCriteria;
import org.featx.sta2ry.musca.entity.TopicEntity;
import org.featx.sta2ry.musca.mapper.TopicMapper;
import org.featx.sta2ry.musca.model.TopicInfo;
import org.featx.sta2ry.musca.model.TopicItem;
import org.featx.sta2ry.musca.model.TopicPageQuery;
import org.featx.sta2ry.musca.model.TopicSave;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.featx.sta2ry.musca.convert.TopicConvert.*;

/**
 * @author Excepts
 * @since 2020/4/12 14:27
 */
@Slf4j
@Service
public class TopicServiceImpl implements TopicService {

    @Resource
    private TopicMapper topicMapper;

    @Resource
    private IdGenerate idGenerate;

    @Override
    @Transactional
    public TopicItem save(TopicSave topicSave) {
        TopicEntity entity = toEntity(topicSave);
        if (StringUtil.isBlank(entity.getCode())) {
            entity.setCode(String.format("%s%s", "DFM", Long.toString(idGenerate.nextId(), 36)));
            topicMapper.insert(entity);
        } else {
            topicMapper.upsert(entity);
        }
        return toItem(entity);
    }

    @Override
    @Transactional
    public TopicItem update(TopicSave topicSave) {
        TopicEntity entity = toEntity(topicSave);
        topicMapper.update(entity);
        return toItem(entity);
    }

    @Override
    public void delete(String code) {
        topicMapper.delete(code, true);
    }

    @Override
    public TopicInfo findOne(String code) {
        return toInfo(topicMapper.selectByCode(code));
    }

    @Override
    public List<TopicItem> listByCodes(List<String> codes) {
        return Optional.ofNullable(codes)
                .map(list -> list.stream().filter(StringUtil::isNotBlank).collect(Collectors.toList()))
                .filter(CollectionUtil::isNotEmpty)
                .map(list -> topicMapper.selectByCodes(list))
                .filter(CollectionUtil::isNotEmpty)
                .map(list -> list.stream()
                        .map(TopicConvert::toItem)
                        .sorted(Comparator.comparingInt(dme -> codes.indexOf(dme.getCode())))
                        .collect(Collectors.toList()))
                .orElseGet(Lists::newArrayList);
    }

    @Override
    @Transactional(readOnly = true)
    public QuerySection<TopicItem> page(TopicPageQuery pageQuery) {
        TopicCriteria criteria = toCriteria(pageQuery);
        long count = topicMapper.countByQuery(criteria);
        if(count <= 0) {
            return QuerySection.of(Lists.newArrayList());
        }
        List<TopicEntity> moduleEntities =
                topicMapper.selectByPage(criteria, pageQuery.correctProperties());
        return QuerySection.of(moduleEntities.stream().map(TopicConvert::toItem).collect(Collectors.toList()))
                .total(count);
    }

}
