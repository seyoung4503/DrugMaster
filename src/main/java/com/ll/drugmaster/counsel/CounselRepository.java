package com.ll.drugmaster.counsel;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CounselRepository extends JpaRepository<Counsel, Long> {
    Page<Counsel> findAll(Pageable pageable);
    Page<Counsel> findByMemberMemberName(String memberName, Pageable pageable);

}
