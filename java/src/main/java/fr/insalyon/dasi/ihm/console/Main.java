package fr.insalyon.dasi.ihm.console;

import fr.insalyon.dasi.dao.JpaUtil;
import fr.insalyon.dasi.metier.modele.Astrologue;
import fr.insalyon.dasi.metier.modele.Cartomancien;
import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.modele.Genre;
import fr.insalyon.dasi.metier.modele.Medium;
import fr.insalyon.dasi.metier.modele.ProfilAstral;
import fr.insalyon.dasi.metier.modele.Spirite;
import fr.insalyon.dasi.metier.service.Service;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Nicolas Trouin et Fabien Narboux
 */
public class Main {

    public static void main(String[] args) {

        // TODO : Pensez à créer une unité de persistance "DASI-PU" et à vérifier son nom dans la classe JpaUtil
        // Contrôlez l'affichage du log de JpaUtil grâce à la méthode log de la classe JpaUtil
        JpaUtil.init();

        initialiserClients();            // Question 3
        //testerInscriptionClient();       // Question 4 & 5
        //testerRechercheClient();         // Question 6
        //testerListeClients();            // Question 7
        //testerAuthentificationClient();  // Question 8
        //saisirInscriptionClient();       // Question 9
        //saisirRechercheClient();
        
        initialiserEmployes();
        
        initialiserMediums();
        
        initialiserConsultation();
        
        testerClientApresConsultation();
        
        listerMediums();
        
        testerAuthentificationEmploye();
        
        JpaUtil.destroy();
    }

    public static void afficherClient(Client client) {
        System.out.println("-> " + client);
    }
    
    public static void afficherEmploye(Employe employe) {
        System.out.println("-> " + employe);
    }
    
    public static void afficherMedium(Medium medium) {
        System.out.println("-> " + medium);
    }
    
    public static void afficherConsultation(Consultation consultation) {
        System.out.println("-> " + consultation);
    }

    public static void initialiserClients() {
        
        System.out.println();
        System.out.println("**** initialiserClients() ****");
        System.out.println();
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DASI-PU");
        EntityManager em = emf.createEntityManager();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        String dateAda = "1852-11-27";
        String dateBlaise = "1623-6-19";
        String dateFred = "1950-5-6";
        Date date_Ada = null;
        Date date_Blaise = null;
        Date date_Fred = null;
        try {
            date_Ada = simpleDateFormat.parse(dateAda);
            date_Blaise = simpleDateFormat.parse(dateBlaise);
            date_Fred = simpleDateFormat.parse(dateFred);
        }
        catch (ParseException ex) {
            // Erreur
        }
        
        ProfilAstral profilAda = new ProfilAstral("Verseau", "Tigre", "Bleu", "Cochon");
        Client ada = new Client("Lovelace", "Ada", "Mme", date_Ada, "0606060606", "2, Rue des ananas", "ada.lovelace@insa-lyon.fr", "Ada1012");
        ada.setProfilAstral(profilAda);
        
        Client blaise = new Client("Pascal", "Blaise","M.", date_Blaise, "0607070707", "3, Rue des cocotiers",  "blaise.pascal@insa-lyon.fr", "Blaise1906");
        Client fred = new Client("Fotiadu", "Frédéric","M.", date_Fred, "0608080808", "5, Boulevard des tulipes",  "frederic.fotiadu@insa-lyon.fr", "INSA-Forever");
        
        System.out.println();
        System.out.println("** Clients avant persistance: ");
        afficherClient(ada);
        afficherClient(blaise);
        afficherClient(fred);
        System.out.println();

        try {
            em.getTransaction().begin();
            em.persist(ada);
            em.persist(blaise);
            em.persist(fred);
            em.getTransaction().commit();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service", ex);
            try {
                em.getTransaction().rollback();
            }
            catch (IllegalStateException ex2) {
                // Ignorer cette exception...
            }
        } finally {
            em.close();
        }

        System.out.println();
        System.out.println("** Clients après persistance: ");
        afficherClient(ada);
        afficherClient(blaise);
        afficherClient(fred);
        System.out.println();
    }
    
    public static void initialiserEmployes() {
        
        System.out.println();
        System.out.println("**** initialiserEmployes() ****");
        System.out.println();
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DASI-PU");
        EntityManager em = emf.createEntityManager();

        Employe emp1 = new Employe("SING", "Ainhoa", Genre.Feminin, "0705224200", "4 Rue Phelypeaux, Villeurbanne", "asing8183@free.fr", "employe1");
        Employe emp2 = new Employe("ABDIULLINA", "David Alexander", Genre.Masculin, "0590232772", "8 Rue Wilhelmine, Villeurbanne", "david-alexander.abdiullina@laposte.net", "employe2");
        Employe emp3 = new Employe("WOAGNER", "Moez", Genre.Masculin, "0832205629", "6 Rue Camille Koechlin, Villeurbanne", "moez.woagner@laposte.net", "employe3");

        
        System.out.println();
        System.out.println("** Employes avant persistance: ");
        afficherEmploye(emp1);
        afficherEmploye(emp2);
        afficherEmploye(emp3);
        System.out.println();

        try {
            em.getTransaction().begin();
            em.persist(emp1);
            em.persist(emp2);
            em.persist(emp3);
            em.getTransaction().commit();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service", ex);
            try {
                em.getTransaction().rollback();
            }
            catch (IllegalStateException ex2) {
                // Ignorer cette exception...
            }
        } finally {
            em.close();
        }

        System.out.println();
        System.out.println("** Employes après persistance: ");
        afficherEmploye(emp1);
        afficherEmploye(emp2);
        afficherEmploye(emp3);
        System.out.println();
    }
    
    public static void initialiserMediums() {
        
        System.out.println();
        System.out.println("**** initialiserMediums() ****");
        System.out.println();
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DASI-PU");
        EntityManager em = emf.createEntityManager();

        Medium med1 = new Cartomancien("Mme Irma",Genre.Feminin,"Comprenez votre entourage grâce à mes cartes ! Résultats rapides.");
        Medium med2 = new Spirite("Gwenaëlle",Genre.Feminin,"Spécialiste des grandes conversations au-delà de TOUTES les frontières.", "Boule de crital");
        Medium med3 = new Astrologue("Serena",Genre.Feminin,"Basée à Champigny-sur-Marne, Serena vous révèlera votre avenir pour éclairer votre passé.","École Normale Supérieure d’Astrologie (ENS-Astro)",2006);

        
        System.out.println();
        System.out.println("** Mediums avant persistance: ");
        afficherMedium(med1);
        afficherMedium(med2);
        afficherMedium(med3);
        System.out.println();

        try {
            em.getTransaction().begin();
            em.persist(med1);
            em.persist(med2);
            em.persist(med3);
            em.getTransaction().commit();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service", ex);
            try {
                em.getTransaction().rollback();
            }
            catch (IllegalStateException ex2) {
                // Ignorer cette exception...
            }
        } finally {
            em.close();
        }

        System.out.println();
        System.out.println("** Mediums après persistance: ");
        afficherMedium(med1);
        afficherMedium(med2);
        afficherMedium(med3);
        System.out.println();
    }
    
    public static void initialiserConsultation() {
        
        System.out.println();
        System.out.println("**** initialiserConsultation() ****");
        System.out.println();
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DASI-PU");
        EntityManager em = emf.createEntityManager();
        
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        String dateC = "2020-04-03";
        Date date_C = null;

        try {
            date_C = simpleDateFormat.parse(dateC);
        }
        catch (ParseException ex) {
            // Erreur
        }

        Service service = new Service();
        Client client = service.rechercherClientParId((long)1);
        Employe employe = service.rechercherEmployeParId((long)1);
        Medium medium = service.rechercherMediumParId((long)1);

        Consultation c1 = new Consultation(date_C,client,employe,medium);
        
        
        System.out.println();
        System.out.println("** Consultation avant persistance: ");
        afficherConsultation(c1);
        System.out.println();

        service.insererConsultation(c1);

        System.out.println();
        System.out.println("** Consultation après persistance: ");
        afficherConsultation(c1);
        System.out.println(client.getConsultations());
        System.out.println();
    }

    private static void listerMediums() {
        Service service = new Service();
        List<Medium> mediums = service.listerMediums();
        mediums.forEach((medium) -> {
            System.out.println(medium);
        });
    }
    
    public static void testerAuthentificationEmploye() {
        System.out.println();
        System.out.println("**** testerAuthentificationEmploye() ****");
        System.out.println();
        
        Service service = new Service();
        Employe employe;
        String mail;
        String motDePasse;

        mail = "david-alexander.abdiullina@laposte.net";
        motDePasse = "employe2";
        employe = service.authentifierEmploye(mail, motDePasse);
        if (employe != null) {
            System.out.println("Authentification réussie avec le mail '" + mail + "' et le mot de passe '" + motDePasse + "'");
            afficherEmploye(employe);
        } else {
            System.out.println("Authentification échouée avec le mail '" + mail + "' et le mot de passe '" + motDePasse + "'");
        }

        mail = "moez.woagner@laposte.net";
        motDePasse = "employe3";
        employe = service.authentifierEmploye(mail, motDePasse);
        if (employe != null) {
            System.out.println("Authentification réussie avec le mail '" + mail + "' et le mot de passe '" + motDePasse + "'");
            afficherEmploye(employe);
        } else {
            System.out.println("Authentification échouée avec le mail '" + mail + "' et le mot de passe '" + motDePasse + "'");
        }

        mail = "employe.fictif@insa-lyon.fr";
        motDePasse = "********";
        employe = service.authentifierEmploye(mail, motDePasse);
        if (employe != null) {
            System.out.println("Authentification réussie avec le mail '" + mail + "' et le mot de passe '" + motDePasse + "'");
            afficherEmploye(employe);
        } else {
            System.out.println("Authentification échouée avec le mail '" + mail + "' et le mot de passe '" + motDePasse + "'");
        }
    }


//    public static void testerInscriptionClient() {
//        
//        System.out.println();
//        System.out.println("**** testerInscriptionClient() ****");
//        System.out.println();
//        
//        Service service = new Service();
//        Client claude = new Client("Chappe", "Claude", "claude.chappe@insa-lyon.fr", "HaCKeR");
//        Long idClaude = service.inscrireClient(claude);
//        if (idClaude != null) {
//            System.out.println("> Succès inscription");
//        } else {
//            System.out.println("> Échec inscription");
//        }
//        afficherClient(claude);
//
//        Client hedy = new Client("Lamarr", "Hedy", "hlamarr@insa-lyon.fr", "WiFi");
//        Long idHedy = service.inscrireClient(hedy);
//        if (idHedy != null) {
//            System.out.println("> Succès inscription");
//        } else {
//            System.out.println("> Échec inscription");
//        }
//        afficherClient(hedy);
//
//        Client hedwig = new Client("Lamarr", "Hedwig Eva Maria", "hlamarr@insa-lyon.fr", "WiFi");
//        Long idHedwig = service.inscrireClient(hedwig);
//        if (idHedwig != null) {
//            System.out.println("> Succès inscription");
//        } else {
//            System.out.println("> Échec inscription");
//        }
//        afficherClient(hedwig);
//    }
//
//    public static void testerRechercheClient() {
//        
//        System.out.println();
//        System.out.println("**** testerRechercheClient() ****");
//        System.out.println();
//        
//        Service service = new Service();
//        long id;
//        Client client;
//
//        id = 1;
//        System.out.println("** Recherche du Client #" + id);
//        client = service.rechercherClientParId(id);
//        if (client != null) {
//            afficherClient(client);
//        } else {
//            System.out.println("=> Client non-trouvé");
//        }
//
//        id = 3;
//        System.out.println("** Recherche du Client #" + id);
//        client = service.rechercherClientParId(id);
//        if (client != null) {
//            afficherClient(client);
//        } else {
//            System.out.println("=> Client non-trouvé");
//        }
//
//        id = 17;
//        System.out.println("** Recherche du Client #" + id);
//        client = service.rechercherClientParId(id);
//        if (client != null) {
//            afficherClient(client);
//        } else {
//            System.out.println("=> Client #" + id + " non-trouvé");
//        }
//    }
//
//    public static void testerListeClients() {
//        
//        System.out.println();
//        System.out.println("**** testerListeClients() ****");
//        System.out.println();
//        
//        Service service = new Service();
//        List<Client> listeClients = service.listerClients();
//        System.out.println("*** Liste des Clients");
//        if (listeClients != null) {
//            for (Client client : listeClients) {
//                afficherClient(client);
//            }
//        }
//        else {
//            System.out.println("=> ERREUR...");
//        }
//    }
//
//    public static void testerAuthentificationClient() {
//        
//        System.out.println();
//        System.out.println("**** testerAuthentificationClient() ****");
//        System.out.println();
//        
//        Service service = new Service();
//        Client client;
//        String mail;
//        String motDePasse;
//
//        mail = "ada.lovelace@insa-lyon.fr";
//        motDePasse = "Ada1012";
//        client = service.authentifierClient(mail, motDePasse);
//        if (client != null) {
//            System.out.println("Authentification réussie avec le mail '" + mail + "' et le mot de passe '" + motDePasse + "'");
//            afficherClient(client);
//        } else {
//            System.out.println("Authentification échouée avec le mail '" + mail + "' et le mot de passe '" + motDePasse + "'");
//        }
//
//        mail = "ada.lovelace@insa-lyon.fr";
//        motDePasse = "Ada2020";
//        client = service.authentifierClient(mail, motDePasse);
//        if (client != null) {
//            System.out.println("Authentification réussie avec le mail '" + mail + "' et le mot de passe '" + motDePasse + "'");
//            afficherClient(client);
//        } else {
//            System.out.println("Authentification échouée avec le mail '" + mail + "' et le mot de passe '" + motDePasse + "'");
//        }
//
//        mail = "etudiant.fictif@insa-lyon.fr";
//        motDePasse = "********";
//        client = service.authentifierClient(mail, motDePasse);
//        if (client != null) {
//            System.out.println("Authentification réussie avec le mail '" + mail + "' et le mot de passe '" + motDePasse + "'");
//            afficherClient(client);
//        } else {
//            System.out.println("Authentification échouée avec le mail '" + mail + "' et le mot de passe '" + motDePasse + "'");
//        }
//    }
//
//    public static void saisirInscriptionClient() {
//        Service service = new Service();
//
//        System.out.println();
//        System.out.println("Appuyer sur Entrée pour passer la pause...");
//        Saisie.pause();
//
//        System.out.println();
//        System.out.println("**************************");
//        System.out.println("** NOUVELLE INSCRIPTION **");
//        System.out.println("**************************");
//        System.out.println();
//
//        String nom = Saisie.lireChaine("Nom ? ");
//        String prenom = Saisie.lireChaine("Prénom ? ");
//        String mail = Saisie.lireChaine("Mail ? ");
//        String motDePasse = Saisie.lireChaine("Mot de passe ? ");
//
//        Client client = new Client(nom, prenom, mail, motDePasse);
//        Long idClient = service.inscrireClient(client);
//
//        if (idClient != null) {
//            System.out.println("> Succès inscription");
//        } else {
//            System.out.println("> Échec inscription");
//        }
//        afficherClient(client);
//
//    }
//
//    public static void saisirRechercheClient() {
//        Service service = new Service();
//
//        System.out.println();
//        System.out.println("*********************");
//        System.out.println("** MENU INTERACTIF **");
//        System.out.println("*********************");
//        System.out.println();
//
//        Saisie.pause();
//
//        System.out.println();
//        System.out.println("**************************");
//        System.out.println("** RECHERCHE de CLIENTS **");
//        System.out.println("**************************");
//        System.out.println();
//        System.out.println();
//        System.out.println("** Recherche par Identifiant:");
//        System.out.println();
//
//        Integer idClient = Saisie.lireInteger("Identifiant ? [0 pour quitter] ");
//        while (idClient != 0) {
//            Client client = service.rechercherClientParId(idClient.longValue());
//            if (client != null) {
//                afficherClient(client);
//            } else {
//                System.out.println("=> Client #" + idClient + " non-trouvé");
//            }
//            System.out.println();
//            idClient = Saisie.lireInteger("Identifiant ? [0 pour quitter] ");
//        }
//
//        System.out.println();
//        System.out.println("********************************");
//        System.out.println("** AUTHENTIFICATION de CLIENT **");
//        System.out.println("********************************");
//        System.out.println();
//        System.out.println();
//        System.out.println("** Authentifier Client:");
//        System.out.println();
//
//        String clientMail = Saisie.lireChaine("Mail ? [0 pour quitter] ");
//
//        while (!clientMail.equals("0")) {
//            String clientMotDePasse = Saisie.lireChaine("Mot de passe ? ");
//            Client client = service.authentifierClient(clientMail, clientMotDePasse);
//            if (client != null) {
//                afficherClient(client);
//            } else {
//                System.out.println("=> Client non-authentifié");
//            }
//            clientMail = Saisie.lireChaine("Mail ? [0 pour quitter] ");
//        }
//
//        System.out.println();
//        System.out.println("*****************");
//        System.out.println("** AU REVOIR ! **");
//        System.out.println("*****************");
//        System.out.println();
//
//    }

    private static void testerClientApresConsultation() {
        Service service = new Service();
        Client client = service.rechercherClientParId((long)1);
        afficherClient(client);
        System.out.println(client.getConsultations());
        System.out.println();
        System.out.println();
    }
}
