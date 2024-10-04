package personnage

import src.item.*



class Personnage(
    val nom: String,
    var pointDeVie: Int,
    val pointDeVieMax: Int,
    var attaque: Int,
    var defense: Int,
    var endurance: Int,
    var vitesse: Int,
) {

    var inventaire :MutableList<Item> = mutableListOf() //inventaire du personnage
    var armeEquipe : Arme? =null
    var armeSecondaireEquipe : Arme? =null
    var armureEquipe: Armure? =null
     //méthodes avoirPotion(), avoirBombe(), boirePotion()
     fun avoirPotion():Boolean{
        return inventaire.count{it is Potion} != 0
     }

     fun avoirBombe():Boolean{
         return inventaire.count{it is Bombe} != 0
     }

     fun boirePotion():Unit{

         var firstPotion :Potion = Potion()
         var listePotion: MutableList<Potion> = mutableListOf<Potion>()
         inventaire.forEach{if( it is Potion ) listePotion.add(it)} //filtre la liste pour avoir que les items de type potion

         if ( avoirPotion() ) { //Le personnage récupère la première potion de la liste (s'il y en a)
             firstPotion = listePotion[0] // prends la première de la liste
             pointDeVie += firstPotion.soin // Le personnage récupère le montant de soin de la potion.
             inventaire.remove(firstPotion) // Le personnage retire la potion de son inventaire.

         }
         println("La potion ${firstPotion.nom} t'a permis de récupérer ${firstPotion.soin} \n" +
         "pv actuel pour ${this.nom}: ${this.pointDeVie} ")

     }

    fun passe(){
        //TODO
    }



     fun calculeTotalDefense():Int{
         if(this.armureEquipe != null){
             return this.defense / 2 + this.armureEquipe!!.calculProtection()
         } else {
             return this.defense / 2
         }
         // TODO: trouver si le perso a une armure puis rajouter le bonus

     }

     // Méthode pour attaquer un adversaire
     fun attaque(adversaire: Personnage) {
        //Mission 4.1
         var degats= this.attaque/2
         //Mission 5.1
         if (adversaire.pointDeVie <= 0) println("L'adversaire à perdu le combat")// verifier que les degats ne soit pas negatif

         // vérifie si l'arme est équipée
         if (armeEquipe != null){
             degats += armeEquipe!!.calculerDegats((0..10).random(),(0..10).random())// utilise la fonction pour calculer les degats de l'arme
         }
         if (adversaire.defense <=1){//Les dégâts sont ajustés en fonction de la défense de l'adversaire.
             adversaire.pointDeVie -= degats
         } else {
             adversaire.pointDeVie -= 1 //Au minimum, au moins 1 point de dégât est infligé.
         }
         println("$nom attaque ${adversaire.nom} avec une attaque de base et inflige $degats points de dégâts.")

    }


    //méthode qui permet d’équiper

    fun equipe(arme : Arme){
        //vérifie si l'arme est dans l'inventaire
        if (arme in inventaire) {
            armeEquipe = arme
            println("$nom équipe $arme")

        } else {
            println("Vous ne possédez pas de $arme")
        }
    }
    fun equipe(armure : Armure) {
        //verifie si l'armure est dans l'inventaire
        if (armure in inventaire) {
            armureEquipe = armure
            println("$nom équipe $armure")

        } else {
            println("Vous ne possédez pas de $armure")
        }
    }

    fun afficheInventaire(){
        println("Inventaire de $nom")
        inventaire.forEach{println("${inventaire.indexOf(it)} : $it")}
    }

    fun loot(cible: Personnage){
        if(cible.pointDeVie <= 0){//On vérifie que la cible a des pv inférieure ou égale à 0.
            armeEquipe = null
            armureEquipe = null

            // on transfère son inventaire a l’objet courant (on ajoute l’inventaire de la cible a l’objet courant).
            this.inventaire.addAll(cible.inventaire)
        }


    }

    override fun toString(): String {
        return "$nom (PV: $pointDeVie/$pointDeVieMax, Attaque: $attaque, Défense: $defense, Endurance: $endurance, Vitesse: $vitesse)"
    }


}