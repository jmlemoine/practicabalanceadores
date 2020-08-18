package com.example.demo.service;

import com.example.demo.data.AlquilerRepository;
import com.example.demo.model.Alquiler;
import com.example.demo.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AlquilerServices {

    @Autowired
    AlquilerRepository alquilerRepository;

    @Transactional
    public Alquiler crearAlquiler(Alquiler alquiler) {

        return alquilerRepository.save(alquiler);

    }

    public List<Alquiler> listadoAlquiler() {

        return alquilerRepository.findAll();

    }

    public List<Alquiler> listadoAlquilerOrdenado() {

        return alquilerRepository.alquileresOrdenados();

    }

    public Alquiler getAlquilerPorID(long id) {

        return alquilerRepository.getOne(id);

    }

    public List<Alquiler> historialAlquiler(Cliente cliente) {

        return alquilerRepository.findAllByClienteOrderByFechaDesc(cliente);

    }

}
