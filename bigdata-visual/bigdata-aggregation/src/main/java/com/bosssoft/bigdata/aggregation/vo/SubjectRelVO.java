package com.bosssoft.bigdata.aggregation.vo;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(description = "用户自定义门户页面内容实体")
public class SubjectRelVO {
    private static final long serialVersionUID = 1L;

    /**
     * 栏目表主键
     */
    @ApiModelProperty(value = "栏目表主键")
    private Integer subjectId;
    /**
     * 轻应用主键
     */
    @ApiModelProperty(value = "轻应用主键")
    private Integer lightappId;
    /**
     * 对应父结点在当前表的主键
     */
    @ApiModelProperty(value = "对应父结点在栏目表的主键")
    private Integer parentSubjectId;
    /**
     * 门户码
     */
    @ApiModelProperty(value = "门户码")
    private String gatewayCode;
    /**
     * 显示状态
     */
    @ApiModelProperty(value = "显示状态（1：新增，0：移除）")
    private String showStatus;
}
