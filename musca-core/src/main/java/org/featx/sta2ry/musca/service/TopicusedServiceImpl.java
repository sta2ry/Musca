package org.featx.sta2ry.musca.service;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.featx.spec.feature.IdGenerate;
import org.featx.spec.model.QuerySection;
import org.featx.spec.util.CollectionUtil;
import org.featx.spec.util.StringUtil;
import org.featx.sta2ry.musca.criteria.TopicusedCriteria;
import org.featx.sta2ry.musca.entity.TopicusedEntity;
import org.featx.sta2ry.musca.mapper.TopicusedMapper;
import org.featx.sta2ry.musca.model.TopicusedInfo;
import org.featx.sta2ry.musca.model.TopicusedItem;
import org.featx.sta2ry.musca.model.TopicusedPageQuery;
import org.featx.sta2ry.musca.model.TopicusedSave;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Excepts
 * @since 2020/4/12 14:27
 */
@Slf4j
@Service
public class TopicusedServiceImpl implements TopicusedService {

    @Resource
    private TopicusedMapper topicusedMapper;

    @Resource
    private IdGenerate idGenerate;

    @Override
    @Transactional
    public TopicusedItem save(TopicusedSave topicusedSave) {
        TopicusedEntity entity = toEntity(topicusedSave);
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
    public TopicusedItem update(TopicusedSave topicusedSave) {
        TopicusedEntity entity = toEntity(topicusedSave);
        topicusedMapper.update(entity);
        return toItem(entity);
    }

    @Override
    public void delete(String code) {
        topicusedMapper.delete(code, true);
    }

    @Override
    public TopicusedInfo findOne(String code) {
        return toInfo(topicusedMapper.selectByCode(code));
    }

    @Override
    public List<TopicusedItem> listByCodes(List<String> codes) {
        return Optional.ofNullable(codes)
                .map(list -> list.stream().filter(StringUtil::isNotBlank).collect(Collectors.toList()))
                .filter(CollectionUtil::isNotEmpty)
                .map(list -> topicusedMapper.selectByCodes(list))
                .filter(CollectionUtil::isNotEmpty)
                .map(list -> list.stream()
                        .map(this::toItem)
                        .sorted(Comparator.comparingInt(dme -> codes.indexOf(dme.getCode())))
                        .collect(Collectors.toList()))
                .orElseGet(Lists::newArrayList);
    }

    @Override
    @Transactional(readOnly = true)
    public QuerySection<TopicusedItem> page(TopicusedPageQuery pageQuery) {
        TopicusedCriteria criteria = toCriteria(pageQuery);
        long count = topicusedMapper.countByQuery(criteria);
        if(count <= 0) {
            return QuerySection.of(Lists.newArrayList());
        }
        List<TopicusedEntity> moduleEntities =
                topicusedMapper.selectByPage(criteria, pageQuery.correctProperties());
        return QuerySection.of(moduleEntities.stream().map(this::toItem).collect(Collectors.toList()))
                .total(count);
    }

}
