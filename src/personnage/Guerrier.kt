package personnage
import personnage.Personnage

class Guerrier(
    nom: String,
    pointDeVie: Int,
    pointDeVieMax: Int,
    attaque: Int,
    defense: Int,
    endurance: Int,
    vitesse: Int,
): Personnage(nom, pointDeVie, pointDeVieMax, attaque, defense, endurance, vitesse) {


    fun afficherArmes(){
    }

    override fun toString() = " Guerrier ${super.toString()} (armeSecondaireEquipee=${super.armeSecondaireEquipe} "

    override fun attaque(adversaire: Personnage){
        var degats = 1 //initialise les dégats à 1
        var bonus = super.attaque/2 //calcul du bonus en fonction du niveau d'attaque du personnage
        var defenseCible = super.calculeTotalDefense() //calcul defense cible
        if (super.armeSecondaireEquipe != null ){ // si l'arme secondaire est équipé
            degats = calculerDegats((0..10).random(),(0..10).random()) + bonus +defenseCible
        } else{
            degats = bonus - defenseCible
        }

        if (degats < 1){ // si les dégats sont inférieur à 1
            degats = 1
        }
        adversaire.pointDeVie -= degats
        println("${super.nom} attaque ${adversaire.nom} avec une attaque de base et inflige $degats points de dégâts.")

    }




}