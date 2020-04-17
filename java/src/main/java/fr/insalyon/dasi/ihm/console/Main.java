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
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import util.AstroTest;

/**
 *
 * @author Nicolas Trouin et Fabien Narboux
 */
public class Main {

    public static void main(String[] args) {
        JpaUtil.init();
        
        Service service = new Service();
        
            //Tests client

        initialiserClients();            
        //testerRechercheClient();         
        //testerListeClients();            
        //testerAuthentificationClient();
        //saisirInscriptionClient();     
        //saisirRechercheClient();
        
            //Tests employe
        
        //service.initialiserEmployes(Boolean.TRUE);
        //testerAuthentificationEmploye();
        //listerEmployes();
        
            //Tests mediums
        
        //service.initialiserMediums(Boolean.TRUE);
        //listerMediums();
        
            //Tests consultation
        
        //testerPrendreRendezVous();
        //testerDebuterConsultation();
        //testerTerminerConsultation();  
        //testerDebuterConsultation();
        //testerTerminerConsultation();  
        //testerPrendreRendezVous();
        //testerDebuterConsultation();
        //testerTerminerConsultation();  
        //testerClientApresConsultation();
       
            //Tests AstroNetApi

        //testerAstroNetApi();
        //testerPredictions();
        
        //testerStats();
        
        JpaUtil.destroy();
    }
    
    //Affichage

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
    
    //Clients

    public static void initialiserClients() {
        
        System.out.println();
        System.out.println("**** initialiserClients() ****");
        System.out.println();      

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
        
        Client ada = new Client("Lovelace", "Ada", "Mme", date_Ada, "0606060606", "2, Rue des ananas", "ada.lovelace@insa-lyon.fr", "Ada1012");
        Client blaise = new Client("Pascal", "Blaise","M.", date_Blaise, "0607070707", "3, Rue des cocotiers",  "blaise.pascal@insa-lyon.fr", "Blaise1906");
        Client fred = new Client("Fotiadu", "Frédéric","M.", date_Fred, "0608080808", "5, Boulevard des tulipes",  "frederic.fotiadu@insa-lyon.fr", "INSA-Forever");
        
        System.out.println();
        System.out.println("** Clients avant persistance: ");
        afficherClient(ada);
        afficherClient(blaise);
        afficherClient(fred);
        System.out.println();

        Service service = new Service();
        service.inscrireClient(ada);
        service.inscrireClient(blaise);
        service.inscrireClient(fred);
        
        System.out.println();
        System.out.println("** Clients après persistance: ");
        afficherClient(ada);
        afficherClient(blaise);
        afficherClient(fred);
        System.out.println();
    }

    public static void testerRechercheClient() {
        
        System.out.println();
        System.out.println("**** testerRechercheClient() ****");
        System.out.println();
        
        Service service = new Service();
        long id;
        Client client;

        id = 1;
        System.out.println("** Recherche du Client #" + id);
        client = service.rechercherClientParId(id);
        if (client != null) {
            afficherClient(client);
        } else {
            System.out.println("=> Client non-trouvé");
        }

        id = 3;
        System.out.println("** Recherche du Client #" + id);
        client = service.rechercherClientParId(id);
        if (client != null) {
            afficherClient(client);
        } else {
            System.out.println("=> Client non-trouvé");
        }

        id = 17;
        System.out.println("** Recherche du Client #" + id);
        client = service.rechercherClientParId(id);
        if (client != null) {
            afficherClient(client);
        } else {
            System.out.println("=> Client #" + id + " non-trouvé");
        }
    }

    public static void testerListeClients() {
        
        System.out.println();
        System.out.println("**** testerListeClients() ****");
        System.out.println();
        
        Service service = new Service();
        List<Client> listeClients = service.listerClients();
        System.out.println("*** Liste des Clients");
        if (listeClients != null) {
            for (Client client : listeClients) {
                afficherClient(client);
            }
        }
        else {
            System.out.println("=> ERREUR...");
        }
    }

    public static void testerAuthentificationClient() {
        
        System.out.println();
        System.out.println("**** testerAuthentificationClient() ****");
        System.out.println();
        
        Service service = new Service();
        Client client;
        String mail;
        String motDePasse;

        mail = "ada.lovelace@insa-lyon.fr";
        motDePasse = "Ada1012";
        client = service.authentifierClient(mail, motDePasse);
        if (client != null) {
            System.out.println("Authentification réussie avec le mail '" + mail + "' et le mot de passe '" + motDePasse + "'");
            afficherClient(client);
        } else {
            System.out.println("Authentification échouée avec le mail '" + mail + "' et le mot de passe '" + motDePasse + "'");
        }

        mail = "ada.lovelace@insa-lyon.fr";
        motDePasse = "Ada2020";
        client = service.authentifierClient(mail, motDePasse);
        if (client != null) {
            System.out.println("Authentification réussie avec le mail '" + mail + "' et le mot de passe '" + motDePasse + "'");
            afficherClient(client);
        } else {
            System.out.println("Authentification échouée avec le mail '" + mail + "' et le mot de passe '" + motDePasse + "'");
        }

        mail = "etudiant.fictif@insa-lyon.fr";
        motDePasse = "********";
        client = service.authentifierClient(mail, motDePasse);
        if (client != null) {
            System.out.println("Authentification réussie avec le mail '" + mail + "' et le mot de passe '" + motDePasse + "'");
            afficherClient(client);
        } else {
            System.out.println("Authentification échouée avec le mail '" + mail + "' et le mot de passe '" + motDePasse + "'");
        }
    }

    public static void saisirInscriptionClient() {
        Service service = new Service();

        System.out.println();
        System.out.println("Appuyer sur Entrée pour passer la pause...");
        Saisie.pause();

        System.out.println();
        System.out.println("**************************");
        System.out.println("** NOUVELLE INSCRIPTION **");
        System.out.println("**************************");
        System.out.println();

        String nom = Saisie.lireChaine("Nom ? ");
        String prenom = Saisie.lireChaine("Prénom ? ");
        String mail = Saisie.lireChaine("Mail ? ");
        String motDePasse = Saisie.lireChaine("Mot de passe ? ");
        String tel = Saisie.lireChaine("Tel ? ");
        String genre = Saisie.lireChaine("Civilite ?");
        String adresse = Saisie.lireChaine("Adresse ?");
        
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        String dateS = Saisie.lireChaine("Date (dd/MM/yyyy)?");
        Date date = null;
        try {
            date = simpleDateFormat.parse(dateS);
        }
        catch (ParseException ex) {
            // Erreur
        }

        Client client = new Client(nom, prenom, genre, date, tel, adresse, mail, motDePasse);
        Long idClient = service.inscrireClient(client);

        if (idClient != null) {
            System.out.println("> Succès inscription");
        } else {
            System.out.println("> Échec inscription");
        }
        afficherClient(client);

    }

    public static void saisirRechercheClient() {
        Service service = new Service();

        System.out.println();
        System.out.println("*********************");
        System.out.println("** MENU INTERACTIF **");
        System.out.println("*********************");
        System.out.println();

        Saisie.pause();

        System.out.println();
        System.out.println("**************************");
        System.out.println("** RECHERCHE de CLIENTS **");
        System.out.println("**************************");
        System.out.println();
        System.out.println();
        System.out.println("** Recherche par Identifiant:");
        System.out.println();

        Integer idClient = Saisie.lireInteger("Identifiant ? [0 pour quitter] ");
        while (idClient != 0) {
            Client client = service.rechercherClientParId(idClient.longValue());
            if (client != null) {
                afficherClient(client);
            } else {
                System.out.println("=> Client #" + idClient + " non-trouvé");
            }
            System.out.println();
            idClient = Saisie.lireInteger("Identifiant ? [0 pour quitter] ");
        }

        System.out.println();
        System.out.println("********************************");
        System.out.println("** AUTHENTIFICATION de CLIENT **");
        System.out.println("********************************");
        System.out.println();
        System.out.println();
        System.out.println("** Authentifier Client:");
        System.out.println();

        String clientMail = Saisie.lireChaine("Mail ? [0 pour quitter] ");

        while (!clientMail.equals("0")) {
            String clientMotDePasse = Saisie.lireChaine("Mot de passe ? ");
            Client client = service.authentifierClient(clientMail, clientMotDePasse);
            if (client != null) {
                afficherClient(client);
            } else {
                System.out.println("=> Client non-authentifié");
            }
            clientMail = Saisie.lireChaine("Mail ? [0 pour quitter] ");
        }

        System.out.println();
        System.out.println("*****************");
        System.out.println("** AU REVOIR ! **");
        System.out.println("*****************");
        System.out.println();

    }
    
    //Employe
    
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
    
    //Medium
    
    public static void initialiserMediums() {
        
        System.out.println();
        System.out.println("**** initialiserMediums() ****");
        System.out.println();
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DASI-PU");
        EntityManager em = emf.createEntityManager();

        Medium med1 = new Cartomancien("Mme Irma",Genre.Feminin,"Comprenez votre entourage grâce à mes cartes ! Résultats rapides.");
        Medium med2 = new Spirite("Gwenaëlle",Genre.Feminin,"Spécialiste des grandes conversations au-delà de TOUTES les frontières.", "Boule de crital");
        Medium med3 = new Astrologue("Serena",Genre.Feminin,"Basée à Champigny-sur-Marne, Serena vous révèlera votre avenir pour éclairer votre passé.","École Normale Supérieure d’Astrologie (ENS-Astro)",2006);
        Medium med4 = new Spirite("Professeur Tran",Genre.Masculin,"Votre avenir est devant vous : regardons-le ensemble !","Marc de café, boule de cristal, oreilles de lapin");

        
        System.out.println();
        System.out.println("** Mediums avant persistance: ");
        afficherMedium(med1);
        afficherMedium(med2);
        afficherMedium(med3);
        afficherMedium(med4);
        System.out.println();

        try {
            em.getTransaction().begin();
            em.persist(med1);
            em.persist(med2);
            em.persist(med3);
            em.persist(med4);
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
        afficherMedium(med4);
        System.out.println();
    }
    
    //Consultation
    
    public static void testerPrendreRendezVous() {
        
        System.out.println();
        System.out.println("**** testerPrendreRendezVous() ****");
        System.out.println();  

        Service service = new Service();
        Client client = service.rechercherClientParId((long)1);
        Medium medium = service.rechercherMediumParId((long)4);
        

        Consultation consultation = service.prendreRendezVous(client,medium);
        if(consultation != null) {
            System.out.println();
            System.out.println("** Consultation après prise de rendez-vous: ");
            afficherConsultation(consultation);
            System.out.println();
        } else {
            System.out.println("ERREUR");
        }
    }

    private static void testerDebuterConsultation() {
        
        System.out.println();
        System.out.println("**** testerDebuterConsultation() ****");
        System.out.println();
        
        Service service = new Service();
        Consultation c = service.rechercherConsultationParId((long)1);
        service.debuterConsultation(c);
        System.out.println("** Consultation démarrée ");
        System.out.println(c);
        System.out.println();
    }
    
    private static void testerTerminerConsultation() {
        
        System.out.println();
        System.out.println("**** testerTerminerConsultation() ****");
        System.out.println();
        
        Service service = new Service();
        Consultation c = service.rechercherConsultationParId((long)1);
        service.terminerConsultation(c);
        System.out.println("** Consultation terminée ");
        System.out.println(c);
    }
       
    private static void listerMediums() {
        Service service = new Service();
        List<Medium> mediums = service.listerMediums();
        mediums.forEach((medium) -> {
            System.out.println(medium);
        });
    }

    private static void testerClientApresConsultation() {
        Service service = new Service();
        Client client = service.rechercherClientParId((long)1);
        afficherClient(client);
        System.out.println(client.getConsultations());
        System.out.println();
        System.out.println();
    }

    private static void listerEmployes() {
        Service service = new Service();
        List<Employe> employes = service.listerEmployes();
        employes.forEach((employe) -> {
            System.out.println(employe);
        });
    }
    
    // AstroNetApi

    private static void testerAstroNetApi() {
        
        System.out.println();
        System.out.println("**** testerAstroNetApi() ****");
        System.out.println(); 
        
        try {
            AstroTest astroApi = new AstroTest();
            Service service = new Service();
            Client client = service.rechercherClientParId((long)1);
            
            // pour obtenir le profil astro d'un client
            List<String> profilClient = astroApi.getProfil(client.getPrenom(), client.getDateDeNaissance());
            String signeZodiaque = profilClient.get(0);
            String signeChinois = profilClient.get(1);
            String couleur = profilClient.get(2);
            String animal = profilClient.get(3);
            
            System.out.println("signeZo = " + signeZodiaque + " ; signeChi = " + signeChinois + " ; couleur = " + couleur + " ; animal = " + animal);
            
            // pour obtenir des prédictions
            List<String> predictions = astroApi.getPredictions(couleur, animal, 1, 2, 3);
            String predictionAmour = predictions.get(0);
            String predictionSante = predictions.get(1);
            String predictionTravail = predictions.get(2);
            
            System.out.println("predictionAmour = " + predictionAmour + " ; predictionSante = " + predictionSante + " ; predictionTravail = " + predictionTravail);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static void testerStats() {
        Service service = new Service();
        Map<Employe, Long> employes = service.répartitionClientsParEmploye();
        employes.entrySet().forEach((entry) -> {
            System.out.println(entry.getKey() + " -- " + entry.getValue());
        });
    }

    private static void testerPredictions() {
        Service service = new Service();
        Client client = service.rechercherClientParId((long)1);
        List<String> predictions = service.getPredictions(3, 2, 1, client);
        System.out.println("");
        System.out.println("~<[ Prédictions ]>~");
        System.out.println("[ Amour ] " + predictions.get(0));
        System.out.println("[ Santé ] " + predictions.get(1));
        System.out.println("[Travail] " + predictions.get(2));
        System.out.println("");
    }
}