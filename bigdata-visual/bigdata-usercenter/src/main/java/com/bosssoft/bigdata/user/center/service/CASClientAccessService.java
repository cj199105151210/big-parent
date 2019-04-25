package com.bosssoft.bigdata.user.center.service;

import com.bosssoft.bigdata.user.center.mapper.casMapper.RegexRegisteredServiceMapper;
import com.bosssoft.bigdata.usercenter.api.entity.RegexRegisteredService;
import com.bosssoft.bigdata.usercenter.api.entity.RegexRegisteredServiceWithBLOBs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CASClientAccessService {
    @Autowired
    private RegexRegisteredServiceMapper regexRegisteredServiceMapper;

    /**
     * 分页查询
     * @param page
     * @param rows
     * @return
     */
    public List<RegexRegisteredService> pageSelect(int page , int rows){
        int start;
        if(page==1){
           start = 0;
        }else{
            start = (page-1)*rows+1;
        }
       return  regexRegisteredServiceMapper.pageSelect(start,rows);
    }

    /**
     * 总条数查询
     * @return
     */
    public int totalNum(){
        return regexRegisteredServiceMapper.totalNum();
    }

    /**
     * 编辑修改
     * @param regexRegisteredService
     * @return
     */
    public int update(RegexRegisteredServiceWithBLOBs regexRegisteredService) {
      return regexRegisteredServiceMapper.updateByPrimaryKeySelective(regexRegisteredService);
    }


    /**
     * 物理删除
     * @param id
     * @return
     */
    public int delete(int id) {
        return  regexRegisteredServiceMapper.deleteByPrimaryKey(id);
    }

    /**
     * 查询所有
     * @return
     */
    public List<RegexRegisteredService> selectAll() {
        return  regexRegisteredServiceMapper.selectAll();
    }

    /**
     * 根据主键查询
     * @param id
     * @return
     */
    public RegexRegisteredService selectById(int id) {
        return regexRegisteredServiceMapper.selectByPrimaryKey((long) id);
    }

    /**
     * 新建记录
     * @param regexRegisteredService
     * @return
     */
    public int insertRecord(RegexRegisteredServiceWithBLOBs regexRegisteredService) {
        return regexRegisteredServiceMapper.insertSelective(regexRegisteredService);
    }
}
