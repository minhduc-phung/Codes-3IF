package metier.modele;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import metier.modele.Consultation;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-05-19T11:27:22")
@StaticMetamodel(Employe.class)
public class Employe_ { 

    public static volatile SingularAttribute<Employe, String> motDePasse;
    public static volatile SingularAttribute<Employe, String> mail;
    public static volatile SingularAttribute<Employe, Date> dateNaissance;
    public static volatile SingularAttribute<Employe, Boolean> disponibilite;
    public static volatile SingularAttribute<Employe, String> genre;
    public static volatile SingularAttribute<Employe, Long> id;
    public static volatile SingularAttribute<Employe, String> nom;
    public static volatile SingularAttribute<Employe, String> prenom;
    public static volatile ListAttribute<Employe, Consultation> historiqueConsultations;
    public static volatile SingularAttribute<Employe, String> numTelephone;

}