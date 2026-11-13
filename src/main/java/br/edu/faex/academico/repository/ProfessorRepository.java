package br.edu.faex.academico.repository;

import br.edu.faex.academico.config.Conexao;
import br.edu.faex.academico.model.Professor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfessorRepository {
    private Professor converter(ResultSet rs) throws SQLException {
        Professor item = new Professor();
        item.setId(rs.getLong("id"));
        item.setNome(rs.getString("nome"));
        item.setEmail(rs.getString("email"));
        return item;
    }

    public void salvar(Professor item) {
        String sql = "INSERT INTO professores (nome, email) VALUES (?, ?)";
        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, item.getNome());
            stmt.setString(2, item.getEmail());
            stmt.executeUpdate();
            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) item.setId(keys.getLong(1));
            }
            System.out.println("Professor cadastrado com sucesso.");
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar professor: " + e.getMessage());
        }
    }

    public List<Professor> listar() {
        List<Professor> itens = new ArrayList<>();
        String sql = "SELECT * FROM professores ORDER BY id";
        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) itens.add(converter(rs));
        } catch (SQLException e) {
            System.out.println("Erro ao listar professores: " + e.getMessage());
        }
        return itens;
    }

    public Professor buscarPorId(Long id) {
        String sql = "SELECT * FROM professores WHERE id = ?";
        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return converter(rs);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar professor: " + e.getMessage());
        }
        return null;
    }

    public void excluir(Long id) {
        String sql = "DELETE FROM professores WHERE id = ?";
        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao excluir professor: " + e.getMessage());
        }
    }

    public void atualizar(Professor item) {
        String sql = "UPDATE professores SET nome = ?, email = ? WHERE id = ?";
        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, item.getNome());
            stmt.setString(2, item.getEmail());
            stmt.setLong(3, item.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar professor: " + e.getMessage());
        }
    }
}
