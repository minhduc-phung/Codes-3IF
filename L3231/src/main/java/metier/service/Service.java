/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.service;

import dao.Clientdao;
import dao.Consultationdao;
import dao.Employedao;
import dao.JPaUtil;
import dao.Mediumdao;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Pair;
import metier.modele.Astrologue;
import metier.modele.Cartomancien;
import metier.modele.Client;
import metier.modele.Consultation;
import metier.modele.Employe;
import metier.modele.Medium;
import metier.modele.ProfilAstral;
import metier.modele.Spirite;
import util.AstroNetApi;
import util.Message;

/**
 *
 * @author nelouennas
 */
public class Service {

    public Service() {
    }

    public Client inscrireClient(Client c) {
        Clientdao clientDao = new Clientdao();
        try {
            AstroNetApi api = new AstroNetApi();
            List<String> profil = new ArrayList();
            profil = api.getProfil(c.getPrenom(), c.getDateNaissance());
            c.getProfilAstral().setZodiaque(profil.get(0));
            c.getProfilAstral().setAstroChinois(profil.get(1));
            c.getProfilAstral().setCouleurPB(profil.get(2));
            c.getProfilAstral().setAnimalTotem(profil.get(3));

            JPaUtil.creerContextePersistance();
            JPaUtil.ouvrirTransaction();
            clientDao.creer(c);
            JPaUtil.validerTransaction();

            Message.envoyerMail("contact@predict.if", c.getMail(), "Bienvenue chez PREDICT'IF", "Bonjour " + c.getPrenom() + ", nous vous confirmons votre inscription au service PREDICT'IF. Rendez-vous vite sur notre site pour consulter votre profil astrologique et profiter des dons incroyables de nos mediums");

        } catch (javax.persistence.RollbackException ex) {
            ex.printStackTrace();
            JPaUtil.annulerTransaction();
            c = null;
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getAnonymousLogger().log(Level.INFO, " > Echec");
            JPaUtil.annulerTransaction();
            Message.envoyerMail("contact@predict.if", c.getMail(), "Echec de l'inscription chez PREDICT'IF", "Bonjour " + c.getPrenom() + ", votre inscription au service PREDICT'IF a malencontreusement échoué...\nMerci de recommencer ultérieurement.");
            c = null;
        } finally {
            JPaUtil.fermerContextePersistance();

        }
        return c;

    }

    public Client authentifierClient(String mail, String mdp) {
        Clientdao clientDAO = new Clientdao();
        Client c = null;
        try {
            JPaUtil.creerContextePersistance();
            c = clientDAO.authentifierClient(mail); // renvoie exception si pas de client trouvé
            if (!c.getMotDePasse().equals(mdp)) {
                c = null;
            }
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.INFO, "Erreur");
            c = null;
        } finally {
            JPaUtil.fermerContextePersistance();
        }
        return c;
    }

    public Employe authentifierEmploye(String mail, String mdp) {
        Employedao employeDAO = new Employedao();
        Employe e = null;
        try {
            JPaUtil.creerContextePersistance();
            e = employeDAO.authentifierEmploye(mail); // renvoie exception si pas de client trouvé
            if (!e.getMotDePasse().equals(mdp)) {
                e = null;
            }
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.INFO, "Erreur");
            e = null;
        } finally {
            JPaUtil.fermerContextePersistance();
        }
        return e;
    }

    public List<Employe> initialiserEmployes() {
        Employedao employeDao = new Employedao();

        List<Employe> listeEmployes = new ArrayList();
        SimpleDateFormat SDFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            listeEmployes.add(new Employe("M.", "Perrin", "Rémy", SDFormat.parse("1970-10-21"), "0645789654", "remy.perrin@gmail.com", "remy_perrin"));
            listeEmployes.add(new Employe("Mme.", "Pichon", "Catherine", SDFormat.parse("1995-8-1"), "0645872032", "catherine.pichon@gmail.com", "catherine_pichon"));
            listeEmployes.add(new Employe("M.", "BORROTI MATIAS DANTAS", "Raphaël", SDFormat.parse("1976-07-10"), "0328178508", "rborrotimatiasdantas4171", "rborroti"));
            listeEmployes.add(new Employe("Mme.", "OLMEADA MARAIS", "Nor", SDFormat.parse("1983-12-09"), "0418932546", "nolmeadamarais1551@gmail.com", "nor_olmeada"));
            listeEmployes.add(new Employe("M.", "ABDIULLINA", "David Alexander	", SDFormat.parse("1975-01-07"), "0590232772", "david-alexander.abdiullina@laposte.net", "davidalexander"));
            listeEmployes.add(new Employe("Mme.", "SING", "Ainhoa", SDFormat.parse("1982-11-09"), "0705224200", "asing8183@free.fr", "asing"));
            listeEmployes.add(new Employe("M.", "WOAGNER", "Moez", SDFormat.parse("1984-08-16"), "0832205629", "moez.woagner@laposte.net", "w_moez"));
            listeEmployes.add(new Employe("Mme.", "VOYRET", "Alice", SDFormat.parse("1988-08-13"), "0486856520", "alice.voyret@hotmail.com", "a_voyret"));
            listeEmployes.add(new Employe("M.", "RINERD", "Julien", SDFormat.parse("1989-05-16"), "0727252485", "jrinerd5241@yahoo.com", "j_rinerd"));
            listeEmployes.add(new Employe("Mme.", "BEUOLLOT", "Audrey-Laure", SDFormat.parse("1993-04-10"), "0202294489", "audrey-laure.beuollot@gmail.com", "a_laure"));
            listeEmployes.add(new Employe("M.", "DALMARRE", "Jose Luis", SDFormat.parse("1994-05-17"), "0549997540", "jose-luis.dalmarre@free.fr", "j_dalmarre"));
            listeEmployes.add(new Employe("Mme.", "LOU", "Silvia", SDFormat.parse("1985-01-22"), "0378851388", "silvia.lou@laposte.net", "s_lou"));
            listeEmployes.add(new Employe("M.", "IGARWAL", "Mick", SDFormat.parse("1992-07-09"), "0590579778", "mick.igarwal@outlook.com", "m_igarwal"));
            listeEmployes.add(new Employe("M.", "PRIMEULT", "Cheng", SDFormat.parse("1971-10-03"), "0618703838", "cprimeult7807@hotmail.com", "c_primeult"));
            listeEmployes.add(new Employe("Mme.", "SOUMOELLIN", "Stéphanie", SDFormat.parse("1983-05-22"), "0239372281", "ssoumoellin3265@yahoo.com", "s_soumoellin"));

            JPaUtil.creerContextePersistance();
            JPaUtil.ouvrirTransaction();
            for (int i = 0; i < listeEmployes.size(); i++) {
                employeDao.creer(listeEmployes.get(i));

            }
            JPaUtil.validerTransaction();

        } catch (javax.persistence.RollbackException ex) {
            ex.printStackTrace();
            JPaUtil.annulerTransaction();
            listeEmployes = null;
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getAnonymousLogger().log(Level.INFO, " > Echec");
            JPaUtil.annulerTransaction();
            listeEmployes = null;
        } finally {
            JPaUtil.fermerContextePersistance();
        }
        return listeEmployes;
    }

    public List<Medium> initialiserMediums() {
        Mediumdao mediumDao = new Mediumdao();

        List<Medium> listeMediums = new ArrayList();
        listeMediums.add(new Cartomancien("Mme.", "Irma", "Comprenez votre entourage grâce à mes cartes ! Résultats rapides"));
        listeMediums.add(new Cartomancien("Mme.", "Endora", "Mes cartes répondront à toutes vos questions personnelles."));
        listeMediums.add(new Cartomancien("Mme.", "Deborah", "Votre Futur, maintenant au Présent"));
        listeMediums.add(new Cartomancien("M.", "Patrick", "Dans le cadre d’une consultation,je m’engage à répondre,avec compétence et sincérité, à toutes les questions qui vous préoccupent."));
        listeMediums.add(new Cartomancien("M.", "Alexandre", "Ma passion c'est de vous aider à prendre les bonnes décisions, celles qui vont résoudre vos problèmes"));
        listeMediums.add(new Cartomancien("M.", "Laurent", "Grâce aux chiffres et à mon précieux tarot de Marseille, je serais en mesure de vous révéler ce que le destin vous réserve !"));
        listeMediums.add(new Cartomancien("Mme.", "Mandy", "Tarologue depuis de nombreuses années, je me sers de l’Oracle de Belline pour aller au plus loin dans la divination et l’interprétation de votre chemin de vie."));
        listeMediums.add(new Spirite("M.", "Professeur Tran", "Votre avenir est devant vous : regardons-le ensemble", "Marc de café, boule de cristal, oreilles de lapin"));
        listeMediums.add(new Spirite("Mme.", "Gwenaëlle", "Spécialiste des grandes conversations au-delà de TOUTES les frontières", "Boule de cristal"));
        listeMediums.add(new Spirite("Mme.", "Geraldine", "Médium depuis mon enfance, au contact de photos ou à l’écoute des personnes, je perçois des images du passé, du présent et du futur.", "Photos"));
        listeMediums.add(new Spirite("Mme.", "Nini", "Mes flashs sont tout aussi précis car je suis guidée par le son de votre voix.", "Encens"));
        listeMediums.add(new Spirite("Mme.", "Nefer", "Animée par la volonté d’éclairer votre chemin, je vous délivre une voyance sans complaisance.", "Psychographe"));
        listeMediums.add(new Spirite("M.", "Darius", "Vous en avez assez de subir les coups du sort ? Moi, Darius, je peux vous aider à devancer la fatalité pour voguer vers de nouveaux horizons, plus calmes et sereins. ", "Pendule, Telepathie"));
        listeMediums.add(new Spirite("M.", "Valentin", "Mes ressentis fonctionnent particulièrement bien avec les problèmes de cœur, car ma sensibilité me permet de cerner votre histoire et de vous apporter les réponses sincères que vous recherchez.", "Flashs, Doigts de la main"));
        listeMediums.add(new Astrologue("Mme.", "Serena", "Basée à Champigny-sur-Marne, Serena vous révèlera votre avenir pour éclairer votre passé", "Ecole Normale Supérieure d'Astrologie(ENS Astro)", "2006"));
        listeMediums.add(new Astrologue("M.", "M", "Avenir, avenir, que nous réserves-tu ? N'attendez plus, demandez à me consulter!", "Institut des Nouveaux Savoirs Astrologiques", "2010"));
        listeMediums.add(new Astrologue("M.", "Olivier", "Aujourd’hui, accompagné de mes guides, les anges, je peux vous soulager de vos douleurs", "Ecole d'Astrologie", "2014"));
        listeMediums.add(new Astrologue("Mme.", "Ambre", "Quel que soit le problème qui vous préoccupe, il existe une solution !", "Polyastrologie", "2012"));
        listeMediums.add(new Astrologue("Mme.", "Shakira", "Au contact de votre voix, des visions de votre futur m'envahissent", "Faculté D'astrologie", "2009"));
        listeMediums.add(new Astrologue("M.", "Jimmy", " Vous avez des questions et les doutes vous empêchent d’avancer ? Ne cherchez plus, j’ai toutes les réponses dont vous avez besoin.", "Mines, ponts et astrologie", "2018"));
        listeMediums.add(new Astrologue("M.", "Donovan", "Votre avenir vous tourmente ? N'hésitez pas à solliciter mon aide.", "Université Technologiques d'astrologie", "2005"));

        try {

            JPaUtil.creerContextePersistance();
            JPaUtil.ouvrirTransaction();
            for (int i = 0; i < listeMediums.size(); i++) {
                mediumDao.creer(listeMediums.get(i));

            }
            JPaUtil.validerTransaction();

        } catch (javax.persistence.RollbackException ex) {
            ex.printStackTrace();
            JPaUtil.annulerTransaction();
            listeMediums = null;
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getAnonymousLogger().log(Level.INFO, " > Echec");
            JPaUtil.annulerTransaction();
            listeMediums = null;
        } finally {
            JPaUtil.fermerContextePersistance();
        }
        return listeMediums;
    }

    public Consultation creerConsultation(Client c, Medium m) {
        Employedao employeDao = new Employedao();
        Clientdao clientDao = new Clientdao();
        Mediumdao mediumDao = new Mediumdao();
        Consultationdao consultationDao = new Consultationdao();
        Consultation con = null;
        try {
            JPaUtil.creerContextePersistance();
            JPaUtil.ouvrirTransaction();
            Employe e = employeDao.affectationEmploye(m);
            if (e != null) {
                con = new Consultation(c, e, m, new Date());
                c.addConsultation(con);
                e.addConsultation(con);
                m.addConsultation(con);
                e.setDisponibilite(Boolean.FALSE);
                Message.envoyerNotification(e.getNumTelephone(), "Vous avez une nouvelle consultation sur votre profil veuillez vous connecter sur le site predict'IF");
                consultationDao.creer(con);
                employeDao.merge(e);
                clientDao.merge(c);
                mediumDao.merge(m);
                JPaUtil.validerTransaction();
            } else {
                System.out.println("");
                System.out.println("************Ce medium n'est malheureusment pas disponible. Prière de réessayer plus tard****************");
                System.out.println("");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getAnonymousLogger().log(Level.INFO, " > Echec");
            con = null;
            JPaUtil.annulerTransaction();
        } finally {
            JPaUtil.fermerContextePersistance();
        }
        return con;
    }

    public List<Medium> consulterListeMedium() {
        JPaUtil.creerContextePersistance();
        Mediumdao mediumdao = new Mediumdao();
        List<Medium> listeMedium = mediumdao.consulterListeMediums();
        JPaUtil.fermerContextePersistance();
        return listeMedium;
    }

    public ProfilAstral consulterProfilAstral(Client c) {
        return c.getProfilAstral();
    }

    public List<Consultation> consulterHistoriqueClient(Client c) {
        return c.getHistoriqueConsultations();
    }

    public List<Consultation> consulterHistoriqueEmploye(Employe e) {
        return e.getHistoriqueConsultations();
    }

    public List<String> consulterPrediction(ProfilAstral p, int amour, int sante, int travail) {
        List<String> prediction = new ArrayList();
        try {
            AstroNetApi api = new AstroNetApi();
            prediction = api.getPredictions(p.getCouleurPB(), p.getAnimalTotem(), amour, sante, travail);
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getAnonymousLogger().log(Level.INFO, " > Echec");
            prediction = null;
        }
        return prediction;
    }

    public Consultation seDeclarerPret(Consultation c) {
        if (c != null) {
            Date date = c.getDateHeureDemande();
            Message.envoyerNotification(c.getClient().getNumTelephone(), "Bonjour " + c.getClient().getPrenom() + ". J'ai bien reçu votre demande de consultation du " + date.getDate() + "/" + (date.getMonth() + 1) + "/" + (date.getYear() + 1900) + " à " + date.getHours() + "h" + date.getMinutes() + ".\nVous pouvez dès à présent me contacter au " + c.getEmploye().getNumTelephone() + ". A tout de suite ! Médiumiquement vôtre, " + c.getMedium().getDenomination());
        }
        return c;
    }

    public Consultation demarrerConsultation(Consultation c) {
        Consultationdao consultationDao = new Consultationdao();
        c.setDateHeureDebutAppel(new Date());
        try {
            JPaUtil.creerContextePersistance();
            JPaUtil.ouvrirTransaction();
            consultationDao.merge(c);
            JPaUtil.validerTransaction();
        } catch (javax.persistence.RollbackException ex) {
            ex.printStackTrace();
            JPaUtil.annulerTransaction();
            c = null;
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getAnonymousLogger().log(Level.INFO, " > Echec");
            JPaUtil.annulerTransaction();
            c = null;
        } finally {
            JPaUtil.fermerContextePersistance();
        }
        return c;
    }

    public Consultation terminerConsultation(Consultation c, String commentaire) {
        Consultationdao consultationDao = new Consultationdao();
        Employedao employeDao = new Employedao();
        c.setDateHeureFinAppel(new Date());
        c.setCommentaire(commentaire);
        c.getEmploye().setDisponibilite(true);
        try {
            JPaUtil.creerContextePersistance();
            JPaUtil.ouvrirTransaction();
            consultationDao.merge(c);
            employeDao.merge(c.getEmploye());
            JPaUtil.validerTransaction();
        } catch (javax.persistence.RollbackException ex) {
            ex.printStackTrace();
            JPaUtil.annulerTransaction();
            c = null;
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getAnonymousLogger().log(Level.INFO, " > Echec");
            JPaUtil.annulerTransaction();
            c = null;
        } finally {
            JPaUtil.fermerContextePersistance();
        }
        return c;
    }

    public HashMap<Medium, Integer> nombreConsultationsMediums() {
        List<Medium> listeMedium = consulterListeMedium();
        HashMap<Medium, Integer> map = new HashMap<>();
        for (Medium medium : listeMedium) {
            map.put(medium, medium.getHistoriqueConsultations().size());
        }
        return map;
    }

    public List<Pair<Medium, Integer>> top5Mediums() {
        List<Pair<Medium, Integer>> list = new LinkedList();

        List<Medium> listeMedium = consulterListeMedium();
        for (Medium medium : listeMedium) {
            list.add(new Pair<>(medium, medium.getHistoriqueConsultations().size()));
        }
        list.sort(Comparator.<Pair<Medium, Integer>>comparingInt(Pair::getValue));
        Collections.reverse(list);
        List<Pair<Medium, Integer>> top5 = new LinkedList();
        for (int i = 0; i < 5; i++) {
            top5.add(list.get(i));
        }
        return top5;
    }

    public List<Pair<Employe, Long>> repartitionClientsParEmploye() {
        List<Pair<Employe, Long>> listeRepartition = new LinkedList();
        JPaUtil.creerContextePersistance();
        Employedao employedao = new Employedao();
        Clientdao clientDAO = new Clientdao();
        long nbClients = clientDAO.compterClients();
        List<Employe> listeEmployes = employedao.listerEmployes();
        for (Employe employe : listeEmployes) {
            listeRepartition.add(new Pair<>(employe, employedao.NbClientsParEmploye(employe)));
        }
        JPaUtil.fermerContextePersistance();

        return listeRepartition;
    }
}
