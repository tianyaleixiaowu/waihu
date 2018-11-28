package com.maimeng.waihu.core.repository;

import com.maimeng.waihu.core.model.Sub;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author wuweifeng wrote on 2018/11/12.
 */
public interface SubRepository extends JpaRepository<Sub, Integer> {
    Sub findFirstBySubid(String subid);

    Sub findFirstByName(String name);
}
