
package filrouge.app;
public class RatingData {
    private String emailUtilisateur;
    private String avis;

    public RatingData() {
        // Constructeur vide requis pour Firebase
    }

    public RatingData(String emailUtilisateur, String avis) {
        this.emailUtilisateur = emailUtilisateur;
        this.avis = avis;
    }

    public String getEmailUtilisateur() {
        return emailUtilisateur;
    }

    public void setEmailUtilisateur(String emailUtilisateur) {
        this.emailUtilisateur = emailUtilisateur;
    }

    public String getAvis() {
        return avis;
    }

    public void setAvis(String avis) {
        this.avis = avis;
    }
}


