package com.sp3.chapter15.app.batch.repository;


import com.sp3.chapter15.app.batch.entity.ApiLog;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiLogRepository extends CrudRepository<ApiLog, Long>, JpaSpecificationExecutor<ApiLog> {
}
