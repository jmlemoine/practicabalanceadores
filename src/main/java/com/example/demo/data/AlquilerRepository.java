package com.example.demo.data;

import com.example.demo.model.Alquiler;
import com.example.demo.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AlquilerRepository extends JpaRepository<Alquiler, Long> {

    List<Alquiler> findAllByClienteOrderByFechaDesc(Cliente cliente);

    @Query("SELECT alquiler FROM Alquiler alquiler ORDER BY alquiler.fecha")
    List<Alquiler> alquileresOrdenados();

}
