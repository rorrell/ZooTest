/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rorrell.zootest.data;

import java.io.Serializable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author rachel
 */
@NoRepositoryBean
public interface EntityRepository<T, ID extends Serializable>
    extends PagingAndSortingRepository<T, ID> {
    default Sort sortByPropertyAsc(String property) {
        return new Sort(Sort.Direction.ASC, property);
    }
}
