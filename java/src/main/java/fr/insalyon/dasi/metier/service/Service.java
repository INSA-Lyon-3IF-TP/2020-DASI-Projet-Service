package fr.insalyon.dasi.metier.service;

import util.Message;
import fr.insalyon.dasi.dao.ClientDao;
import fr.insalyon.dasi.dao.ConsultationDao;
import fr.insalyon.dasi.dao.EmployeDao;
import fr.insalyon.dasi.dao.JpaUtil;
import fr.insalyon.dasi.dao.MediumDao;
import fr.insalyon.dasi.metier.modele.Astrologue;
import fr.insalyon.dasi.metier.modele.Cartomancien;
import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.modele.Genre;
import fr.insalyon.dasi.metier.modele.Medium;
import fr.insalyon.dasi.metier.modele.ProfilAstral;
import fr.insalyon.dasi.metier.modele.Spirite;
import java.util.ArrayList;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.OptimisticLockException;
import util.AstroTest;

/**
 *
 * @author Nicolas Trouin et Fabien Narboux
 */
public class Service {

    protected ClientDao clientDao = new ClientDao();
    protected EmployeDao employeDao = new EmployeDao();
    protected MediumDao mediumDao = new MediumDao();
    protected ConsultationDao consultationDao = new ConsultationDao();
    
    // Services d'initialisation
    
    public void initialiserEmployes(Boolean DEBUG){
        System.out.println();
        System.out.println("**** initialiserEmployes() ****");
        System.out.println();
        
        List<Employe> employes = new ArrayList();
        Employe emp1 = new Employe("SING", "Ainhoa", Genre.Feminin, "0705224200", "4 Rue Phelypeaux, Villeurbanne", "asing8183@free.fr", "employe1");
        Employe emp2 = new Employe("ABDIULLINA", "David Alexander", Genre.Masculin, "0590232772", "8 Rue Wilhelmine, Villeurbanne", "david-alexander.abdiullina@laposte.net", "employe2");
        Employe emp3 = new Employe("WOAGNER", "Moez", Genre.Masculin, "0832205629", "6 Rue Camille Koechlin, Villeurbanne", "moez.woagner@laposte.net", "employe3");
        Employe emp4 = new Employe("LOURONT", "Sébastien", Genre.Masculin, "0705816871", "Rue Hippolyte Kahn, Villeurbanne", "sebastien.louront@yahoo.com", "employe4");
        Employe emp5 = new Employe("JIAN", "Sébastien", Genre.Masculin, "0395730422", "Rue Marcel Dutartre, Villeurbanne", "sjian@outlook.com", "employe5");
        Employe emp6 = new Employe("ZOBONGE", "Rafael Armando", Genre.Masculin, "0257054651", "Rue Jean Claude Vivant, Villeurbanne", "rzobonge8769@laposte.net", "employe6");
        Employe emp7 = new Employe("MENETTO", "Catalin", Genre.Feminin, "0964474543", "Rue de l'Union, Villeurbanne", "cmenetto1960@gmail.com", "employe7");
        Employe emp8 = new Employe("DEVELLE", "Servane", Genre.Feminin, "0829249483", "Allée Athena, Villeurbanne", "servane.develle@outlook.com", "employe8");
        Employe emp9 = new Employe("IGUIL", "Guillaume", Genre.Masculin, "0810286833", "Avenue du Commandant l'Herminier, Villeurbanne", "guillaume.iguil-forrer@free.fr", "employe9");
        Employe emp10 = new Employe("ZIHLAKE", "Nicolas", Genre.Masculin, "0508618538", "Rue Joseph Gillet, Villeurbanne", "nicolas.zihlake@outlook.com", "employe10");
        Employe emp11 = new Employe("IGARWAL", "Manon", Genre.Feminin, "0871922334", "Avenue Marcel Cerdan, Villeurbanne", "manon.igarwal@laposte.net", "employe11");
        Employe emp12 = new Employe("SENCHIZ", "Marc", Genre.Masculin, "0551035127", "Rue Mansard, Villeurbanne", "msenchiz1104@laposte.net", "employe12");
        Employe emp13 = new Employe("TIMAILLON", "Dan", Genre.Masculin, "0938101703", "Rue Philippe Verzier, Villeurbanne", "dan.timaillon@outlook.com", "employe13");
        Employe emp14 = new Employe("CIMOTS", "Lucas", Genre.Masculin, "0905584011", "Rue du Foyer, Villeurbanne", "lucas.cimots@gmail.com", "employe14");
        Employe emp15 = new Employe("CHAS", "Chongguang", Genre.Masculin, "0661727682", "Rue Louis Braille, Villeurbanne", "cchas9019@outlook.com", "employe15");
        
        employes.add(emp1);
        employes.add(emp2);
        employes.add(emp3);
        employes.add(emp4);
        employes.add(emp5);
        employes.add(emp6);
        employes.add(emp7);
        employes.add(emp8);
        employes.add(emp9);
        employes.add(emp10);
        employes.add(emp11);
        employes.add(emp12);
        employes.add(emp13);
        employes.add(emp14);
        employes.add(emp15);
        
        JpaUtil.creerContextePersistance();
        try {
            JpaUtil.ouvrirTransaction();

            employes.forEach((employe) -> employeDao.creer(employe));
            
            JpaUtil.validerTransaction();

        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service initialiserEmployes()", ex);
            JpaUtil.annulerTransaction();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        

        System.out.println();
        if(DEBUG){
            System.out.println("** Employes après persistance: ");
            employes.forEach((employe) -> System.out.println(employe));
        }
        System.out.println();
        
    }
    
    public void initialiserMediums(Boolean DEBUG){
        System.out.println();
        System.out.println("**** initialiserMediums() ****");
        System.out.println();
        
        List<Medium> mediums = new ArrayList();
        Medium med1 = new Cartomancien("Mme Irma",Genre.Feminin,"Comprenez votre entourage grâce à mes cartes ! Résultats rapides.");
        Medium med2 = new Spirite("Gwenaëlle",Genre.Feminin,"Spécialiste des grandes conversations au-delà de TOUTES les frontières.", "Boule de crital");
        Medium med3 = new Astrologue("Serena",Genre.Feminin,"Basée à Champigny-sur-Marne, Serena vous révèlera votre avenir pour éclairer votre passé.","École Normale Supérieure d’Astrologie (ENS-Astro)",2006);
        Medium med4 = new Cartomancien("Endora",Genre.Feminin,"Mes cartes répondront à toutes vos questions personnelles.");
        Medium med5 = new Spirite("Professeur Tran",Genre.Masculin,"Votre avenir est devant vous : regardons-le ensemble !","Marc de café, boule de cristal, oreilles de lapin");
        Medium med6 = new Astrologue("Mr M",Genre.Masculin,"Avenir, avenir, que nous réserves-tu ? N'attendez plus, demandez à me consulter!","Institut des Nouveaux Savoirs Astrologiques",2010);
        Medium med7 = new Cartomancien("Steph",Genre.Masculin,"Mathémagicien le plus expérimenté au monde, mes cartes donneront un sens à votre vie !");
        Medium med8 = new Spirite("Mathias Moronzono",Genre.Masculin,"Ma maîtrise parfaite de la mémoire me permettra de remonter dans votre esprit.", "Le pointeur, et surtout l'objet pointé !");
        Medium med9 = new Astrologue("Dr. Roualt",Genre.Masculin,"Le docteur Roualt est particulièrement connu pour prédire la mort.","Astrologie Marseille Union (AMU)", 1999);
        Medium med10 = new Cartomancien("Picachou",Genre.Masculin,"Mes cartes ne manquent pas d'énergie !");
        Medium med11 = new Spirite("Fred Lebuisson",Genre.Feminin,"Je modéliserai votre avenir à la perfection !", "Beautiful english accent");
        Medium med12 = new Astrologue("Nolwann Leruy",Genre.Feminin,"J'entends beaucoup de choses, même des animaux","BZH", 2002);
        Medium med13 = new Cartomancien("Patrock Bréul",Genre.Masculin,"Je lis dans les cartes de poker comme dans le jeu de tarot.");
        Medium med14 = new Spirite("Jean-Ma Le Stylo",Genre.Masculin,"Pour un futur plus clair, je renierai votre passé", "Des cendres");
        Medium med15 = new Astrologue("Mama Mitha",Genre.Feminin,"Avec moi, vos problèmes ne seront plus que de TOUS PETITS souvenirs !","École des Anges Gardiens", 2005);
        Medium med16 = new Cartomancien("Roneud",Genre.Masculin,"Spécialiste de tous les maux, vous aidera à rester toujours debout.");
        Medium med17 = new Spirite("Éric Guérhône",Genre.Masculin,"Je vous propose d'avoir une image limpide de votre futur.", "Tutos vidéos");
        Medium med18 = new Astrologue("Marie Cumin",Genre.Feminin,"Marie pourra analyser vos ondes pour vous aider à devenir radieuse !","École supérieure de physique et de chimie industrielles de la ville de Paris (ESCPI)", 2005);
        Medium med19 = new Cartomancien("Mary Poppers",Genre.Feminin,"Nous volerons à travers votre futur");
        Medium med20 = new Spirite("Jean-Cul Maironchon",Genre.Masculin,"Dans votre esprit, il trouvera 600 000 voies", "VIe République");
        
        mediums.add(med1);
        mediums.add(med2);
        mediums.add(med3);
        mediums.add(med4);
        mediums.add(med5);
        mediums.add(med6);
        mediums.add(med7);
        mediums.add(med8);
        mediums.add(med9);
        mediums.add(med10);
        mediums.add(med11);
        mediums.add(med12);
        mediums.add(med13);
        mediums.add(med14);
        mediums.add(med15);
        mediums.add(med16);
        mediums.add(med17);
        mediums.add(med18);
        mediums.add(med19);
        mediums.add(med20);
        
        JpaUtil.creerContextePersistance();
        try {
            JpaUtil.ouvrirTransaction();

            mediums.forEach((medium) -> mediumDao.creer(medium));
            
            JpaUtil.validerTransaction();

        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service initialiserMediums()", ex);
            JpaUtil.annulerTransaction();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        

        System.out.println();
        if(DEBUG){
            System.out.println("** Mediums après persistance: ");
            mediums.forEach((medium) -> System.out.println(medium));
        }
        System.out.println();
        
    }
    
    
    //Gestion des clients

    public Long inscrireClient(Client client) {
        Long resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            JpaUtil.ouvrirTransaction();
            AstroTest astroApi = new AstroTest();
            List<String> profilClient = astroApi.getProfil(client.getPrenom(), client.getDateDeNaissance());
            ProfilAstral profilAstral = new ProfilAstral(profilClient.get(0), profilClient.get(1), profilClient.get(2), profilClient.get(3));
            client.setProfilAstral(profilAstral);
            clientDao.creer(client);
            JpaUtil.validerTransaction();
            Message.envoyerConfirmationInscription(client);
            resultat = client.getId();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service inscrireClient(client)", ex);
            JpaUtil.annulerTransaction();
            Message.envoyerEchecInscription(client);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }

    public Client rechercherClientParId(Long id) {
        Client resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            resultat = clientDao.chercherParId(id);
            resultat.getConsultations().size(); // Pour forcer à charger la liste
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service rechercherClientParId(id)", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }

    public Client authentifierClient(String mail, String motDePasse) {
        Client resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            // Recherche du client
            Client client = clientDao.chercherParMail(mail);
            if (client != null) {
                // Vérification du mot de passe
                if (client.getMotDePasse().equals(motDePasse)) {
                    resultat = client;
                }
            }
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service authentifierClient(mail,motDePasse)", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }

    public List<Client> listerClients() {
        List<Client> resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            resultat = clientDao.listerClients();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service listerClients()", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }

    
    // Gestion des employes
    
    
    public Long inscrireEmploye(Employe employe) {
        Long resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            JpaUtil.ouvrirTransaction();
            employeDao.creer(employe);
            JpaUtil.validerTransaction();
            resultat = employe.getId();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service inscrireEmploye(employe)", ex);
            JpaUtil.annulerTransaction();
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
        
    public Employe rechercherEmployeParId(Long id) {
        Employe resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            resultat = employeDao.chercherParId(id);
            resultat.getConsultations().size(); // Pour forcer à charger la liste
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service rechercherEmployeParId(id)", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    
    public Employe authentifierEmploye(String mail, String motDePasse) {
        Employe resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            // Recherche du client
            Employe employe = employeDao.chercherParMail(mail);
            if (employe != null) {
                // Vérification du mot de passe
                if (employe.getMotDePasse().equals(motDePasse)) {
                    resultat = employe;
                }
            }
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service authentifierEmploye(mail,motDePasse)", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    
    public List<Employe> listerEmployes() {
        List<Employe> resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            resultat = employeDao.listerEmployes();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service listerEmployes()", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    
    
    // Gestion des mediums
    
    
    public Long inscrireMedium(Medium medium) {
        Long resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            JpaUtil.ouvrirTransaction();
            mediumDao.creer(medium);
            JpaUtil.validerTransaction();
            resultat = medium.getId();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service inscrireMedium(medium)", ex);
            JpaUtil.annulerTransaction();
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    
    public Medium rechercherMediumParId(Long id) {
        Medium resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            resultat = mediumDao.chercherParId(id);
            resultat.getConsultations().size(); // Pour forcer à charger la liste
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service rechercherMediumParId(id)", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    
    public List<Medium> listerMediums() {
        List<Medium> resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            resultat = mediumDao.listerMediums();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service listerMediums()", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    
    
    // Gestion des consultations
    
    public Consultation prendreRendezVous(Client client,Medium medium) {
        Consultation resultat = null;
        boolean employeTrouve = Boolean.FALSE;
        JpaUtil.creerContextePersistance();
        try {
            List<Employe> employes = employeDao.chercherParGenreEtDisponible(medium.getGenre());
            //System.out.println(employe);
            if(employes != null) {
                for(Employe e : employes) {
                    try {
                        JpaUtil.ouvrirTransaction();
                        resultat = new Consultation(new Date(),client,e,medium);
                        e.addConsultation(resultat);
                        e.setEstOccupe(Boolean.TRUE);
                        employeDao.modifier(e); //Modification de l'employe
                        client.addConsultation(resultat);
                        clientDao.modifier(client); //Modification du client
                        medium.addConsultation(resultat);
                        mediumDao.modifier(medium); //Modification du medium
                        consultationDao.creer(resultat);
                        JpaUtil.validerTransaction();
                    } catch (OptimisticLockException oex) {
                        Logger.getAnonymousLogger().log(Level.WARNING, "Conflit sur l'employe, plusieurs accès en même temps ! On utilise l'employe suivant", oex);
                        e.setEstOccupe(Boolean.FALSE);
                        e.removeConsultation(resultat);
                        client.removeConsultation(resultat);
                        medium.removeConsultation(resultat);
                        continue; 
                    }
                    employeTrouve = Boolean.TRUE;
                    Message.envoyerNotificationConsultationEmploye(resultat);
                 }

            }
            if(employeTrouve == Boolean.FALSE){
                JpaUtil.annulerTransaction();
                Message.envoyerEchecDemandeConsultation(client, medium);
            }
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service prendreRendezVous(Client client,Medium medium)", ex);
            JpaUtil.annulerTransaction();
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
        
    public Consultation rechercherConsultationParId(Long id) {
        Consultation resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            resultat = consultationDao.chercherParId(id);
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service rechercherConsultationParId(id)", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    
    public void debuterConsultation(Consultation consultation) {
        consultation.setHeureDebut(new Date());
        JpaUtil.creerContextePersistance();
        try {
            JpaUtil.ouvrirTransaction();
            consultationDao.modifier(consultation);
            JpaUtil.validerTransaction();
            Message.envoyerConfirmationConsultation(consultation);
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service commencerConsultation(consultation)", ex);
        } finally {
            JpaUtil.fermerContextePersistance();
        }
    }
    
    public void terminerConsultation(Consultation consultation) {
        consultation.setHeureFin(new Date());
        Employe employe = consultation.getEmploye();
        employe.setEstOccupe(Boolean.FALSE);
        JpaUtil.creerContextePersistance();
        try {
            JpaUtil.ouvrirTransaction();
            consultationDao.modifier(consultation);
            employeDao.modifier(employe);
            JpaUtil.validerTransaction();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service arreterConsultation(consultation)", ex);
        } finally {
            JpaUtil.fermerContextePersistance();
        }
    }
    
    // Statistiques
    
    public Map<Employe, Long> repartitionClientsParEmploye() {
        Map<Employe, Long> resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            resultat = employeDao.nombreClientsParEmploye();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service listerEmployes()", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    
    // Predictions
    
    public List<String> getPredictions(int niveauAmour, int niveauSante, int niveauTravail, Client client) {     
        AstroTest astroApi = new AstroTest();
        List<String> predictions = null;
        try {
            predictions = astroApi.getPredictions(client.getProfilAstral().getCouleur(), client.getProfilAstral().getAnimal(), niveauAmour, niveauSante, niveauTravail);
        } catch(IOException ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service getPredictions(niveauAmour, niveauSante, niveauTravail, client)", ex);
        } finally {
            return predictions;
        }
    }
}
