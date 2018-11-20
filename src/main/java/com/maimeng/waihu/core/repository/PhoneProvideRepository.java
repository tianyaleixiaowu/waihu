package com.maimeng.waihu.core.repository;

import com.maimeng.waihu.core.model.PhoneProvide;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author wuweifeng wrote on 2018/11/12.
 */
public interface PhoneProvideRepository extends JpaRepository<PhoneProvide, Integer> {
    List<PhoneProvide> findByIdBetween(Integer beginId, Integer endId);
}
