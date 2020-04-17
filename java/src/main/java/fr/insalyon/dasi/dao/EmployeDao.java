package fr.insalyon.dasi.dao;

import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.modele.Genre;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;

/**
 *
 * @author Nicolas Trouin et Fabien Narboux
 */
public class EmployeDao {
    
    public void creer(Employe employe) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.persist(employe);
    }
    
    public void modifier(Employe employe) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.merge(employe);
    }
    
    public Employe chercherParId(Long employeId) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        return em.find(Employe.class, employeId); // renvoie null si l'identifiant n'existe pas
    }
    
    public Employe chercherParMail(String clientMail) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Employe> query = em.createQuery("SELECT e FROM Employe e WHERE e.mail = :mail", Employe.class);
        query.setParameter("mail", clientMail); // correspond au paramètre ":mail" dans la requête
        List<Employe> employes = query.getResultList();
        Employe result = null;
        if (!employes.isEmpty()) {
            result = employes.get(0); // premier de la liste
        }
        return result;
    }

    public List<Employe> listerEmployes() {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Employe> query = em.createQuery("SELECT e FROM Employe e ORDER BY e.nom ASC, e.prenom ASC", Employe.class);
        return query.getResultList();
    }
    
    public Map<Employe,Long> nombreClientsParEmploye() {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        Map<Employe, Long> result = new HashMap();
        List<Object[]> resultList = em.createQuery("SELECT c.employe, count(distinct c.client) as nb FROM Consultation c GROUP BY c.employe").getResultList();

        // Place results in map
        resultList.forEach((tuple) -> {
            result.put((Employe)tuple[0], (long)tuple[1]);
        });
        
        return result;
    }
    
    public Employe chercherParGenreEtDisponible(Genre mediumGenre) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Employe> query = em.createQuery("SELECT e FROM Employe e WHERE e.genre = :genre and e.estOccupe = FALSE", Employe.class);
        query.setParameter("genre", mediumGenre); // correspond au paramètre ":genre" dans la requête
        List<Employe> employes = query.getResultList();
        Employe result = null;
        if (!employes.isEmpty()) {
            // Ordre croissant
            Collections.sort(employes, (Employe employe1, Employe employe2) -> employe1.getConsultations().size() > employe2.getConsultations().size() ? 1 : (employe1.getConsultations().size() < employe2.getConsultations().size()) ? -1 : 0);
            result = employes.get(0); // premier de la liste, celui avec le moins de consultations
        }
        return result;
    }
}
