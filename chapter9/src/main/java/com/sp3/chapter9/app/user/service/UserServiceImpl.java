package com.sp3.chapter9.app.user.service;

import com.sp3.chapter9.app.user.entity.User;
import com.sp3.chapter9.app.user.repository.UserRepository;
import com.sp3.chapter9.app.user.request.CreateUserRequest;
import com.sp3.chapter9.app.user.request.UserQueryRequest;
import com.sp3.chapter9.common.SearchSpecification;
import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl {

    @Resource
    UserRepository userRepository;

    @Resource
    private ModelMapper modelMapper;


    @PersistenceContext
    private EntityManager entityManager;


    @Transactional
    @Modifying
    public void store(CreateUserRequest request) {
        userRepository.save(modelMapper.map(request, User.class));
    }

    public HashMap<Object, Object> getListByCriteria(UserQueryRequest request) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = cb.createQuery(User.class);
        Root<User> user = query.from(User.class);
        Predicate criteria = cb.conjunction();
        if (request.getMobile() != null) {
            criteria = cb.and(criteria, cb.equal(user.get("mobile"), request.getMobile()));
        }
        if (request.getKeywords() != null) {
            criteria = cb.and(criteria, cb.like(user.get("keywords"), "%" + request.getKeywords() + "%"));
        }
        query.where(criteria);
        // 统计
        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        Root<User> countRoot = countQuery.from(User.class);
        countQuery.select(cb.count(countRoot)).where(criteria);
        Long total = entityManager.createQuery(countQuery).getSingleResult();
        // 分页
        Pageable pageable = PageRequest.of(request.getPage(), request.getPageSize());
        List<User> data = entityManager.createQuery(query)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();
        return new HashMap<>() {{
            put("data", data);
            put("page", pageable);
            put("total", total);
        }};
    }

    public Object getListBySpecification(UserQueryRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getPageSize());
        SearchSpecification<User> searchSpecification = new SearchSpecification<>();
        Specification<User> spec =
                Specification.where(searchSpecification.searchMobile(request.getMobile())).
                and(searchSpecification.searchKeywords(request.getKeywords()));
        Long total = userRepository.count(spec);
        return new HashMap<>() {{
            put("data", userRepository.findAll(spec, pageable));
            put("page", pageable);
            put("total", total);
        }};
    }

}
