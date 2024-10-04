package personnage

class Voleur(
    nom: String,
    pointDeVie: Int,
    pointDeVieMax: Int,
    attaque: Int,
    defense: Int,
    endurance: Int,
    vitesse: Int,
): Personnage(nom, pointDeVie, pointDeVieMax, attaque, defense, endurance, vitesse){


    override fun toString() = "Voleur ${super.toString()}  "

    fun volerItem(victime:Personnage) {
        if (inventaire.isEmpty()) {
            println("L'inventaire est vide.")
        } else {
            println("L'inventaire n'est pas vide.")

            var position =  (0..victime.inventaire.lastindex).random()
            var item = victime.inventaire[position]
            // Ajouter a l'inventaire du voleur
            this.inventaire.add(item)
            // on le retire de l'inventaire de la victime
            victime.inventaire.remove(item)
            if ( item == victime.armeEquipe)  victime.armeEquipe=null // si l'item est l'arme équipée de la victime, on le lui retire 
            if ( item == victime.armureEquipe)  victime.armureEquipe=null // idem pour l'armure 🤣
            toString()


            println("Vous avez dérober une arme à $victime.\n" + "Vous avez dérober une armure à $victime.")
        }
    }
}
