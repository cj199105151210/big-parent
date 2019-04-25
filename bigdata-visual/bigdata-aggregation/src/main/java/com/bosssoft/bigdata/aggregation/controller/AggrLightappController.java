package com.bosssoft.bigdata.aggregation.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bosssoft.bigdata.aggregation.constant.CodeDetailConstant;
import com.bosssoft.bigdata.aggregation.constant.MinioBucketEnum;
import com.bosssoft.bigdata.aggregation.entity.AggrWidgetRel;
import com.bosssoft.bigdata.aggregation.service.AggrWidgetRelService;
import com.bosssoft.bigdata.common.core.util.R;
import com.bosssoft.bigdata.common.core.utils.DateTimeUtil;
import com.bosssoft.bigdata.common.core.utils.DateUtil;
import com.bosssoft.bigdata.common.log.annotation.SysLog;
import com.bosssoft.bigdata.aggregation.entity.AggrLightapp;
import com.bosssoft.bigdata.aggregation.service.AggrLightappService;
import com.bosssoft.bigdata.common.minio.service.MinioTemplate;
import com.bosssoft.bigdata.common.security.util.SecurityUtils;
import com.bosssoft.bigdata.usercenter.api.feign.RemoteUserService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.*;


/**
 * @author bosx code aggregation
 * @date 2019-04-04 10:38:01
 */
@RestController
@AllArgsConstructor
@RequestMapping("/aggrlightapp")
@Api(value = "aggrlightapp", tags = "Aggr轻应用信息表")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class, SQLException.class})
public class AggrLightappController {

    private final AggrLightappService aggrLightappService;
    private final RemoteUserService remoteUserService;
    private final MinioTemplate template;
    private final AggrWidgetRelService aggrWidgetRelService;

    /**
     * 分页查询
     *
     * @param page         分页对象
     * @param aggrLightapp
     * @return
     */
    @GetMapping("/page")
    public R getAggrLightappPage(Page page, AggrLightapp aggrLightapp) {
        // 查询所有轻应用用户组信息
        Map<Integer, List<String>> lightAppGroups = new HashMap<>();
        QueryWrapper<AggrWidgetRel> aggrWidgetRelQueryWrapper = new QueryWrapper<>();
        aggrWidgetRelQueryWrapper.eq("type", CodeDetailConstant.WIDGET_TYPE_LIGHTAPP);
        List<AggrWidgetRel> aggrWidgetRelList = aggrWidgetRelService.list(aggrWidgetRelQueryWrapper);
        for (AggrWidgetRel aggrWidgetRel : aggrWidgetRelList) {
            List<String> groups = lightAppGroups.get(aggrWidgetRel.getSubjectId());
            if (groups == null || groups.size() == 0) {
                groups = new ArrayList<>();
            }
            groups.add(aggrWidgetRel.getGroupId());
            lightAppGroups.put(aggrWidgetRel.getSubjectId(), groups);
        }

        QueryWrapper<AggrLightapp> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(aggrLightapp.getName())) {
            queryWrapper.like("name", aggrLightapp.getName());
        }
        if (StringUtils.isNotBlank(aggrLightapp.getServiceCategory())) {
            queryWrapper.eq("service_category", aggrLightapp.getServiceCategory());
        }
        if (StringUtils.isNotBlank(aggrLightapp.getServiceTopice())) {
            queryWrapper.like("service_topice", aggrLightapp.getServiceTopice());
        }
        if (StringUtils.isNotBlank(aggrLightapp.getServiceMode())) {
            queryWrapper.eq("service_mode", aggrLightapp.getServiceMode());
        }
        if (StringUtils.isNotBlank(aggrLightapp.getServiceDpt())) {
            queryWrapper.eq("service_dpt", aggrLightapp.getServiceDpt());
        }
        if (aggrLightapp.getGroups() != null && aggrLightapp.getGroups().size() == 1) {
            List<Integer> lightappIds = new ArrayList<>();
            for (Map.Entry<Integer, List<String>> entry : lightAppGroups.entrySet()) {
                if (entry.getValue().contains(aggrLightapp.getGroups().get(0))) {
                    lightappIds.add(entry.getKey());
                }
            }
            queryWrapper.in("lightapp_id", lightappIds);
        }
        IPage<AggrLightapp> aggrLightappIPage = aggrLightappService.page(page, queryWrapper);
        List<AggrLightapp> aggrLightappList = aggrLightappIPage.getRecords();
        for (AggrLightapp aggrLightapp1 : aggrLightappList) {
            aggrLightapp1.setPhotoName(aggrLightapp1.getPhotoUrl());
            aggrLightapp1.setPhotoUrl(template.getObjectURL(MinioBucketEnum.BUCKETNAME_LIGHTAPP.getName(), aggrLightapp1.getPhotoUrl(), 604800));
            aggrLightapp1.setGroups(lightAppGroups.get(aggrLightapp1.getLightappId()) == null ? new ArrayList<String>() : lightAppGroups.get(aggrLightapp1.getLightappId()));
            List<String> serviceTime = new ArrayList<>();
            if (StringUtils.isNotBlank(aggrLightapp1.getStartTime())) {
                serviceTime.add(aggrLightapp1.getStartTime());
            }
            if (StringUtils.isNotBlank(aggrLightapp1.getEndTime())) {
                serviceTime.add(aggrLightapp1.getEndTime());
            }
            aggrLightapp1.setServiceTime(serviceTime);
        }
        return new R<>(aggrLightappIPage);
    }


    /**
     * 通过id查询
     *
     * @param lightappId id
     * @return R
     */
    @GetMapping("/{lightappId}")
    public R getById(@PathVariable("lightappId") Integer lightappId) {
        return new R<>(aggrLightappService.getById(lightappId));
    }

    /**
     * 新增
     *
     * @param aggrLightapp
     * @return R
     */
    @SysLog("新增")
    @PostMapping
    public R save(@RequestBody AggrLightapp aggrLightapp) {
        String username = SecurityUtils.getUser().getUsername();
        LocalDateTime nowDate = LocalDateTime.now();
        aggrLightapp.setCreateUser(username);
        aggrLightapp.setCreateTime(nowDate);
        aggrLightapp.setPhotoUrl(aggrLightapp.getPhotoName());
        List<String> serviceDate = aggrLightapp.getServiceTime();
        if (serviceDate != null && serviceDate.size() == 2) {
            aggrLightapp.setStartTime(serviceDate.get(0));
            aggrLightapp.setEndTime(serviceDate.get(1));
        }
        aggrLightappService.save(aggrLightapp);
        // 对应用户组信息全删全插
        AggrWidgetRel aggrWidgetRel = new AggrWidgetRel();
        aggrWidgetRel.setSubjectId(aggrLightapp.getLightappId());
        aggrWidgetRel.setType(CodeDetailConstant.WIDGET_TYPE_LIGHTAPP);
        aggrWidgetRelService.remove(new QueryWrapper<>(aggrWidgetRel));
        // 插入控件用户组关系表
        List<AggrWidgetRel> aggrWidgetRelList = new ArrayList<>();
        for (String m : aggrLightapp.getGroups()) {
            aggrWidgetRel = new AggrWidgetRel();
            aggrWidgetRel.setSubjectId(aggrLightapp.getLightappId());
            aggrWidgetRel.setType(CodeDetailConstant.WIDGET_TYPE_LIGHTAPP);
            aggrWidgetRel.setGroupId(m);
            aggrWidgetRel.setCreateUser(username);
            aggrWidgetRel.setCreateTime(nowDate);
            aggrWidgetRelList.add(aggrWidgetRel);
        }
        aggrWidgetRelService.saveBatch(aggrWidgetRelList);
        return new R<>("保存成功！");
    }

    /**
     * 修改
     *
     * @param aggrLightapp
     * @return R
     */
    @SysLog("修改")
    @PutMapping
    public R updateById(@RequestBody AggrLightapp aggrLightapp) {
        String username = SecurityUtils.getUser().getUsername();
        LocalDateTime nowDate = LocalDateTime.now();
        aggrLightapp.setCreateUser(username);
        aggrLightapp.setCreateTime(nowDate);
        aggrLightapp.setPhotoUrl(aggrLightapp.getPhotoName());
        List<String> serviceDate = aggrLightapp.getServiceTime();
        if (serviceDate != null && serviceDate.size() == 2) {
            aggrLightapp.setStartTime(serviceDate.get(0));
            aggrLightapp.setEndTime(serviceDate.get(1));
        }
        aggrLightappService.updateById(aggrLightapp);
        // 对应用户组信息全删全插
        AggrWidgetRel aggrWidgetRel = new AggrWidgetRel();
        aggrWidgetRel.setSubjectId(aggrLightapp.getLightappId());
        aggrWidgetRel.setType(CodeDetailConstant.WIDGET_TYPE_LIGHTAPP);
        aggrWidgetRelService.remove(new QueryWrapper<>(aggrWidgetRel));
        // 插入控件用户组关系表
        List<AggrWidgetRel> aggrWidgetRelList = new ArrayList<>();
        for (String m : aggrLightapp.getGroups()) {
            aggrWidgetRel = new AggrWidgetRel();
            aggrWidgetRel.setSubjectId(aggrLightapp.getLightappId());
            aggrWidgetRel.setType(CodeDetailConstant.WIDGET_TYPE_LIGHTAPP);
            aggrWidgetRel.setGroupId(m);
            aggrWidgetRel.setCreateUser(username);
            aggrWidgetRel.setCreateTime(nowDate);
            aggrWidgetRelList.add(aggrWidgetRel);
        }
        aggrWidgetRelService.saveBatch(aggrWidgetRelList);
        return new R<>("修改成功！");
    }

    /**
     * 通过id删除
     *
     * @param lightappId id
     * @return R
     */
    @SysLog("删除")
    @DeleteMapping("/{lightappId}")
    public R removeById(@PathVariable Integer lightappId) {
        AggrWidgetRel aggrWidgetRel = new AggrWidgetRel();
        aggrWidgetRel.setSubjectId(lightappId);
        aggrWidgetRel.setType(CodeDetailConstant.WIDGET_TYPE_LIGHTAPP);
        aggrWidgetRelService.remove(new QueryWrapper<>(aggrWidgetRel));
        return new R<>(aggrLightappService.removeById(lightappId));
    }

    /**
     * 删除（批量）
     *
     * @param lightappIds
     * @return
     */
    @PostMapping("/batchDelObj")
    public R batchDelObj(@RequestBody List<String> lightappIds) {
        aggrLightappService.removeByIds(lightappIds);
        QueryWrapper<AggrWidgetRel> aggrWidgetRelQueryWrapper = new QueryWrapper<>();
        aggrWidgetRelQueryWrapper.eq("type", CodeDetailConstant.WIDGET_TYPE_LIGHTAPP);
        aggrWidgetRelQueryWrapper.in("subject_id", lightappIds);
        aggrWidgetRelService.remove(aggrWidgetRelQueryWrapper);
        return new R<>("msg", "批量删除成功");
    }

    /**
     * 获取全部组织机构
     *
     * @return
     */
    @GetMapping("/organization/getAll")
    public R getAllOrganization() {
        return remoteUserService.getAllOrganization();
    }

    /**
     * 图片上传
     *
     * @param file 需要上传的文件
     * @return
     */
    @PostMapping("/upload")
    public Map<String, String> createObject(MultipartFile file) throws Exception {
        String objectName = "";
        String fileName[] = file.getOriginalFilename().split("\\.");
        if (fileName.length == 2) {
            objectName = String.valueOf(System.currentTimeMillis()) + "." + fileName[1];
        } else {
            throw new Exception("请勿在文件名中包含“.”！");
        }
        if (file.getSize() / 1048576 > 5) {
            throw new Exception("上传图片大小不能超过 5MB!！");
        }
        template.putObject(MinioBucketEnum.BUCKETNAME_LIGHTAPP.getName(), objectName, file.getInputStream(), file.getSize(), file.getContentType());
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("url", template.getObjectURL(MinioBucketEnum.BUCKETNAME_LIGHTAPP.getName(), objectName, 300));
        resultMap.put("fileName", objectName);
        return resultMap;
    }
}
