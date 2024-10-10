package com.Hostease.Hostease.service;

import com.Hostease.Hostease.model.TipoUsuario;
import com.Hostease.Hostease.repository.ITipoUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class TipoUsuarioService implements ITipoUsuarioService {

    @Autowired
    private ITipoUsuarioRepository tipoUser;


    @Override
    public Optional<TipoUsuario> findById(Long id) {
        return tipoUser.findById(id);
    }

    @Override
    public List<TipoUsuario> findAll() {
        return tipoUser.findAll();
    }

    @Override
    public void deleteById(Long id) {

        tipoUser.deleteById(id);

    }

    @Override
    public TipoUsuario createTipoUsuario(TipoUsuario tipoUsuario) {
        return tipoUser.save(tipoUsuario);
    }

    @Override
    public TipoUsuario editTipoUsuario(TipoUsuario tipoUsuario, Long id) {
        Optional<TipoUsuario> optionalTipo = tipoUser.findById(id);

        if (optionalTipo.isPresent()) {
            TipoUsuario user = optionalTipo.get();

            user.setNombre(tipoUsuario.getNombre());
            return tipoUser.save(user);
        }else {
            throw new RuntimeException("Tipo de Usuario no encontrado con el ID: " + id);
        }

    }
}
