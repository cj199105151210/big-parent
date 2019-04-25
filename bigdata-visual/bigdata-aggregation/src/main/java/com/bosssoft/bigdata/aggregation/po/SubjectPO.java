package com.bosssoft.bigdata.aggregation.po;

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
public class SubjectPO extends Model<SubjectPO> {
    private static final long serialVersionUID = 1L;
    /**
     * 门户信息表主键
     */
    private Integer gatewayId;
    /**
     * 类型标识_数据字典：100
     */
    private String typeFlag;
    /**
     * 名称
     */
    private String name;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 状态
     */
    private String status;
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
    private String groups;
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

}
