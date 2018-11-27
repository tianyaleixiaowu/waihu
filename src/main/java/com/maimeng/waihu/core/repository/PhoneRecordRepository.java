package com.maimeng.waihu.core.repository;

import com.maimeng.waihu.core.model.PhoneRecord;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author wuweifeng wrote on 2018/11/12.
 */
public interface PhoneRecordRepository extends JpaRepository<PhoneRecord, Integer> {
    PhoneRecord findFirstByOrderByBegintimeTempDesc();

}
