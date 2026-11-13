package br.edu.faex.academico;

import br.edu.faex.academico.controller.AlunoController;
import br.edu.faex.academico.model.Aluno;
import br.edu.faex.academico.repository.AlunoRepository;
import br.edu.faex.academico.service.AlunoService;

import br.edu.faex.academico.model.Curso;
import br.edu.faex.academico.model.Professor;
import br.edu.faex.academico.repository.CursoRepository;
import br.edu.faex.academico.repository.ProfessorRepository;
import br.edu.faex.academico.service.CursoService;
import br.edu.faex.academico.service.ProfessorService;
import br.edu.faex.academico.controller.CursoController;
import br.edu.faex.academico.controller.ProfessorController;
import java.util.List;

public class Main {
    static void main() {
        Aluno aluno1 = new Aluno("Aleandro Lima", "aleandro.lima2@faex.edu.br");
        Aluno aluno2 = new Aluno("Maria Lima", "maria.lima2@faex.edu.br");
        Aluno aluno3 = new Aluno("José Lima", "jose.lima2@faex.edu.br");

        AlunoRepository repository = new AlunoRepository();
        AlunoService service = new AlunoService(repository);
        AlunoController controller = new AlunoController(service);
        //Cadastro de Alunos BD
        controller.cadastrar(aluno1);
        controller.cadastrar(aluno2);
        controller.cadastrar(aluno3);

        // Listar Alunos BD
        System.out.println("\n===== LISTA DE ALUNOS =====");

        List<Aluno> alunos = controller.listar();

        for (Aluno aluno : alunos) {
            System.out.println("ID: " + aluno.getId());
            System.out.println("Nome: " + aluno.getNome());
            System.out.println("E-mail: " + aluno.getEmail());
            System.out.println("Ativo: " + aluno.isAtivo());
            System.out.println("--------------------------");
        }

        Aluno aluno = controller.buscarPorId(1L);
        if (aluno != null) {
            System.out.println("Aluno encontrado!");
            System.out.println("ID: " + aluno.getId());
            System.out.println("Nome: " + aluno.getNome());
            System.out.println("E-mail: " + aluno.getEmail());
        } else {
            System.out.println("Aluno não encontrado!");
        }

        // CURSOS
        CursoRepository cursoRepository = new CursoRepository();
        CursoService cursoService = new CursoService(cursoRepository);
        CursoController cursoController = new CursoController(cursoService);
        cursoController.cadastrar(new Curso("Análise e Desenvolvimento de Sistemas", "Presencial"));
        cursoController.cadastrar(new Curso("Sistemas de Informação", "EAD"));
        System.out.println("\n===== LISTA DE CURSOS =====");
        List<Curso> cursos = cursoController.listar();
        for (Curso curso : cursos) {
            System.out.println("ID: " + curso.getId());
            System.out.println("Nome: " + curso.getNome());
            System.out.println("Modalidade: " + curso.getModalidade());
            System.out.println("--------------------------");
        }
        if (!cursos.isEmpty()) {
            Long idCurso = cursos.get(0).getId();
            cursoController.excluir(idCurso);
            System.out.println("\nCurso excluído!");
        }

        // PROFESSORES
        ProfessorRepository professorRepository = new ProfessorRepository();
        ProfessorService professorService = new ProfessorService(professorRepository);
        ProfessorController professorController = new ProfessorController(professorService);
        professorController.cadastrar(new Professor("Carlos Oliveira", "carlos.oliveira@faex.edu.br"));
        professorController.cadastrar(new Professor("Ana Souza", "ana.souza@faex.edu.br"));
        System.out.println("\n===== LISTA DE PROFESSORES =====");
        List<Professor> professores = professorController.listar();
        for (Professor professor : professores) {
            System.out.println("ID: " + professor.getId());
            System.out.println("Nome: " + professor.getNome());
            System.out.println("E-mail: " + professor.getEmail());
            System.out.println("--------------------------");
        }
        if (!professores.isEmpty()) {
            Long idProfessor = professores.get(0).getId();
            professorController.excluir(idProfessor);
            System.out.println("\nProfessor excluído!");
        }
    }
}
