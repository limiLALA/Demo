package com.ninn.demo.repository;

import com.ninn.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
//    @Query(value="select * from #(#name) u where u.name=?1", nativeQuery = true)
//    List<User> findByName(String name);



}
