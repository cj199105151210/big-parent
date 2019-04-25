package com.bosssoft.bigdata.aggregation.service;

import com.bosssoft.bigdata.aggregation.entity.AggrSubjectRel;
import com.bosssoft.bigdata.aggregation.vo.SubjectRelVO;
import com.bosssoft.bigdata.aggregation.vo.SubjectTreeVO;
import com.bosssoft.bigdata.aggregation.vo.SubjectVO;
import com.bosssoft.bigdata.common.core.util.R;

import java.util.List;

/**
 * <p>
 * 栏目页面 服务类
 * </p>
 *
 * @author lifei
 * @since 2019-03-14
 */
public interface SubjectService {
    /**
     * 查询树结构方法
     *
     * @return 每个门户所包含栏目的树形结构
     */
    List<SubjectTreeVO> getSubjectTree();

    /**
     * 查询控件详细信息方法
     *
     * @param subjectIdList 《栏目表》主键
     * @return 查询对应《控件信息表》和《栏目表》取得控件详细信息
     */
    List<SubjectVO> getSubjectById(List<Integer> subjectIdList, List<Integer> gatewayIdList);

    /**
     * 新增下级
     *
     * @param subjectVO 页面数据
     * @return 插入结果
     */
    R addOrUpdateTreeNode(SubjectVO subjectVO);

    /**
     * 删除下级
     *
     * @param subjectId 栏目表主键
     * @return 删除结果
     */
    R deleteTreeNode(Integer subjectId);

    /**
     * 保存用户自定义栏目信息
     *
     * @param subjectRelVO 自定义栏目信息
     * @return 处理结果
     */
    R subjectRel(SubjectRelVO subjectRelVO);

    /**
     * 根据登录用户获取页面内容
     *
     * @param code 门户码
     * @return 页面内容
     */
    List<SubjectTreeVO> getGatewayPage(String code);
}
