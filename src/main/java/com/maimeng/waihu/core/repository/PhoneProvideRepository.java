package com.maimeng.waihu.core.repository;

import com.maimeng.waihu.core.model.PhoneProvide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author wuweifeng wrote on 2018/11/12.
 */
public interface PhoneProvideRepository extends JpaRepository<PhoneProvide, Integer> {
    List<PhoneProvide> findByIdBetween(Integer beginId, Integer endId);

    /**
     * 查第一个
     * @return PhoneProvide
     */
    PhoneProvide findFirstByOrderById();

    @Modifying
    @Query("update PhoneProvide p set p.subid = ?1 where p.subname = ?2")
    void updateSubid(String subid, String name);


    List<PhoneProvide> findBySubid(String subid);
}
