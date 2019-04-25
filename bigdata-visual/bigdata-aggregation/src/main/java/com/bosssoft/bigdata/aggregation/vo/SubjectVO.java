package com.bosssoft.bigdata.aggregation.vo;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @author bosx code aggregation
 * @date 2019-03-11 17:38:46
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SubjectVO extends Model<SubjectVO> {
    private static final long serialVersionUID = 1L;

    /**
     * 栏目表主键
     */
    private Integer subjectId;
    /**
     * 门户信息表主键
     */
    private Integer gatewayId;
    /**
     * 类型标识_数据字典：100
     */
    @NotBlank(message = "类型不能为空")
    private String typeFlag;
    /**
     * 名称
     */
    @NotBlank(message = "名称不能为空")
    private String name;
    /**
     * 排序
     */
    @NotNull(message = "排序不能为空")
    private Integer sort;
    /**
     * 状态
     */
    @NotBlank(message = "启用状态不能为空")
    private String status;
    /**
     * 显示性质_数据字典：101
     */
    @NotBlank(message = "显示性质不能为空")
    private String showType;
    /**
     * 操作类型_数据字典：102
     */
    private String operationType;
    /**
     * 对应父结点在当前表的主键
     */
    private Integer parentSubjectId;
    /**
     * 创建者
     */
    private String createUser;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 用户组信息
     */
    private List<String> groupMsg;
    /**
     * 轻应用图标url
     */
    private String photoUrl;
    /**
     * 轻应用主键
     */
    private Integer lightappId;
    /**
     * 轻应用名称
     */
    private String lightappName;
    /**
     * 数据、超链接url
     */
    private String url;
    /**
     * 描述
     */
    private String describeMsg;
    /**
     * 打开方式
     */
    private String openMode;
    /**
     * 门户编码
     */
    private String code;
    /**
     * 门户类型
     */
    private String type;
    /**
     * 轮播图信息
     */
    private List<Map<String, Object>> slideShowMsg;

}
