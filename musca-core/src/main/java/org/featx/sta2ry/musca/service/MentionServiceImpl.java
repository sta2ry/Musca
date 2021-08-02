package org.featx.sta2ry.musca.service;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.featx.spec.feature.IdGenerate;
import org.featx.spec.model.QuerySection;
import org.featx.spec.util.CollectionUtil;
import org.featx.spec.util.StringUtil;
import org.featx.sta2ry.musca.convert.MentionConvert;
import org.featx.sta2ry.musca.criteria.MentionCriteria;
import org.featx.sta2ry.musca.entity.MentionEntity;
import org.featx.sta2ry.musca.mapper.MentionMapper;
import org.featx.sta2ry.musca.model.MentionInfo;
import org.featx.sta2ry.musca.model.MentionItem;
import org.featx.sta2ry.musca.model.MentionPageQuery;
import org.featx.sta2ry.musca.model.MentionSave;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.featx.sta2ry.musca.convert.MentionConvert.*;

/**
 * @author Excepts
 * @since 2020/4/12 14:27
 */
@Slf4j
@Service
public class MentionServiceImpl implements MentionService {

    @Resource
    private MentionMapper mentionMapper;

    @Resource
    private IdGenerate idGenerate;

    @Override
    @Transactional
    public MentionItem save(MentionSave mentionSave) {
        MentionEntity entity = toEntity(mentionSave);
        if (StringUtil.isBlank(entity.getCode())) {
            entity.setCode(String.format("%s%s", "DFM", Long.toString(idGenerate.nextId(), 36)));
            mentionMapper.insert(entity);
        } else {
            mentionMapper.upsert(entity);
        }
        return toItem(entity);
    }

    @Override
    @Transactional
    public MentionItem update(MentionSave mentionSave) {
        MentionEntity entity = toEntity(mentionSave);
        mentionMapper.update(entity);
        return toItem(entity);
    }

    @Override
    public void delete(String code) {
        mentionMapper.delete(code, true);
    }

    @Override
    public MentionInfo findOne(String code) {
        return toInfo(mentionMapper.selectByCode(code));
    }

    @Override
    public List<MentionItem> listByCodes(List<String> codes) {
        return Optional.ofNullable(codes)
                .map(list -> list.stream().filter(StringUtil::isNotBlank).collect(Collectors.toList()))
                .filter(CollectionUtil::isNotEmpty)
                .map(list -> mentionMapper.selectByCodes(list))
                .filter(CollectionUtil::isNotEmpty)
                .map(list -> list.stream()
                        .map(MentionConvert::toItem)
                        .sorted(Comparator.comparingInt(dme -> codes.indexOf(dme.getCode())))
                        .collect(Collectors.toList()))
                .orElseGet(Lists::newArrayList);
    }

    @Override
    @Transactional(readOnly = true)
    public QuerySection<MentionItem> page(MentionPageQuery pageQuery) {
        MentionCriteria criteria = toCriteria(pageQuery);
        long count = mentionMapper.countByQuery(criteria);
        if(count <= 0) {
            return QuerySection.of(Lists.newArrayList());
        }
        List<MentionEntity> moduleEntities =
                mentionMapper.selectByPage(criteria, pageQuery.correctProperties());
        return QuerySection.of(moduleEntities.stream().map(MentionConvert::toItem).collect(Collectors.toList()))
                .total(count);
    }

}
