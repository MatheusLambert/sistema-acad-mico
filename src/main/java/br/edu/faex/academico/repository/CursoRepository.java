package br.edu.faex.academico.repository;

import br.edu.faex.academico.config.Conexao;
import br.edu.faex.academico.model.Curso;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CursoRepository {
    private Curso converter(ResultSet rs) throws SQLException {
        Curso item = new Curso();
        item.setId(rs.getLong("id"));
        item.setNome(rs.getString("nome"));
        item.setModalidade(rs.getString("modalidade"));
        return item;
    }

    public void salvar(Curso item) {
        String sql = "INSERT INTO cursos (nome, modalidade) VALUES (?, ?)";
        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, item.getNome());
            stmt.setString(2, item.getModalidade());
            stmt.executeUpdate();
            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) item.setId(keys.getLong(1));
            }
            System.out.println("Curso cadastrado com sucesso.");
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar curso: " + e.getMessage());
        }
    }

    public List<Curso> listar() {
        List<Curso> itens = new ArrayList<>();
        String sql = "SELECT * FROM cursos ORDER BY id";
        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) itens.add(converter(rs));
        } catch (SQLException e) {
            System.out.println("Erro ao listar cursos: " + e.getMessage());
        }
        return itens;
    }

    public Curso buscarPorId(Long id) {
        String sql = "SELECT * FROM cursos WHERE id = ?";
        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return converter(rs);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar curso: " + e.getMessage());
        }
        return null;
    }

    public void excluir(Long id) {
        String sql = "DELETE FROM cursos WHERE id = ?";
        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao excluir curso: " + e.getMessage());
        }
    }

    public void atualizar(Curso item) {
        String sql = "UPDATE cursos SET nome = ?, modalidade = ? WHERE id = ?";
        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, item.getNome());
            stmt.setString(2, item.getModalidade());
            stmt.setLong(3, item.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar curso: " + e.getMessage());
        }
    }
}
