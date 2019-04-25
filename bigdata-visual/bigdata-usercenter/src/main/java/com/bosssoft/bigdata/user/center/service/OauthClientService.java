package com.bosssoft.bigdata.user.center.service;

import com.bosssoft.bigdata.user.center.mapper.oauthMapper.SysOauthClientDetailsMapper;
import com.bosssoft.bigdata.usercenter.api.entity.SysOauthClientDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OauthClientService {

    @Autowired
    private SysOauthClientDetailsMapper sysOauthClientDetailsMapper;


    /**
     * 编辑修改
     * @param sysOauthClientDetails
     * @return
     */
    public int update(SysOauthClientDetails sysOauthClientDetails, String oldClientId) {
        String clientId = sysOauthClientDetails.getClientId();
        String clientSecret = sysOauthClientDetails.getClientSecret();
        return sysOauthClientDetailsMapper.updateSelective(clientId, clientSecret, oldClientId);
    }

    /**
     * 物理删除
     * @param clientId
     * @return
     */
    public int delete(String clientId) {
        return sysOauthClientDetailsMapper.deleteByPrimaryKey(clientId);
    }

    /**
     * 查询所有
     * @return
     */
    public List<SysOauthClientDetails> selectAll() {
        return sysOauthClientDetailsMapper.selectAll();
    }

    /**
     * 根据主键查询信息
     * @param clientId
     * @return
     */
    public SysOauthClientDetails selectById(String clientId) {
        return sysOauthClientDetailsMapper.selectByPrimaryKey(clientId);
    }

    /**
     * 新增记录
     * @param sysOauthClientDetails
     * @return
     */
    public int insertRecord(SysOauthClientDetails sysOauthClientDetails) {
        return sysOauthClientDetailsMapper.insertSelective(sysOauthClientDetails);
    }
}
