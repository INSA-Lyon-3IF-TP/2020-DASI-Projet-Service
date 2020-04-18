package util;

import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.modele.Medium;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author DASI Team, Nicolas Trouin, Fabien Narboux
 */
public class Message {
    
    private final static PrintStream OUT = System.out;
    private final static SimpleDateFormat TIMESTAMP_FORMAT = new SimpleDateFormat("yyyy-MM-dd~HH:mm:ss");
    private final static SimpleDateFormat HORODATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy à HH:mm:ss");
    private final static String MAIL_EXPEDITEUR = "contact@predict.if";
    
    private static void debut() {
        Date maintenant = new Date();
        OUT.println();
        OUT.println();
        OUT.println("---<([ MESSAGE @ " + TIMESTAMP_FORMAT.format(maintenant) + " ])>---");
        OUT.println();
    }
    
    private static void fin() {
        OUT.println();
        OUT.println("---<([ FIN DU MESSAGE ])>---");
        OUT.println();
        OUT.println();
    }
    
    public static void envoyerMail(String mailExpediteur, String mailDestinataire, String objet, String corps) {
        
        Date maintenant = new Date();
        Message.debut();
        OUT.println("~~~ E-mail envoyé le " + HORODATE_FORMAT.format(maintenant) + " ~~~");
        OUT.println("De : " + mailExpediteur);
        OUT.println("À  : " + mailDestinataire);
        OUT.println("Obj: " + objet);
        OUT.println();
        OUT.println(corps);
        Message.fin();
    }

    public static void envoyerNotification(String nomDestinataire, String telephoneDestinataire, String message) {
        
        Date maintenant = new Date();
        Message.debut();
        OUT.println("~~~ Notification envoyée le " + HORODATE_FORMAT.format(maintenant) + " ~~~");
        OUT.println("Pour  : " + nomDestinataire + " , Tél : " + telephoneDestinataire);
        OUT.println();
        OUT.println("Message : " + message);
        Message.fin();
    }
    
    public static void envoyerConfirmationConsultation(Consultation consultation){
        Client client = consultation.getClient();
        Medium medium = consultation.getMedium();
        Employe employe = consultation.getEmploye();
        String dateConsultation = HORODATE_FORMAT.format(consultation.getHeureDemande());
        
        StringWriter message = new StringWriter();
        PrintWriter notificationWriter = new PrintWriter(message);
        
        notificationWriter.println("Bonjour " + client.getPrenom() + ". J’ai bien reçu votre demande de consultation du " + dateConsultation + ".");
        notificationWriter.println("Vous pouvez dès à présent me contacter au " + employe.getTelephone() + ". A tout de suite !");
        notificationWriter.println("Médiumiquement vôtre, " + medium.getDenomination() + ".");
               
        Message.envoyerNotification(client.getPrenom() + " " + client.getNom(), client.getTelephone(), message.toString());
    }
    
    public static void envoyerEchecDemandeConsultation(Client client, Medium medium){
        StringWriter message = new StringWriter();
        PrintWriter notificationWriter = new PrintWriter(message);
        
        notificationWriter.println("Bonjour " + client.getPrenom() + ".");
        notificationWriter.println("Malheureusement, " + medium.getDenomination() + " n'est pas disponible actuellement.");
        notificationWriter.println("N'hésitez pas à reprendre rendez-vous à un autre moment.");
               
        Message.envoyerNotification(client.getPrenom() + " " + client.getNom(), client.getTelephone(), message.toString());
    }
    
    public static void envoyerNotificationConsultationEmploye(Consultation consultation){
        Client client = consultation.getClient();
        Medium medium = consultation.getMedium();
        Employe employe = consultation.getEmploye();
        
        StringWriter message = new StringWriter();
        PrintWriter notificationWriter = new PrintWriter(message);
        
        notificationWriter.println("Bonjour " + employe.getPrenom() + ".");
        notificationWriter.println("Consultation requise pour " + client.getCivilite() + " " + client.getPrenom() + " " + client.getNom() + ".");
        notificationWriter.println("Médium à incarner : " + medium.getDenomination() + ".");
               
        Message.envoyerNotification(employe.getPrenom() + " " + employe.getNom(), employe.getTelephone(), message.toString());
    }
    
    public static void envoyerConfirmationInscription(Client client){   
        StringWriter message = new StringWriter();
        PrintWriter notificationWriter = new PrintWriter(message);
        
        notificationWriter.println("Bonjour " + client.getPrenom() + ".");
        notificationWriter.println("Nous vous confirmons votre inscription au service PREDICT’IF.");
        notificationWriter.println("Rendez-vous vite sur notre site pour consulter votre profil astrologique et profiter des dons incroyables de nos mediums.");
               
        Message.envoyerMail(MAIL_EXPEDITEUR, client.getMail(), "Bienvenue chez PREDICT’IF", message.toString());
    }
    
    public static void envoyerEchecInscription(Client client){   
        StringWriter message = new StringWriter();
        PrintWriter notificationWriter = new PrintWriter(message);
        
        notificationWriter.println("Bonjour " + client.getPrenom() + ".");
        notificationWriter.println("Votre inscription au service PREDICT’IF a malencontreusement échoué...");
        notificationWriter.println("Merci de recommencer ultérieurement.");
               
        Message.envoyerMail(MAIL_EXPEDITEUR, client.getMail(), "Echec de l’inscription chez PREDICT’IF", message.toString());
    }
    
    public static void main(String[] args) {
        
        //DebugLogger.log("Début des Tests...");
        
        StringWriter corps = new StringWriter();
        PrintWriter mailWriter = new PrintWriter(corps);
        
        mailWriter.println("Bonjour,");
        mailWriter.println();
        mailWriter.println("  Ceci est un mail destiné à tester l'envoi simulé par affichage sur la console.");
        mailWriter.println();
        mailWriter.println("  Cordialement,");
        mailWriter.println();
        mailWriter.println("    Yann Gripay");

        Message.envoyerMail(
                "yann.gripay@insa-lyon.fr",
                "etudiants.3IF@insa-lyon.fr",
                "[DASI] Test d'envoi de e-mail",
                corps.toString()
            );
        
        
        StringWriter message = new StringWriter();
        PrintWriter notificationWriter = new PrintWriter(message);
        
        notificationWriter.println("Ceci est une notification pour prévenir de 2 choses:");
        notificationWriter.println("1) NE PAS oublier le poly");
        notificationWriter.println("2) TESTER au fur et à mesure du développement");

        Message.envoyerNotification(
                "Prenom Nom",
                "0988776655",
                message.toString()
            );
        
        //DebugLogger.log("Fin des Tests...");
        
    }
}