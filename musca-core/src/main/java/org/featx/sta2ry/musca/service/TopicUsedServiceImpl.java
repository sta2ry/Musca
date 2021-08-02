package org.featx.sta2ry.musca.service;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.featx.spec.feature.IdGenerate;
import org.featx.spec.model.QuerySection;
import org.featx.spec.util.CollectionUtil;
import org.featx.spec.util.StringUtil;
import org.featx.sta2ry.musca.convert.TopicUsedConvert;
import org.featx.sta2ry.musca.criteria.TopicUsedCriteria;
import org.featx.sta2ry.musca.entity.TopicUsedEntity;
import org.featx.sta2ry.musca.mapper.TopicUsedMapper;
import org.featx.sta2ry.musca.model.TopicUsedInfo;
import org.featx.sta2ry.musca.model.TopicUsedItem;
import org.featx.sta2ry.musca.model.TopicUsedPageQuery;
import org.featx.sta2ry.musca.model.TopicUsedSave;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.featx.sta2ry.musca.convert.TopicUsedConvert.*;

/**
 * @author Excepts
 * @since 2020/4/12 14:27
 */
@Slf4j
@Service
public class TopicUsedServiceImpl implements TopicUsedService {

    @Resource
    private TopicUsedMapper topicusedMapper;

    @Resource
    private IdGenerate idGenerate;

    @Override
    @Transactional
    public TopicUsedItem save(TopicUsedSave topicusedSave) {
        TopicUsedEntity entity = toEntity(topicusedSave);
        if (StringUtil.isBlank(entity.getCode())) {
            entity.setCode(String.format("%s%s", "DFM", Long.toString(idGenerate.nextId(), 36)));
            topicusedMapper.insert(entity);
        } else {
            topicusedMapper.upsert(entity);
        }
        return toItem(entity);
    }

    @Override
    @Transactional
    public TopicUsedItem update(TopicUsedSave topicusedSave) {
        TopicUsedEntity entity = toEntity(topicusedSave);
        topicusedMapper.update(entity);
        return toItem(entity);
    }

    @Override
    public void delete(String code) {
        topicusedMapper.delete(code, true);
    }

    @Override
    public TopicUsedInfo findOne(String code) {
        return toInfo(topicusedMapper.selectByCode(code));
    }

    @Override
    public List<TopicUsedItem> listByCodes(List<String> codes) {
        return Optional.ofNullable(codes)
                .map(list -> list.stream().filter(StringUtil::isNotBlank).collect(Collectors.toList()))
                .filter(CollectionUtil::isNotEmpty)
                .map(list -> topicusedMapper.selectByCodes(list))
                .filter(CollectionUtil::isNotEmpty)
                .map(list -> list.stream()
                        .map(TopicUsedConvert::toItem)
                        .sorted(Comparator.comparingInt(dme -> codes.indexOf(dme.getCode())))
                        .collect(Collectors.toList()))
                .orElseGet(Lists::newArrayList);
    }

    @Override
    @Transactional(readOnly = true)
    public QuerySection<TopicUsedItem> page(TopicUsedPageQuery pageQuery) {
        TopicUsedCriteria criteria = toCriteria(pageQuery);
        long count = topicusedMapper.countByQuery(criteria);
        if(count <= 0) {
            return QuerySection.of(Lists.newArrayList());
        }
        List<TopicUsedEntity> moduleEntities =
                topicusedMapper.selectByPage(criteria, pageQuery.correctProperties());
        return QuerySection.of(moduleEntities.stream().map(TopicUsedConvert::toItem).collect(Collectors.toList()))
                .total(count);
    }

}
