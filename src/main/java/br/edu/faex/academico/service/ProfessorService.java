package br.edu.faex.academico.service;

import br.edu.faex.academico.model.Professor;
import br.edu.faex.academico.repository.ProfessorRepository;
import java.util.List;

public class ProfessorService {
    private final ProfessorRepository repository;

    public ProfessorService(ProfessorRepository repository) {
        this.repository = repository;
    }

    private boolean validar(Professor item) {
        if (item == null) return false;
        if (item.getNome() == null || item.getNome().isBlank()) {
            System.out.println("Nome é obrigatório.");
            return false;
        }
        if (item.getNome().length() > 150) {
            System.out.println("Nome excede o tamanho permitido.");
            return false;
        }
        if (item.getEmail() == null || item.getEmail().isBlank()) {
            System.out.println("Email é obrigatório.");
            return false;
        }
        if (item.getEmail().length() > 150) {
            System.out.println("Email excede o tamanho permitido.");
            return false;
        }
        if (!item.getEmail().matches("^[^@ ]+@[^@ ]+\\.[^@ ]+$")) {
            System.out.println("E-mail inválido.");
            return false;
        }
        if (repository.listar().stream().anyMatch(p -> p.getEmail().equalsIgnoreCase(item.getEmail()) && (item.getId() == null || !p.getId().equals(item.getId())))) {
            System.out.println("E-mail já cadastrado.");
            return false;
        }
        return true;
    }

    public void cadastrar(Professor item) {
        if (validar(item)) repository.salvar(item);
    }

    public List<Professor> listar() {
        return repository.listar();
    }

    public Professor buscarPorId(Long id) {
        return id == null ? null : repository.buscarPorId(id);
    }

    public void excluir(Long id) {
        if (buscarPorId(id) == null) {
            System.out.println("Professor não encontrado.");
            return;
        }
        repository.excluir(id);
    }

    public void atualizar(Professor item) {
        if (item == null || buscarPorId(item.getId()) == null) {
            System.out.println("Professor não encontrado.");
            return;
        }
        if (validar(item)) repository.atualizar(item);
    }
}
