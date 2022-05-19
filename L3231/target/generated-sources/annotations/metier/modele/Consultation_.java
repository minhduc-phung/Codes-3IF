package metier.modele;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import metier.modele.Client;
import metier.modele.Employe;
import metier.modele.Medium;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-05-19T11:27:22")
@StaticMetamodel(Consultation.class)
public class Consultation_ { 

    public static volatile SingularAttribute<Consultation, Date> dateHeureDemande;
    public static volatile SingularAttribute<Consultation, String> numero;
    public static volatile SingularAttribute<Consultation, Date> dateHeureDebutAppel;
    public static volatile SingularAttribute<Consultation, Employe> employe;
    public static volatile SingularAttribute<Consultation, Client> client;
    public static volatile SingularAttribute<Consultation, Medium> medium;
    public static volatile SingularAttribute<Consultation, String> commentaire;
    public static volatile SingularAttribute<Consultation, Date> dateHeureFinAppel;

}