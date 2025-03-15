package com.example.subcriptionsmanagments_api.repository;

import com.example.subcriptionsmanagments_api.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
}
