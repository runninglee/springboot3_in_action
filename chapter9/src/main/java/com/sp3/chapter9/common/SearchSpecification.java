package com.sp3.chapter9.common;


import org.springframework.data.jpa.domain.Specification;

public class SearchSpecification<T> {
    
    public Specification<T> searchMobile(String mobile) {
        return (root, query, criteriaBuilder) -> {
            if (mobile == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("mobile"), mobile);
        };
    }

    public Specification<T> searchKeywords(String keywords) {
        return (root, query, criteriaBuilder) -> {
            if (keywords == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(root.get("keywords"), "%" + keywords + "%");
        };
    }
}