package com.example.repository;

import com.example.pojo.ClassroomApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ClassroomApplicationRepository extends JpaRepository<ClassroomApplication, String> {
    @Transactional
    @Modifying
    @Query("delete from ClassroomApplication c where c.userId = ?1 and c.classroomId = ?2 and c.needTime = ?3")
    void deleteApplicationByUserIdAndClassroomIdAndNeedTime(String userId, String classroomId, String needTime);
}
