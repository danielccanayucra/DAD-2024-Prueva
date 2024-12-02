package jdcy.com.mscategory.repository;

import jdcy.com.mscategory.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
