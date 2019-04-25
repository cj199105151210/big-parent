package com.bosssoft.bigdata.aggregation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bosssoft.bigdata.admin.api.vo.TreeUtil;
import com.bosssoft.bigdata.aggregation.constant.CodeDetailConstant;
import com.bosssoft.bigdata.aggregation.constant.MinioBucketEnum;
import com.bosssoft.bigdata.aggregation.entity.*;
import com.bosssoft.bigdata.aggregation.mapper.SubjectMapper;
import com.bosssoft.bigdata.aggregation.po.SubjectPO;
import com.bosssoft.bigdata.aggregation.service.*;
import com.bosssoft.bigdata.aggregation.vo.SubjectRelVO;
import com.bosssoft.bigdata.aggregation.vo.SubjectTreeVO;
import com.bosssoft.bigdata.aggregation.vo.SubjectVO;
import com.bosssoft.bigdata.common.core.constant.CommonConstants;
import com.bosssoft.bigdata.common.core.util.R;
import com.bosssoft.bigdata.common.core.utils.CollectionUtil;
import com.bosssoft.bigdata.common.minio.service.MinioTemplate;
import com.bosssoft.bigdata.common.security.util.SecurityUtils;
import com.bosssoft.bigdata.usercenter.api.feign.RemoteUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.*;


/**
 * <p>
 * 栏目页面 服务类
 * </p>
 *
 * @author lifei
 * @since 2019-03-14
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class, SQLException.class})
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private AggrSubjectService aggrSubjectService;
    @Autowired
    private AggrWidgetRelService aggrWidgetRelService;
    @Autowired
    private AggrDataService aggrDataService;
    @Autowired
    private AggrHyperlinkService aggrHyperlinkService;
    @Autowired
    private AggrSlideshowService aggrSlideshowService;
    @Autowired
    private AggrSubjectRelService aggrSubjectRelService;
    @Autowired
    private AggrGatewayService aggrGatewayService;
    @Autowired
    private AggrLightappService aggrLightappService;
    @Autowired
    private SubjectMapper subjectMapper;
    @Autowired
    private RemoteUserService remoteUserService;
    @Autowired
    private MinioTemplate template;

    @Override
    public List<SubjectTreeVO> getSubjectTree() {
        // 树结构集合
        List<SubjectTreeVO> subjectTreeVOList = new ArrayList<>();
        SubjectTreeVO subjectTreeVO;

        // 栏目表所有数据
        QueryWrapper<AggrSubject> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("parent_subject_id", "sort");
        List<AggrSubject> aggrSubjectList = aggrSubjectService.list(queryWrapper);

        // 先将所有数据都转化为树结构
        for (AggrSubject aggrSubject : aggrSubjectList) {
            subjectTreeVO = new SubjectTreeVO();
            subjectTreeVO.setId(aggrSubject.getSubjectId());
            subjectTreeVO.setParentId(aggrSubject.getParentSubjectId() == null ? aggrSubject.getGatewayId() : aggrSubject.getParentSubjectId());
            subjectTreeVO.setLabel(aggrSubject.getName());
            subjectTreeVO.setChildren(null);
            subjectTreeVOList.add(subjectTreeVO);
        }

        // 门户表所有数据
        List<AggrGateway> aggrGatewayList = aggrGatewayService.list();
        for (AggrGateway aggrGateway : aggrGatewayList) {
            subjectTreeVO = new SubjectTreeVO();
            subjectTreeVO.setId(aggrGateway.getGatewayId());
            subjectTreeVO.setParentId(0);
            subjectTreeVO.setLabel(aggrGateway.getName());
            subjectTreeVO.setChildren(null);
            subjectTreeVOList.add(subjectTreeVO);
        }
        return TreeUtil.build(subjectTreeVOList, 0);
    }

    @Override
    public List<SubjectVO> getSubjectById(List<Integer> subjectIdList, List<Integer> gatewayIdList) {
        // 返回前台的具体信息集合
        List<SubjectVO> subjectVOList = new ArrayList<>();

        // 用于保存栏目表中的轻应用ID
        List<Integer> lightAppIdList = new ArrayList<>();
        // 用于保存栏目表中的轮播图控件ID
        List<Integer> subjectIdForSlideshow = new ArrayList<>();
        // 用于保存栏目表中的数据控件ID
        List<Integer> subjectIdForData = new ArrayList<>();
        // 用于保存栏目表中的超链接控件ID
        List<Integer> subjectIdForLink = new ArrayList<>();

        List<AggrSubject> aggrSubjectList = new ArrayList<>();
        List<AggrGateway> aggrGatewayList = new ArrayList<>();
        if (subjectIdList != null && subjectIdList.size() > 0) {
            // 栏目表具体内容
            QueryWrapper<AggrSubject> subjectQueryWrapper = new QueryWrapper<>();
            subjectQueryWrapper.in("subject_id", subjectIdList);
            aggrSubjectList = aggrSubjectService.list(subjectQueryWrapper);
        }
        if (gatewayIdList != null && gatewayIdList.size() > 0) {
            // 门户表具体内容
            QueryWrapper<AggrGateway> gatewayQueryWrapper = new QueryWrapper<>();
            gatewayQueryWrapper.in("gateway_id", gatewayIdList);
            aggrGatewayList = aggrGatewayService.list(gatewayQueryWrapper);
        }
        if (CollectionUtil.isEmpty(aggrSubjectList) && CollectionUtil.isEmpty(aggrGatewayList)) {
            return null;
        }
        for (AggrSubject aggrSubject : aggrSubjectList) {
            // 控件标识
            String typeFlag = aggrSubject.getTypeFlag();
            switch (typeFlag) {
                // 写在常量类中
                case CodeDetailConstant.GATEWAYTYPE_LIGHTAPP:
                    // 轻应用
                    lightAppIdList.add(aggrSubject.getLightappId());
                    break;
                case CodeDetailConstant.GATEWAYTYPE_SLIDESHOW:
                    // 轮播图
                    subjectIdForSlideshow.add(aggrSubject.getSubjectId());
                    break;
                case CodeDetailConstant.GATEWAYTYPE_DATA:
                    // 数据
                    subjectIdForData.add(aggrSubject.getSubjectId());
                    break;
                case CodeDetailConstant.GATEWAYTYPE_LINK:
                    // 超链接
                    subjectIdForLink.add(aggrSubject.getSubjectId());
                    break;
                default:
                    break;
            }
        }

        // 用户组信息
        Map<Integer, List<AggrWidgetRel>> aggrWidgetRelMap = new HashMap<>();
        // 轻应用用户组信息
        Map<Integer, List<AggrWidgetRel>> lightAppRelMap = new HashMap<>();
        // 轻应用信息
        Map<Integer, AggrLightapp> aggrLightappMap = new HashMap<>();
        // 轮播图信息
        Map<Integer, List<Map<String, Object>>> aggrSlideshowMap = new HashMap<>();
        // 数据信息
        Map<Integer, AggrData> aggrDataMap = new HashMap<>();
        // 超链接信息
        Map<Integer, AggrHyperlink> aggrHyperlinkMap = new HashMap<>();

        // 查询栏目对应用户组信息
        QueryWrapper<AggrWidgetRel> widgetRelQueryWrapper = new QueryWrapper<>();
        if (CollectionUtil.isNotEmpty(subjectIdList)) {
            widgetRelQueryWrapper.in("subject_id", subjectIdList);
            widgetRelQueryWrapper.eq("type", CodeDetailConstant.WIDGET_TYPE_NOT_LIGHTAPP);
            List<AggrWidgetRel> aggrWidgetRelList = aggrWidgetRelService.list(widgetRelQueryWrapper);
            for (AggrWidgetRel aggrWidgetRel : aggrWidgetRelList) {
                Integer mapId = aggrWidgetRel.getSubjectId();
                if (aggrWidgetRelMap.get(mapId) != null) {
                    aggrWidgetRelMap.get(mapId).add(aggrWidgetRel);
                } else {
                    List<AggrWidgetRel> mapValue = new ArrayList<>();
                    mapValue.add(aggrWidgetRel);
                    aggrWidgetRelMap.put(mapId, mapValue);
                }
            }
        }

        // 查询栏目对应轻应用信息
        if (CollectionUtil.isNotEmpty(lightAppIdList)) {
            QueryWrapper<AggrLightapp> lightappQueryWrapper = new QueryWrapper<>();
            lightappQueryWrapper.in("lightapp_id", lightAppIdList);
            List<AggrLightapp> aggrLightappList = aggrLightappService.list(lightappQueryWrapper);
            for (AggrLightapp aggrLightapp : aggrLightappList) {
                aggrLightappMap.put(aggrLightapp.getLightappId(), aggrLightapp);
            }
            // 查询轻应用用户组信息
            widgetRelQueryWrapper = new QueryWrapper<>();
            widgetRelQueryWrapper.in("subject_id", lightAppIdList);
            widgetRelQueryWrapper.eq("type", CodeDetailConstant.WIDGET_TYPE_LIGHTAPP);
            List<AggrWidgetRel> lightAppRelList = aggrWidgetRelService.list(widgetRelQueryWrapper);
            for (AggrWidgetRel lightAppRel : lightAppRelList) {
                Integer mapId = lightAppRel.getSubjectId();
                if (lightAppRelMap.get(mapId) != null) {
                    lightAppRelMap.get(mapId).add(lightAppRel);
                } else {
                    List<AggrWidgetRel> mapValue = new ArrayList<>();
                    mapValue.add(lightAppRel);
                    lightAppRelMap.put(mapId, mapValue);
                }
            }
        }

        // 查询栏目对应轮播图信息
        if (CollectionUtil.isNotEmpty(subjectIdForSlideshow)) {
            QueryWrapper<AggrSlideshow> slideshowQueryWrapper = new QueryWrapper<>();
            slideshowQueryWrapper.in("subject_id", subjectIdForSlideshow);
            slideshowQueryWrapper.orderByAsc("subject_id", "sort");
            List<Map<String, Object>> aggrSlideshowList = aggrSlideshowService.listMaps(slideshowQueryWrapper);
            for (Map<String, Object> aggrSlideshow : aggrSlideshowList) {
                Integer mapId = (Integer) aggrSlideshow.get("subject_id");
                if (aggrSlideshowMap.get(mapId) != null) {
                    aggrSlideshowMap.get(mapId).add(aggrSlideshow);
                } else {
                    List<Map<String, Object>> mapValue = new ArrayList<>();
                    mapValue.add(aggrSlideshow);
                    aggrSlideshowMap.put(mapId, mapValue);
                }
            }
        }

        // 查询栏目对应数据信息
        if (CollectionUtil.isNotEmpty(subjectIdForData)) {
            QueryWrapper<AggrData> aggrDataQueryWrapper = new QueryWrapper<>();
            aggrDataQueryWrapper.in("subject_id", subjectIdForData);
            List<AggrData> aggrDataList = aggrDataService.list(aggrDataQueryWrapper);
            for (AggrData aggrData : aggrDataList) {
                aggrDataMap.put(aggrData.getSubjectId(), aggrData);
            }
        }

        // 查询栏目对应超链接信息
        if (CollectionUtil.isNotEmpty(subjectIdForLink)) {
            QueryWrapper<AggrHyperlink> aggrHyperlinkQueryWrapper = new QueryWrapper<>();
            aggrHyperlinkQueryWrapper.in("subject_id", subjectIdForLink);
            List<AggrHyperlink> aggrHyperlinkList = aggrHyperlinkService.list(aggrHyperlinkQueryWrapper);
            for (AggrHyperlink aggrHyperlink : aggrHyperlinkList) {
                aggrHyperlinkMap.put(aggrHyperlink.getSubjectId(), aggrHyperlink);
            }
        }

        // 查询栏目对应门户信息
        if (CollectionUtil.isNotEmpty(aggrGatewayList)) {
            for (AggrGateway aggrGateway : aggrGatewayList) {
                SubjectVO subject = new SubjectVO();
                BeanUtils.copyProperties(aggrGateway, subject);
                subjectVOList.add(subject);
            }
        }

        // 拼装控件信息
        for (AggrSubject aggrSubject : aggrSubjectList) {
            // 控件标识
            String typeFlag = aggrSubject.getTypeFlag();
            SubjectVO subject = new SubjectVO();
            // 当前控件用户组信息
            List<String> groupMsg = new ArrayList<>();
            if (aggrWidgetRelMap.get(aggrSubject.getSubjectId()) != null && !CodeDetailConstant.GATEWAYTYPE_LIGHTAPP.equals(typeFlag)) {
                for (AggrWidgetRel aggrWidgetRel : aggrWidgetRelMap.get(aggrSubject.getSubjectId())) {
                    groupMsg.add(aggrWidgetRel.getGroupId());
                }
            } else if (lightAppRelMap.get(aggrSubject.getLightappId()) != null && CodeDetailConstant.GATEWAYTYPE_LIGHTAPP.equals(typeFlag)) {
                for (AggrWidgetRel lightAppRel : lightAppRelMap.get(aggrSubject.getLightappId())) {
                    groupMsg.add(lightAppRel.getGroupId());
                }
            }
            subject.setGroupMsg(groupMsg);
            subject.setCode(aggrSubject.getSubjectCode());
            switch (typeFlag) {
                case CodeDetailConstant.GATEWAYTYPE_LIGHTAPP:
                    // 轻应用
                    AggrLightapp aggrLightapp = aggrLightappMap.get(aggrSubject.getLightappId());
                    if (aggrLightapp != null) {
                        subject.setPhotoUrl(template.getObjectURL(MinioBucketEnum.BUCKETNAME_LIGHTAPP.getName(), aggrLightapp.getPhotoUrl(), 604800));
                        subject.setLightappName(aggrLightapp.getName());
                        subject.setUrl(aggrLightapp.getUrl());
                    }
                    break;
                case CodeDetailConstant.GATEWAYTYPE_SLIDESHOW:
                    // 轮播图
                    for (Map<String, Object> msg : aggrSlideshowMap.get(aggrSubject.getSubjectId())) {
                        msg.put("file_name", msg.get("photo_url"));
                        msg.put("photo_url", template.getObjectURL(MinioBucketEnum.BUCKETNAME_SLIDESHOW.getName(), msg.get("photo_url").toString(), 604800));
                    }
                    subject.setSlideShowMsg(aggrSlideshowMap.get(aggrSubject.getSubjectId()));
                    break;
                case CodeDetailConstant.GATEWAYTYPE_DATA:
                    // 数据
                    BeanUtils.copyProperties(aggrDataMap.get(aggrSubject.getSubjectId()), subject);
                    break;
                case CodeDetailConstant.GATEWAYTYPE_LINK:
                    // 超链接
                    BeanUtils.copyProperties(aggrHyperlinkMap.get(aggrSubject.getSubjectId()), subject);
                    break;
                default:
                    break;
            }
            BeanUtils.copyProperties(aggrSubject, subject);
            subjectVOList.add(subject);
        }
        return subjectVOList;
    }

    @Override
    public R addOrUpdateTreeNode(SubjectVO subjectVO) {

        String username = SecurityUtils.getUser().getUsername();
        LocalDateTime nowDate = LocalDateTime.now();
        // 栏目表
        AggrSubject aggrSubject = new AggrSubject();
        BeanUtils.copyProperties(subjectVO, aggrSubject);
        aggrSubject.setSubjectCode(subjectVO.getCode());
        aggrSubject.setCreateUser(username);
        aggrSubject.setCreateTime(nowDate);

        switch (subjectVO.getTypeFlag()) {
            case CodeDetailConstant.GATEWAYTYPE_SUBJECT:
                // 栏目
                aggrSubjectService.saveOrUpdate(aggrSubject);
                break;
            case CodeDetailConstant.GATEWAYTYPE_LIGHTAPP:
                // 轻应用
                aggrSubjectService.saveOrUpdate(aggrSubject);
                break;
            case CodeDetailConstant.GATEWAYTYPE_SLIDESHOW:
                // 轮播图
                aggrSubjectService.saveOrUpdate(aggrSubject);
                // 轮播图表全删全插
                AggrSlideshow aggrSlideshow = new AggrSlideshow();
                aggrSlideshow.setSubjectId(aggrSubject.getSubjectId());
                aggrSlideshowService.remove(new QueryWrapper<>(aggrSlideshow));

                List<Map<String, Object>> slideShowMsgList = subjectVO.getSlideShowMsg();
                List<AggrSlideshow> aggrSlideshowList = new ArrayList<>();
                for (Map<String, Object> slideShowMsg : slideShowMsgList) {
                    aggrSlideshow = new AggrSlideshow();
                    aggrSlideshow.setSubjectId(aggrSubject.getSubjectId());
                    if (slideShowMsg.get("name") != null) {
                        aggrSlideshow.setName(slideShowMsg.get("name").toString());
                    }
                    if (slideShowMsg.get("photo_url") != null) {
                        aggrSlideshow.setPhotoUrl(slideShowMsg.get("file_name").toString());
                    }
                    if (slideShowMsg.get("url") != null) {
                        aggrSlideshow.setUrl(slideShowMsg.get("url").toString());
                    }
                    if (slideShowMsg.get("sort") != null) {
                        aggrSlideshow.setSort(Integer.valueOf(slideShowMsg.get("sort").toString()));
                    }
                    if (slideShowMsg.get("describe_msg") != null) {
                        aggrSlideshow.setDescribeMsg(slideShowMsg.get("describe_msg").toString());
                    }
                    if (slideShowMsg.get("open_mode") != null) {
                        aggrSlideshow.setOpenMode(slideShowMsg.get("open_mode").toString());
                    }
                    aggrSlideshow.setCreateUser(username);
                    aggrSlideshow.setCreateTime(nowDate);
                    aggrSlideshowList.add(aggrSlideshow);
                }
                aggrSlideshowService.saveBatch(aggrSlideshowList);
                break;
            case CodeDetailConstant.GATEWAYTYPE_DATA:
                // 数据
                aggrSubjectService.saveOrUpdate(aggrSubject);
                // 全删全插
                AggrData aggrData = new AggrData();
                aggrData.setSubjectId(aggrSubject.getSubjectId());
                aggrDataService.remove(new QueryWrapper<>(aggrData));

                aggrData.setUrl(subjectVO.getUrl());
                aggrData.setDescribeMsg(subjectVO.getDescribeMsg());
                aggrData.setCreateUser(username);
                aggrData.setCreateTime(nowDate);
                aggrDataService.save(aggrData);
                break;
            case CodeDetailConstant.GATEWAYTYPE_LINK:
                // 超链接
                aggrSubjectService.saveOrUpdate(aggrSubject);
                // 全删全插
                AggrHyperlink aggrHyperlink = new AggrHyperlink();
                aggrHyperlink.setSubjectId(aggrSubject.getSubjectId());
                aggrHyperlinkService.remove(new QueryWrapper<>(aggrHyperlink));

                aggrHyperlink.setUrl(subjectVO.getUrl());
                aggrHyperlink.setDescribeMsg(subjectVO.getDescribeMsg());
                aggrHyperlink.setOpenMode(subjectVO.getOpenMode());
                aggrHyperlink.setCreateUser(username);
                aggrHyperlink.setCreateTime(nowDate);
                aggrHyperlinkService.save(aggrHyperlink);
                break;
            default:
                break;
        }
        if (subjectVO.getGroupMsg() != null && !CodeDetailConstant.GATEWAYTYPE_LIGHTAPP.equals(subjectVO.getTypeFlag())) {
            // 用户组关系表全删全插
            AggrWidgetRel aggrWidgetRel = new AggrWidgetRel();
            aggrWidgetRel.setSubjectId(aggrSubject.getSubjectId());
            aggrWidgetRel.setType(CodeDetailConstant.WIDGET_TYPE_NOT_LIGHTAPP);
            aggrWidgetRelService.remove(new QueryWrapper<>(aggrWidgetRel));
            // 插入控件用户组关系表
            List<AggrWidgetRel> aggrWidgetRelList = new ArrayList<>();
            for (String m : subjectVO.getGroupMsg()) {
                aggrWidgetRel = new AggrWidgetRel();
                aggrWidgetRel.setSubjectId(aggrSubject.getSubjectId());
                aggrWidgetRel.setType(CodeDetailConstant.WIDGET_TYPE_NOT_LIGHTAPP);
                aggrWidgetRel.setGroupId(m);
                aggrWidgetRel.setCreateUser(username);
                aggrWidgetRel.setCreateTime(nowDate);
                aggrWidgetRelList.add(aggrWidgetRel);
            }
            aggrWidgetRelService.saveBatch(aggrWidgetRelList);
        }
        return new R<>(aggrSubject.getGatewayId());
    }

    @Override
    public R deleteTreeNode(Integer subjectId) {
        // 先查出栏目表该条数据
        AggrSubject aggrSubject = aggrSubjectService.getById(subjectId);
        // 删除栏目表数据
        aggrSubjectService.removeById(subjectId);
        switch (aggrSubject.getTypeFlag()) {
            case CodeDetailConstant.GATEWAYTYPE_SUBJECT:
                // 栏目
                break;
            case CodeDetailConstant.GATEWAYTYPE_LIGHTAPP:
                // 轻应用
                break;
            case CodeDetailConstant.GATEWAYTYPE_SLIDESHOW:
                // 轮播图
                AggrSlideshow slideshowCondition = new AggrSlideshow();
                slideshowCondition.setSubjectId(subjectId);
                aggrSlideshowService.remove(new QueryWrapper<>(slideshowCondition));
                break;
            case CodeDetailConstant.GATEWAYTYPE_DATA:
                // 数据
                AggrData dataCondition = new AggrData();
                dataCondition.setSubjectId(subjectId);
                aggrDataService.remove(new QueryWrapper<>(dataCondition));
                break;
            case CodeDetailConstant.GATEWAYTYPE_LINK:
                // 超链接
                AggrHyperlink linkCondition = new AggrHyperlink();
                linkCondition.setSubjectId(subjectId);
                aggrHyperlinkService.remove(new QueryWrapper<>(linkCondition));
                break;
            default:
                break;
        }
        // 删除关系表
        AggrSubjectRel subjectRelCondition = new AggrSubjectRel();
        subjectRelCondition.setSubjectId(subjectId);
        aggrSubjectRelService.remove(new QueryWrapper<>(subjectRelCondition));
        if (!CodeDetailConstant.GATEWAYTYPE_LIGHTAPP.equals(aggrSubject.getTypeFlag())) {
            AggrWidgetRel widgetRelCondition = new AggrWidgetRel();
            widgetRelCondition.setSubjectId(subjectId);
            widgetRelCondition.setType(CodeDetailConstant.WIDGET_TYPE_NOT_LIGHTAPP);
            aggrWidgetRelService.remove(new QueryWrapper<>(widgetRelCondition));
        }
        return new R<>("删除成功！");
    }

    @Override
    public R subjectRel(SubjectRelVO subjectRelVO) {
        R userInfo = remoteUserService.selectUserInfo();
        Map<String, Object> userInfoMap = (Map<String, Object>) userInfo.getData();
        if (userInfoMap == null) {
            userInfoMap = new HashMap<>();
        }
        String userId = "";
        if (userInfoMap.get("guid") != null) {
            userId = userInfoMap.get("guid").toString();
        }

        String showStatus = subjectRelVO.getShowStatus();
        if ("1".equals(showStatus)) {
            // 新增
            // 新增之前删除的
            AggrSubjectRel condition = new AggrSubjectRel();
            BeanUtils.copyProperties(subjectRelVO, condition);
            condition.setUserId(userId);
            condition.setShowStatus(CommonConstants.STATUS_NORMAL);
            AggrSubjectRel oldAggrSubjectRel = aggrSubjectRelService.getOne(new QueryWrapper<>(condition));
            if (oldAggrSubjectRel != null) {
                aggrSubjectRelService.removeById(oldAggrSubjectRel);
            } else {
                AggrSubjectRel aggrSubjectRel = new AggrSubjectRel();
                BeanUtils.copyProperties(subjectRelVO, aggrSubjectRel);
                aggrSubjectRel.setUserId(userId);
                int sort = 1;
                // 新增控件在当前父节点最后一位
                AggrSubject subjectCondition = new AggrSubject();
                subjectCondition.setParentSubjectId(subjectRelVO.getParentSubjectId());
                QueryWrapper<AggrSubject> subjectQueryWrapper = new QueryWrapper<>(subjectCondition);
                subjectQueryWrapper.orderByDesc("sort");
                List<AggrSubject> aggrSubjectList = aggrSubjectService.list(subjectQueryWrapper);
                if (CollectionUtil.isNotEmpty(aggrSubjectList)) {
                    sort = aggrSubjectList.get(0).getSort() + 1;
                }
                // 判断当前父节点有无其他新增控件
                AggrSubjectRel subjectRelCondition = new AggrSubjectRel();
                subjectRelCondition.setParentSubjectId(subjectRelVO.getParentSubjectId());
                subjectRelCondition.setUserId(userId);
                subjectRelCondition.setShowStatus(CommonConstants.STATUS_DEL);
                QueryWrapper<AggrSubjectRel> subjectRelQueryWrapper = new QueryWrapper<>(subjectRelCondition);
                subjectRelQueryWrapper.orderByDesc("sort");
                List<AggrSubjectRel> aggrSubjectRelList = aggrSubjectRelService.list(subjectRelQueryWrapper);
                if (CollectionUtil.isNotEmpty(aggrSubjectRelList)) {
                    sort = aggrSubjectRelList.get(0).getSort() + 1;
                }
                aggrSubjectRel.setSort(sort);
                aggrSubjectRel.setCreateUser(userId);
                aggrSubjectRel.setCreateTime(LocalDateTime.now());
                aggrSubjectRelService.save(aggrSubjectRel);
            }
        } else if (CommonConstants.STATUS_NORMAL.equals(showStatus)) {
            // 移除
            if (subjectRelVO.getSubjectId() == null) {
                // 移除的为自定义增加的控件
                AggrSubjectRel condition = new AggrSubjectRel();
                BeanUtils.copyProperties(subjectRelVO, condition);
                condition.setUserId(userId);
                condition.setShowStatus(CommonConstants.STATUS_DEL);
                aggrSubjectRelService.remove(new QueryWrapper<>(condition));
            } else {
                // 默认控件移除
                AggrSubjectRel aggrSubjectRel = new AggrSubjectRel();
                BeanUtils.copyProperties(subjectRelVO, aggrSubjectRel);
                aggrSubjectRel.setUserId(userId);
                aggrSubjectRel.setCreateUser(userId);
                aggrSubjectRel.setCreateTime(LocalDateTime.now());
                aggrSubjectRelService.save(aggrSubjectRel);
            }
        }
        return new R<>("处理成功！");
    }

    @Override
    public List<SubjectTreeVO> getGatewayPage(String code) {
        R userInfo = remoteUserService.selectUserInfo();
        Map<String, Object> userInfoMap = (Map<String, Object>) userInfo.getData();
        if (userInfoMap == null) {
            userInfoMap = new HashMap<>();
        }

        String identity = "";
        String userId = "";
        List<String> groupIdList = new ArrayList<>();

        if (userInfoMap.get("type") != null) {
            identity = userInfoMap.get("type").toString();
        }
        if (userInfoMap.get("guid") != null) {
            userId = userInfoMap.get("guid").toString();
        }
        if (userInfoMap.get("userGroup") != null) {
            List<Map<String, Object>> userGroupList = (List<Map<String, Object>>) userInfoMap.get("userGroup");
            for (Map<String, Object> userGroup : userGroupList) {
                if (userGroup.get("guid") != null) {
                    groupIdList.add(userGroup.get("guid").toString());
                }
            }
        }

        // 树结构集合
        List<SubjectTreeVO> subjectTreeVOList = new ArrayList<>();
        SubjectTreeVO subjectTreeVO;

        // 第一步：返回除用户自定义移除的控件以外的门户下所有控件
        List<AggrSubject> aggrSubjectList = subjectMapper.getGatewayPage(userId, identity, code, groupIdList);
        // 完善第一步取得的数据的详细信息
        List<Integer> subjectIdList = new ArrayList<>();
        List<Integer> gatewayIdList = new ArrayList<>();
        for (AggrSubject aggrSubject : aggrSubjectList) {
            subjectIdList.add(aggrSubject.getSubjectId());
            if (!gatewayIdList.contains(aggrSubject.getGatewayId())) {
                gatewayIdList.add(aggrSubject.getGatewayId());
            }
        }
        List<SubjectVO> subjectMsgList = getSubjectById(subjectIdList, gatewayIdList);
        // 先将门户信息加入到集合中
        for (SubjectVO subjectMsg : subjectMsgList) {
            if (subjectMsg.getSubjectId() == null) {
                subjectTreeVO = new SubjectTreeVO();
                subjectTreeVO.setId(subjectMsg.getGatewayId());
                subjectTreeVO.setParentId(0);
                subjectTreeVO.setLabel(subjectMsg.getName());
                subjectTreeVO.setChildren(null);
                subjectTreeVO.setMsg(subjectMsg);
                subjectTreeVOList.add(subjectTreeVO);
            }
        }
        for (AggrSubject aggrSubject : aggrSubjectList) {
            subjectTreeVO = new SubjectTreeVO();
            subjectTreeVO.setId(aggrSubject.getSubjectId());
            subjectTreeVO.setParentId(aggrSubject.getParentSubjectId() == null ? aggrSubject.getGatewayId() : aggrSubject.getParentSubjectId());
            subjectTreeVO.setLabel(aggrSubject.getName());
            for (SubjectVO subjectMsg : subjectMsgList) {
                if (aggrSubject.getSubjectId().equals(subjectMsg.getSubjectId())) {
                    subjectTreeVO.setMsg(subjectMsg);
                    break;
                }
            }
            subjectTreeVO.setChildren(null);
            subjectTreeVOList.add(subjectTreeVO);
        }
        // 第二步：取得所有用户自定义新增的控件（新增控件只有轻应用）
        List<SubjectPO> addAppByUserList = subjectMapper.getAddAppByUser(userId, code, CodeDetailConstant.WIDGET_TYPE_LIGHTAPP);
        SubjectVO subjectVO;
        for (SubjectPO addAppByUser : addAppByUserList) {
            addAppByUser.setPhotoUrl(template.getObjectURL(MinioBucketEnum.BUCKETNAME_LIGHTAPP.getName(), addAppByUser.getPhotoUrl(), 604800));
            addAppByUser.setLightappName(addAppByUser.getName());
            subjectTreeVO = new SubjectTreeVO();
            subjectTreeVO.setId(-1);
            subjectTreeVO.setParentId(addAppByUser.getParentSubjectId());
            subjectTreeVO.setLabel(addAppByUser.getName());
            subjectVO = new SubjectVO();
            BeanUtils.copyProperties(addAppByUser, subjectVO);
            subjectVO.setGroupMsg(Arrays.asList(addAppByUser.getGroups().split(",")));
            subjectTreeVO.setMsg(subjectVO);
            subjectTreeVO.setChildren(null);
            subjectTreeVOList.add(subjectTreeVO);
        }
        return TreeUtil.build(subjectTreeVOList, 0);
    }
}
