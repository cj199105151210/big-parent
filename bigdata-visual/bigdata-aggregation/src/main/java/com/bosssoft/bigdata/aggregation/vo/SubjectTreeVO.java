package com.bosssoft.bigdata.aggregation.vo;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.bosssoft.bigdata.admin.api.dto.TreeNode;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author bosx code aggregation
 * @date 2019-03-11 17:38:46
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SubjectTreeVO extends TreeNode {
    private static final long serialVersionUID = 1L;

    /**
     * label
     */
    private String label;
    /**
     * children
     */
    private SubjectVO msg;

}
