package br.edu.faex.academico.repository;

import br.edu.faex.academico.model.Aluno;

import java.util.ArrayList;
import java.util.List;

public class AlunoRepository {
    private List<Aluno> alunos = new ArrayList<>();

    public void salvar(Aluno aluno){
        alunos.add(aluno);
    }

    public List<Aluno> listar(){
        return alunos;
    }

    public Aluno buscarPorId(Long id){
        for (Aluno aluno : alunos){
            if (aluno.getId().equals(id)){
                return aluno;
            }
        }
        return null;
    }

    public void atualizar(Aluno alunoEditado) {
        for (Aluno aluno : alunos) {
            if (aluno.getId().equals(alunoEditado.getId())) {
                aluno.setNome(alunoEditado.getNome());
                aluno.setEmail(alunoEditado.getEmail());
                return;
            }
        }
    }

    public void excluir(Long id) {
        for (int i = 0; i < alunos.size(); i++) {
            if (alunos.get(i).getId().equals(id)) {
                alunos.remove(i);
                return;
            }
        }
    }
}
