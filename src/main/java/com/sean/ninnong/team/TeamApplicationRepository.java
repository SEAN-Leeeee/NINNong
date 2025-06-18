package com.sean.ninnong.team;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamApplicationRepository extends JpaRepository<TeamApplication, Long> {

}
