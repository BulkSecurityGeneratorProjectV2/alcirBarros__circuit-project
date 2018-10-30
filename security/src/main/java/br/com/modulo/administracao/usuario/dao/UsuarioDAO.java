package br.com.modulo.administracao.usuario.dao;

import br.com.modulo.administracao.aluno.model.Usuario;
import com.exception.BusinessException;
import java.text.Normalizer;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioDAO {

    @PersistenceContext
    private EntityManager em;

    public Usuario carregar(String login) {
        try {
            StringBuilder query = new StringBuilder();
            query.append("SELECT ");
            query.append("    usr.* ");
            query.append("FROM ");
            query.append("    tbusuarios usr ");
            query.append("WHERE ");
            query.append("    usr.tbusuarios_login = :login ");
            Query createNativeQuery = em.createNativeQuery(query.toString(), Usuario.class);
            createNativeQuery.setParameter("login", removerCaracteresEspeciais(login));
            return (Usuario) createNativeQuery.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (NonUniqueResultException rx) {
            throw new BusinessException("Registro duplicado: " + login);
        }
    }

    public static String removerCaracteresEspeciais(String str) {
        return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^a-zZ-Z1-9 ]", "");
    }
}
