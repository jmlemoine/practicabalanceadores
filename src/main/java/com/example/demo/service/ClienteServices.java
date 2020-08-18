package com.example.demo.service;

import com.example.demo.data.ClienteRepository;
import com.example.demo.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ClienteServices {

    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional
    public Cliente crearCliente(Cliente cliente) {

        clienteRepository.save(cliente);
        return cliente;

    }

    public List<Cliente> getListadoDeClientes() {

        return clienteRepository.findAll();

    }

    public void eliminarCliente(long id) {

        clienteRepository.deleteById(id);

    }

    public Cliente getClientePorID(long id) {

        return clienteRepository.getOne(id);

    }


}
