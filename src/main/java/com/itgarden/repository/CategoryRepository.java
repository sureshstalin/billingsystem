package com.itgarden.repository;

import com.itgarden.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/*
 * Created by Suresh Stalin on 23 / Nov / 2020.
 */

public interface CategoryRepository extends JpaRepository<Category,Long> {

//    select * from category where category_code='CAT21728' and is_deleted=0
    Category findCategoryByCategoryCodeAndDeletedFalse(String categoryCode);
}
