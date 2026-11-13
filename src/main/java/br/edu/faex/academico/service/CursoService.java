package br.edu.faex.academico.service;

import br.edu.faex.academico.model.Curso;
import br.edu.faex.academico.repository.CursoRepository;
import java.util.List;

public class CursoService {
    private final CursoRepository repository;

    public CursoService(CursoRepository repository) {
        this.repository = repository;
    }

    private boolean validar(Curso item) {
        if (item == null) return false;
        if (item.getNome() == null || item.getNome().isBlank()) {
            System.out.println("Nome é obrigatório.");
            return false;
        }
        if (item.getNome().length() > 150) {
            System.out.println("Nome excede o tamanho permitido.");
            return false;
        }
        if (item.getModalidade() == null || item.getModalidade().isBlank()) {
            System.out.println("Modalidade é obrigatório.");
            return false;
        }
        if (item.getModalidade().length() > 100) {
            System.out.println("Modalidade excede o tamanho permitido.");
            return false;
        }
        return true;
    }

    public void cadastrar(Curso item) {
        if (validar(item)) repository.salvar(item);
    }

    public List<Curso> listar() {
        return repository.listar();
    }

    public Curso buscarPorId(Long id) {
        return id == null ? null : repository.buscarPorId(id);
    }

    public void excluir(Long id) {
        if (buscarPorId(id) == null) {
            System.out.println("Curso não encontrado.");
            return;
        }
        repository.excluir(id);
    }

    public void atualizar(Curso item) {
        if (item == null || buscarPorId(item.getId()) == null) {
            System.out.println("Curso não encontrado.");
            return;
        }
        if (validar(item)) repository.atualizar(item);
    }
}
