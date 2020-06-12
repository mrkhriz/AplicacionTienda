package com.personal.persistence.repo;

import com.personal.persistence.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.math.BigInteger;
import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, BigInteger> {

    List<Client> findByEmail(String email);

}
