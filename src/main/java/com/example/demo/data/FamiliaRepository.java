package com.example.demo.data;

import com.example.demo.model.Familia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FamiliaRepository extends JpaRepository<Familia, Long> {

    Familia findById(long id);

}
