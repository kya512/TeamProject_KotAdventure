package jeu

import personnage.Personnage



class Jeu(monstres: List<Personnage>) {
    //Le personage du joueur
    lateinit var joueur: Personnage
    //La liste des monstre a combatre
     var combats: MutableList<Combat> = mutableListOf()
    //Le score
    var score: Int = 0

    // Corps du constructeur
    init {
        // Lancement de la création du personage du joueur
        this.creerPersonnage()
        // Pour chaque monstre dans la liste de monstres
        for (unMonstre in monstres){
            // On créer un combat
            val unCombat = Combat(this,unMonstre, this.joueur)
            combats.add(unCombat)
        }
    }

    fun lancerCombat() {
        for (unCombat in this.combats) {
            unCombat.executerCombat()
            // Mettre à jour le score en fonction du nombre de tours
            val tours = unCombat.nombreTours
            score += calculerScore(tours)
        }
        println("Score final du joueur: $score")
    }

    private fun calculerScore(tours: Int): Int {
        // Par exemple, vous pouvez attribuer plus de points pour moins de tours
        return 500 - tours * 10
    }

    /**
     *  Méthode pour créer le personnage du joueur en demandant les informations à l'utilisateur
     *
     */
    fun creerPersonnage(): Personnage {
        println("\u001b[38;2;255;0;0m")
        println("\u001b[1m")
        println("Création de votre personnage:")
        println("\u001b[0m")

        println("\u001b[1m")
        println("\u001b[38;2;243;225;255m")
        // TODO Mission 1.1
        println("Entrez le nom : ")
        var nom = readln().toString()

        var attaque :Int = 0
        var defense :Int = 0
        var endurance :Int = 0
        var vitesse :Int = 0

        var total : Int = 0 //Total des points
 //Si le total des points depasse 40, les points se réinitialisent


        do {
            println("Vous avez 40 points à répartir sur vos compétences ( attaque / defense / endurance / vitesse ) ")

            println("Entrez votre niveau d'attaque : ")
            attaque = readln().toInt()
            total+= attaque
            println("il vous reste ${40-total}")

            println("Entrez votre niveau de defense : ")
            defense = readln().toInt()
            total+= defense
            println("il vous reste ${40-total}")

            println("Entrez votre niveau d'endurance : ")
            endurance = readln().toInt()
            total+= endurance
            println("il vous reste ${40-total}")

            println("Entrez votre niveau de vitesse : ")
            vitesse = readln().toInt()
            total+= vitesse
            println("il vous reste ${40-total}")

            if (total > 40){
                println( "Vous avez dépassé votre total de points, veuillez ressaisir vos point en respectant la limite")
                total = 0
            }

        } while (total == 0)


        var pointDeVie = 50 //Total des points
        var pointDeVieMax = pointDeVie + 10*endurance // Point de vie max
        println("\u001b[0m")
        println("\u001b[1m")
        println("\u001b[38;5;${10}m")
        println("Vous avez $pointDeVie pv et $pointDeVieMax de pv Max")
        println("\u001b[0m")
        val hero = Personnage(nom,pointDeVie,pointDeVieMax,attaque,defense,endurance,vitesse)
        this.joueur = hero

        return hero
    }


}