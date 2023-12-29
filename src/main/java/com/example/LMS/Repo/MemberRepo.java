package com.example.LMS.Repo;

import com.example.LMS.Models.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepo extends JpaRepository<Member, Long> {


}
