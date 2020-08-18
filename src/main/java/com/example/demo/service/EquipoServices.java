package com.example.demo.service;

import com.example.demo.data.EquipoRepository;
import com.example.demo.model.Equipo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class EquipoServices {

    @Autowired
    EquipoRepository equipoRepository;

    @Transactional
    public Equipo crearEquipo(Equipo equipo) {

        return equipoRepository.save(equipo);

    }

    public List<Equipo> listadoEquipos() {

        return equipoRepository.findAll();

    }

    public void eliminarEquipo(long id) {

        equipoRepository.deleteById(id);

    }

    public Equipo getEquipoPorID(long id) {

        return equipoRepository.getOne(id);

    }

}
