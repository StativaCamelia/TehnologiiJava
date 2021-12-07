package com.lab3.repo;

import com.lab3.log.Log;
import com.lab3.model.Users;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.Locale;
import java.util.Objects;

@Log
@Stateless
public class UserRepo implements UserRepoBase {

    @PersistenceContext(unitName = "JPAExample")
    private EntityManager em;


    /**
     * Stores a {@link Users} into the database
     *
     * @param user the user that needs to be stored
     */
    @Override
    @Transactional
    public int insertUser(Users user) {

        int result = 0;

        em.persist(user);

        return result;
    }

    @Override
    @Transactional
    public Users login(String username, String password) {

        Query query = em.createNamedQuery("User.findByUsername");
        query.setMaxResults(1);
        query.setParameter("username", username);
        Users user = (Users) query.getSingleResult();
        if (user != null) {
            if (Objects.equals(user.getPassword(), password.toUpperCase(Locale.ROOT))) {
                return user;
            } else {
                return null;
            }
        }
        return user;
    }

    @Transactional
    public Users getStudentById(String id) {

        return em.find(Users.class, id);
    }
}
