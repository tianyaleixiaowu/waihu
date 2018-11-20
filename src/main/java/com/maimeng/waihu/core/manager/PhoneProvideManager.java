package com.maimeng.waihu.core.manager;

import com.maimeng.waihu.core.model.PhoneProvide;
import com.maimeng.waihu.core.repository.PhoneProvideRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wuweifeng wrote on 2018/11/20.
 */
@Service
public class PhoneProvideManager {
    @Resource
    private PhoneProvideRepository phoneProvideRepository;

    public List<PhoneProvide> findByIdBetween(Integer beginId, Integer endId) {
        return phoneProvideRepository.findByIdBetween(beginId, endId);
    }
    
}
