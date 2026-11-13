package br.edu.faex.academico.controller;

import br.edu.faex.academico.model.Professor;
import br.edu.faex.academico.service.ProfessorService;
import java.util.List;

public class ProfessorController {
    private final ProfessorService service;

    public ProfessorController(ProfessorService service) {
        this.service = service;
    }

    public void cadastrar(Professor item) { service.cadastrar(item); }
    public List<Professor> listar() { return service.listar(); }
    public Professor buscarPorId(Long id) { return service.buscarPorId(id); }
    public void excluir(Long id) { service.excluir(id); }
    public void atualizar(Professor item) { service.atualizar(item); }
}
